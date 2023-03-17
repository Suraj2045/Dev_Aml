package com.nellinfotech.aml.service;

import com.nellinfotech.aml.entities.RoleMenuMap;

/**
 * @author ASHWIN
 */
public interface RoleMenuMapService {
    
    public RoleMenuMap saveRoleMenuMap(RoleMenuMap roleMenuMap);
    
    public RoleMenuMap getRoleMenuMapByRoleCode(String roleCode);
    
}