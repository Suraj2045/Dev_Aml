package com.nellinfotech.aml.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nellinfotech.aml.config.GenerateToken;
import com.nellinfotech.aml.config.Iconstants;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.MenuRoleMap;
import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.entities.UserPasswordPolicyEntity;
import com.nellinfotech.aml.model.DecodedTokenData;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.LoginService;
import com.nellinfotech.aml.service.RoleMapService;
import com.nellinfotech.aml.service.UserMstService;
import com.nellinfotech.aml.service.UserPasswordPolicyService;

/**
 * @author ASHWIN
 */

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    RoleMapService roleMenuMapService;
    
    @Autowired
    UserMstService userMstService;
    
    @Autowired
    private UserPasswordPolicyService userPasswordPolicyService;
    
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    
    @Override
    public ResponseEntity<Map<String, Object>> userLogin(UserMst user) {
        ResponseEntity<Map<String, Object>> result = null;
        try {
            Map<String, Object> json = new HashMap<String, Object>();
            HttpHeaders headers = new HttpHeaders();
            Map<String, Object> message = new HashMap<String, Object>();
            
        
            ///
            Integer loginflag = 0;
            UserMst userMst = userMstService.getUserMstByBankCodeAndUserId(user.getBankCode(), user.getUserId());
            if (userMst != null) {
                if (userMst.getIsActive() != 1) {
                    loginflag = 1;
                }
                if (userMst.getIsUserLocked() != 0) {
                    loginflag = 1;
                }
                if (userMst.getIsLoginSuspended() != 0) {
                    loginflag = 1;
                }
                if (userMst.getIsUserLoggedIn() != 0) {
                    if (userMst.getIsConcurrentLoginAllowed().equals(1)) {
                        loginflag = 1;
                    } else {
                        
                    }
                }
                if (!userMst.getPwd().contentEquals(user.getPwd())) {
                    Integer badLoginCount = userMst.getBadLoginCount();
                    if (badLoginCount < 3) {
                        loginflag = 1;
                        badLoginCount = badLoginCount + 1;
                        userMst.setBadLoginCount(badLoginCount);
                        userMstService.saveUserMst(userMst);
                    } else {
                        loginflag = 1;
                        userMst.setIsUserLocked(1);
                        userMstService.saveUserMst(userMst);
                    }
                }
                if (userMst.getPwdChangeNextLogin() != 0) {
                    loginflag = 1;
                }
                
                if (!loginflag.equals(1)) {
                    userMst.setIsConcurrentLoginAllowed(1);
                    userMstService.saveUserMst(userMst);
                    UserMst userData = new UserMst();
                    userData.setId(userMst.getId());
                    //userData.setUserName(userMst.getUserName());
                    String jwttoken = "";
                    DecodedTokenData tokenData = GenerateToken.generateToken(Iconstants.SECRET_KEY, userData);
                    jwttoken = tokenData.getAccessToken();
                    List<MenuRoleMap> menumap = roleMenuMapService.getUserRoleMenus(userMst.getBaseRoleCode());
                    json.put("userId", userMst.getUserId());        
                    message.put("summary", "login successfully");
                    message.put("code", 200);
                    json.put("user", userMst);
                    json.put("menu", menumap);
                    json.put("Token", jwttoken);
                    json.put("message", message);
                    headers.add("Content-Type", "application/json; charset=UTF-8");
                    headers.add("X-Fsl-Location", "/");
                    headers.add("X-Fsl-Response-Code", "200(ok)");
                    result = (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
                    return result;
                }
                else
                {
                	if(userMst!=null) {
            			if(userMst.getBadLoginCount()>0&&userMst.getBadLoginCount()<3)
            			{
                 json.put("userId", userMst.getUserId()); 
                 message.put("summary", "check  password");
                 message.put("code", 401);
                 json.put("message", message);
                  headers.add("Content-Type", "application/json; charset=UTF-8");
                 headers.add("X-Fsl-Location", "/");
                 headers.add("X-Fsl-Response-Code", "401(UNAUTHORIZED)");     
                 result = (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.UNAUTHORIZED));
                return result;
            			}
            			 if(userMst.getIsUserLocked()>0)
            			{
            				 json.put("userId", userMst.getUserId()); 
                             message.put("summary", "user is locked contact to Admin");
                             message.put("code", 200);
                             json.put("message", message);
                             headers.add("Content-Type", "application/json; charset=UTF-8");
                             headers.add("X-Fsl-Location", "/");
                             headers.add("X-Fsl- -Code", "200(ok)");                    
                             result = (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
                             return result;
            			}
            		}
                }
            } else {
                   message.put("summary", "check  bankcode and userId");
                   message.put("code", 404);
                   json.put("message", message);
                    headers.add("Content-Type", "application/json; charset=UTF-8");
                   headers.add("X-Fsl-Location", "/");
                   headers.add("X-Fsl-Response-Code", "404(NOT_FOUND)");
            		result = (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.NOT_FOUND));
            		return result;	
            }
            // System.out.println(userMst.getUserName());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
    
    /**
     * @author ASHWIN
     */
    @Transactional
    @Override
    public String changeCredentials(Map<String, String> map) {
        logger.info("In LoginServiceImpl --- changeCredentials()");
        String oldPwd = map.get("oldPwd");
        String bankCode = map.get("BANKCODE");
        String userId = map.get("USERID");
        String newPwd = map.get("newPwd");
        UserMst userMst;
        
        try {
            userMst = userMstService.getUserMstByBankCodeAndUserId(bankCode, userId);
            patternMatch(newPwd, userMst);
            // validate patternMatch response Here
            if (userMst.getPwdExpiryDate() != null) {
                updatePasswordExp(newPwd, userMst);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return newPwd;
        
    }
    
    private void updatePasswordExp(String newPwd, UserMst entity) {
        
        List<UserPasswordPolicyEntity> lstUserPasswordPolicyEntities = userPasswordPolicyService
                .getUserPasswordPolicyByBankCode(entity.getBankCode());
        
        UserPasswordPolicyEntity passwordPolicyEntity = lstUserPasswordPolicyEntities.get(0);
        
        Integer expDays = passwordPolicyEntity.getPwdExpDays();
        Date oldDate = entity.getPwdExpiryDate();
        Calendar c = Calendar.getInstance();
        c.setTime(oldDate);
        c.add(Calendar.DAY_OF_MONTH, expDays);
        Date newExpDate = c.getTime();
        entity.setPwd(newPwd);
        entity.setPwdExpiryDate(newExpDate);
        
    }
    
    private String patternMatch(String newDigest, UserMst entity) {
        
        List<UserPasswordPolicyEntity> lstUserPasswordPolicyEntities = userPasswordPolicyService
                .getUserPasswordPolicyByBankCode(entity.getBankCode());
        
        UserPasswordPolicyEntity userPasswordPolicyEntity = lstUserPasswordPolicyEntities.get(0);
        
        if (newDigest.length() <= userPasswordPolicyEntity.getMinPassLength()
                && newDigest.length() >= userPasswordPolicyEntity.getMaxPassLength()) {
            return "PAWD_LENGTH_NOT_MATCHED";
        }
        int alphaCount = 0;
        int digCount = 0;
        int specialCharCount = 0;
        for (int i = 0; i < newDigest.length(); i++) {
            if (Character.isAlphabetic(newDigest.charAt(i))) {
                alphaCount++;
            } else if (Character.isDigit(newDigest.charAt(i))) {
                digCount++;
            } else {
                specialCharCount++;
            }
        }
        if (alphaCount < userPasswordPolicyEntity.getMinAlpha()) {
            return "ALPHA_NOT_MATCHED";
        }
        if (digCount < userPasswordPolicyEntity.getMinDigit()) {
            return "DIG_NOT_MATCHED";
            
        }
        if (specialCharCount < userPasswordPolicyEntity.getMinSpecialChar()) {
            return "DIG_NOT_MATCHED";
            
        }
        return ResponseStatus.SUCCESS;
        
    }
    
    @Override
    public String checkPasswordPolicy(Header header, String pwd) {
        try {
            List<UserPasswordPolicyEntity> lstUserPasswordPolicyEntities = userPasswordPolicyService
                    .getUserPasswordPolicyByBankCode(header.getBankCode());
            
            UserPasswordPolicyEntity userPasswordPolicyEntity = lstUserPasswordPolicyEntities.get(0);
            
            if (pwd.length() <= userPasswordPolicyEntity.getMinPassLength()
                    && pwd.length() >= userPasswordPolicyEntity.getMaxPassLength()) {
                return "PAWD_LENGTH_NOT_MATCHED";
            }
            int alphaCount = 0;
            int digCount = 0;
            int specialCharCount = 0;
            for (int i = 0; i < pwd.length(); i++) {
                if (Character.isAlphabetic(pwd.charAt(i))) {
                    alphaCount++;
                } else if (Character.isDigit(pwd.charAt(i))) {
                    digCount++;
                } else {
                    specialCharCount++;
                }
            }
            if (alphaCount < userPasswordPolicyEntity.getMinAlpha()) {
                return "ALPHA_NOT_MATCHED";
            }
            if (digCount < userPasswordPolicyEntity.getMinDigit()) {
                return "DIG_NOT_MATCHED";
                
            }
            if (specialCharCount < userPasswordPolicyEntity.getMinSpecialChar()) {
                return "DIG_NOT_MATCHED";
                
            }
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseStatus.FAILED;
    }

	@Override
	public String userLock(UserMst user) {
		try{
			UserMst userMst= userMstService.userLock(user);
			return "User unlocked";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "User Locked";
	}
    
}