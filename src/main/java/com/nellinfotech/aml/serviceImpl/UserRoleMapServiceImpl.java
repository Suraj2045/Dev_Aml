package com.nellinfotech.aml.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.entities.UserRoleMap;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.UserRoleMapRepository;
import com.nellinfotech.aml.service.UserRoleMapService;

/**
 * @author ASHWIN
 */

@Service
public class UserRoleMapServiceImpl implements UserRoleMapService {
    
    @Autowired
    UserRoleMapRepository userRoleMapRepository;
    @Autowired
    DateValidator dateValidator;
    
    /**
     * @author ASHWIN
     * @param userRoleMap
     */
    @Override
    public UserRoleMap saveUserRoleMap(Header header, UserRoleMap userRoleMap) {
        Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
        userRoleMap.setCreatedBy(header.getUserId());
        userRoleMap.setBankCode(header.getBankCode());
        userRoleMap.setCreatedDate(currentDate);
        return userRoleMapRepository.save(userRoleMap);
    }
    
    /**
     * @author ASHWIN
     * @param userId
     */
    @Override
    public UserRoleMap getUserRoleMapByUserId(String userId) {
        return userRoleMapRepository.findByUserId(userId);
    }
    
    /**
     * @author ASHWIN
     * @param userId
     * @param roleCode
     * @param brCode
     */
    @Override
    public UserRoleMap getUserRoleMapByUserIdAndRoleCodeAndBrCode(String userId, String roleCode, String brCode) {
        return userRoleMapRepository.findByUserIdAndRoleCodeAndBrCode(userId, roleCode, brCode);
    }
    
}