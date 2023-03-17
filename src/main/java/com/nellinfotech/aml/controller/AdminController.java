package com.nellinfotech.aml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.MenuMst;
import com.nellinfotech.aml.entities.RoleMenuMap;
import com.nellinfotech.aml.entities.RoleMst;
import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.entities.UserRoleMap;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.MenuMstService;
import com.nellinfotech.aml.service.RoleMenuMapService;
import com.nellinfotech.aml.service.RoleMstService;
import com.nellinfotech.aml.service.UserMstService;
import com.nellinfotech.aml.service.UserRoleMapService;

/**
 * @author ASHWIN
 */
@CrossOrigin(origins = "*")
@RestController
public class AdminController {
    
    @Autowired
    RoleMstService roleMstService;
    
    @Autowired
    RoleMenuMapService roleMenuMapService;
    
    @Autowired
    UserMstService userMstService;
    
    @Autowired
    UserRoleMapService userRoleMapService;
    
    @Autowired
    MenuMstService menuMstService;
    
    /**
     * @author ASHWIN
     * @param roleMst
     * @return
     */
    @PostMapping("/saveRoleMst")
    public RoleMst saveRoleMst(@RequestHeader HttpHeaders httpHeaders, @RequestBody RoleMst roleMst) {
        Header header = HeaderValidator.headerConversion(httpHeaders);
        return roleMstService.saveRoleMst(header, roleMst);
    }
    
    /**
     * @author ASHWIN
     * @param roleCode
     * @return
     */
    @GetMapping("/getRoleMstByRoleCode")
    public RoleMst getRoleMstByRoleCode(@RequestParam String roleCode) {
        return roleMstService.getRoleMstByRoleCode(roleCode);
        
    }
    
    @GetMapping("/getRoleMst")
    public List<RoleMst> getRoleMst(@RequestParam String bankCode) {
        return roleMstService.getRoleMst(bankCode);
        
    }
    
    @PostMapping("/deleteRole")
    public RoleMst deleteRole(@RequestHeader HttpHeaders httpHeaders, @RequestBody RoleMst role) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return roleMstService.deleteRole(header, role);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    /**
     * @author ASHWIN
     * @param roleMenuMap
     * @return
     */
    //    @PostMapping("/saveRoleMenuMap")
    //    public RoleMenuMap saveRoleMenuMap(@RequestBody RoleMenuMap roleMenuMap) {
    //        return roleMenuMapService.saveRoleMenuMap(roleMenuMap);
    //    }
    
    /**
     * @author ASHWIN
     * @param roleCode
     * @return
     */
    @GetMapping("/getRoleMenuMapByRoleCode")
    public RoleMenuMap getRoleMenuMapByRoleCode(@RequestParam String roleCode) {
        return roleMenuMapService.getRoleMenuMapByRoleCode(roleCode);
        
    }
    
    /**
     * @author ASHWIN
     * @param userMst
     * @return
     */
    @PostMapping("/saveUserMst")
    public UserMst saveUserMst(@RequestBody UserMst userMst) {
        return userMstService.saveUserMst(userMst);
    }
    
    /**
     * @author ASHWIN
     * @param userId
     * @return
     */
    @GetMapping("/getUserMstByUserId")
    public UserMst getUserMstByUserId(@RequestParam String userId) {
        return userMstService.getUserMstByUserId(userId);
        
    }
    
    /**
     * @author ASHWIN
     * @param userMst
     * @return
     */
    @PostMapping("/saveUserRoleMap")
    public UserRoleMap saveUserRoleMap(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserRoleMap userRoleMap) {
        Header header = HeaderValidator.headerConversion(httpHeaders);
        return userRoleMapService.saveUserRoleMap(header, userRoleMap);
    }
    
    /**
     * @author ASHWIN
     * @param userId
     * @return
     */
    @GetMapping("/getUserRoleMapByUserId")
    public UserRoleMap getUserRoleMapByUserId(@RequestParam String userId) {
        return userRoleMapService.getUserRoleMapByUserId(userId);
        
    }
    
    /**
     * @author ASHWIN
     * @param userId
     * @param roleCode
     * @param brCode
     * @return
     */
    @GetMapping("/getUserRoleMapByUserIdAndRoleCodeAndBrCode")
    public UserRoleMap getUserRoleMapByUserIdAndRoleCodeAndBrCode(@RequestParam String userId,
            @RequestParam String roleCode, @RequestParam String brCode) {
        return userRoleMapService.getUserRoleMapByUserIdAndRoleCodeAndBrCode(userId, roleCode, brCode);
        
    }
    
    /**
     * @author ASHWIN
     * @param menuMst
     * @return
     */
    @PostMapping("/saveMenuMst")
    public MenuMst saveMenuMst(@RequestBody MenuMst menuMst) {
        return menuMstService.saveMenuMst(menuMst);
    }
    
    /**
     * @author ASHWIN
     * @param roleCode
     * @return
     */
    @GetMapping("/getMenuMstByMenuCode")
    public MenuMst getMenuMstByMenuCode(@RequestParam String menuCode) {
        return menuMstService.getMenuMstByMenuCode(menuCode);
        
    }
    
    /**
     * @author TUSHAR
     * @param user
     * @return
     */
    @PostMapping("/userRegistration")
    public String userRegistration(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserMst user) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return userMstService.userRegistration(header, user);
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @PostMapping("/deleteUser")
    public UserMst deleteUser(@RequestHeader HttpHeaders httpHeaders, @RequestBody UserMst user) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return userMstService.deleteUser(header, user);
        } catch (Exception e) {
            
        }
        return null;
    }
    
    @GetMapping("/userList")
    public List<UserMst> getUser(@RequestParam String bankCode) {
        try {
            return userMstService.getUser(bankCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}