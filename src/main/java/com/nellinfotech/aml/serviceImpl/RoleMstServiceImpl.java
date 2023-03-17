package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.RoleMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.RoleMstRepository;
import com.nellinfotech.aml.service.RoleMstService;

/**
 * @author ASHWIN
 */

@Service
public class RoleMstServiceImpl implements RoleMstService {
    
    @Autowired
    RoleMstRepository roleMstRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author ASHWIN
     * @param roleMst
     */
    @Override
    public RoleMst saveRoleMst(Header header, RoleMst roleMst) {
        Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        roleMst.setCreatedBy(header.getUserId());
        roleMst.setCreatedDate(currentDate);
        roleMst.setBankCode(header.getBankCode());
        return roleMstRepository.save(roleMst);
    }
    
    /**
     * @author ASHWIN
     * @param roleCode
     */
    @Override
    public RoleMst getRoleMstByRoleCode(String roleCode) {
        return roleMstRepository.findByRoleCode(roleCode);
    }
    
    @Override
    public List<RoleMst> getRoleMst(String bankCode) {
        
    	Integer isActive=1;
        return roleMstRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
    }

	@Override
	public RoleMst deleteRole(Header header, RoleMst role) {
	try {
		RoleMst existingRole = roleMstRepository.findById(role.getId()).orElse(null);
        
        Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        existingRole.setBankCode(header.getBankCode());
        existingRole.setModifiedBy(header.getUserId());
        existingRole.setModifiedDate(currentDate);
        existingRole.setRoleCode(role.getRoleCode());
        existingRole.setRoleDesc(role.getRoleDesc());
        existingRole.setIsActive(2);
        return roleMstRepository.save(existingRole);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return null;
	}
	
	
}