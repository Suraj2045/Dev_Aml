package com.nellinfotech.aml.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.entities.RoleMenuMap;
import com.nellinfotech.aml.repository.RoleMenuMapRepository;
import com.nellinfotech.aml.service.RoleMenuMapService;

/**
 * @author ASHWIN
 */

@Service
public class RoleMenuMapServiceImpl implements RoleMenuMapService {
    
    @Autowired
    RoleMenuMapRepository roleMenuMapRepository;
    
    /**
     * @author ASHWIN
     * @param roleMenuMap
     */
    @Override
    public RoleMenuMap saveRoleMenuMap(RoleMenuMap roleMenuMap) {
        return roleMenuMapRepository.save(roleMenuMap);
    }
    
    /**
     * @author ASHWIN
     * @param roleCode
     */
    @Override
    public RoleMenuMap getRoleMenuMapByRoleCode(String roleCode) {
        return roleMenuMapRepository.findByRoleCode(roleCode);
    }
    
}