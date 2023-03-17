package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.RoleMst;
import com.nellinfotech.aml.model.Header;

/**
 * @author ASHWIN
 */
public interface RoleMstService {
    
    public RoleMst saveRoleMst(Header header, RoleMst roleMst);
    
    public RoleMst getRoleMstByRoleCode(String roleCode);
    
    public List<RoleMst> getRoleMst(String bankCode);

	public RoleMst deleteRole(Header header, RoleMst role);
    
}