package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.RoleMst;
import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.UserMstRepository;
import com.nellinfotech.aml.service.UserMstService;
    
/**
 * @author ASHWIN
 */

@Service
public class UserMstServiceImpl implements UserMstService {
    
    @Autowired
    UserMstRepository userMstRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author ASHWIN
     * @param userMst
     */
    @Override
    public UserMst saveUserMst(UserMst userMst) {
        return userMstRepository.save(userMst);
    }
    
    /**
     * @author ASHWIN
     * @param userId
     */
    @Override
    public UserMst getUserMstByUserId(String userId) {
        return userMstRepository.findByUserId(userId);
    }
    
    @Override
    public String userRegistration(Header header, UserMst user) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            user.setCreatedBy(header.getUserId());
            user.setCreatedDate(currentDate);
            user.setBankCode(header.getBankCode());
            userMstRepository.save(user);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author ASHWIN
     */
    @Override
    public UserMst getUserMstByBankCodeAndUserId(String bankCode, String userId) {
        return userMstRepository.findByBankCodeAndUserId(bankCode, userId);
    }
    
    @Override
    public List<UserMst> getUser(String bankCode) {
        // TODO Auto-generated method stub
    	Integer isActive=1;
        return userMstRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
    }

	@Override
	public UserMst deleteUser(Header header, UserMst user) {
		try{
			UserMst existingUser = userMstRepository.findById(user.getId()).orElse(null);
        
        Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        existingUser.setBankCode(header.getBankCode());
        existingUser.setModifiedBy(header.getUserId());
        existingUser.setModifiedDate(currentDate);
         existingUser.setIsActive(2);
        return userMstRepository.save(existingUser);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return null;

	}

	@Override
	public UserMst userLock(UserMst user) {
		try{
			UserMst existingUser = userMstRepository.findById(user.getId()).orElse(null);
        
        existingUser.setIsUserLocked(0);
        existingUser.setBadLoginCount(0);
       
        return userMstRepository.save(existingUser);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return null;

	}
    
}
