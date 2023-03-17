package com.nellinfotech.aml.service;

import com.nellinfotech.aml.entities.UserRoleMap;
import com.nellinfotech.aml.model.Header;

/**
 * @author ASHWIN
 */
public interface UserRoleMapService {
    
    public UserRoleMap saveUserRoleMap(Header header, UserRoleMap userRoleMap);
    
    public UserRoleMap getUserRoleMapByUserId(String userId);
    
    public UserRoleMap getUserRoleMapByUserIdAndRoleCodeAndBrCode(String userId, String roleCode, String brCode);
    
}