package com.nellinfotech.aml.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.MenuDtls;
import com.nellinfotech.aml.entities.MenuMst;
import com.nellinfotech.aml.entities.MenuRoleMap;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.model.RoleDetails;
import com.nellinfotech.aml.repository.MenuMstRepository;
import com.nellinfotech.aml.repository.MenuRoleMapReposritory;
import com.nellinfotech.aml.repository.RoleMapRepository;
import com.nellinfotech.aml.service.RoleMapService;

@Service
public class RoleMapServicelmp implements RoleMapService {
    @Autowired
    RoleMapRepository roleMapRepository;
    
    @Autowired
    MenuRoleMapReposritory menuRoleMapRepository;
    
    @Autowired
    MenuMstRepository menuMstRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    @Override
    public MenuDtls saveMenuDtls(MenuDtls menuDtls) {
        try {
            return roleMapRepository.save(menuDtls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String saveRoleMenuMap(HttpHeaders httpHeaders, List<MenuRoleMap> MenuMap) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            for (MenuRoleMap menuRoleMap : MenuMap) {
                menuRoleMap.setCreatedBy(header.getUserId());
                menuRoleMap.setCreatedDate(currentDate);
                menuRoleMap.setBankCode(header.getBankCode());
                menuRoleMapRepository.save(menuRoleMap);
            }
            //menuRoleMapRepository.saveAll(MenuMap);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<RoleDetails> getMenuMap(String roleCode) {
        try {
            List<MenuMst> menuMst = menuMstRepository.findAll();
            List<MenuRoleMap> menuRoleMapList = menuRoleMapRepository.findByRoleCode(roleCode);
            List<RoleDetails> roleDetailsList = new ArrayList<RoleDetails>();
            for (MenuMst menuDetls : menuMst) {
                RoleDetails roleDetails = new RoleDetails();
                int checkFlag = 0;
                if (menuRoleMapList.size() > 0) {
                    for (MenuRoleMap menuRoleMap : menuRoleMapList) {
                        
                        if (menuDetls.getMenuCode().contentEquals(menuRoleMap.getMenuCode())) {
                            
                            roleDetails.setMenuCode(menuDetls.getMenuCode());
                            roleDetails.setMenuName(menuDetls.getMenuDesc());
                            roleDetails.setMenuDesc(menuDetls.getMenuDesc());
                            roleDetails.setMenuURL(menuDetls.getMenuURL());
                            roleDetails.setId(menuRoleMap.getId());
                            roleDetails.setIsVisible("1");
                            roleDetails.setRoleCode(menuRoleMap.getRoleCode());
                            checkFlag = checkFlag + 1;
                            roleDetailsList.add(roleDetails);
                        }
                        
                    }
                    if (checkFlag != 1) {
                        roleDetails.setMenuCode(menuDetls.getMenuCode());
                        roleDetails.setMenuName(menuDetls.getMenuDesc());
                        roleDetails.setMenuDesc(menuDetls.getMenuDesc());
                        roleDetails.setMenuURL(menuDetls.getMenuURL());
                        roleDetails.setMenuURL(menuDetls.getMenuURL());
                        roleDetails.setIsVisible("0");
                        roleDetails.setRoleCode("0");
                        roleDetailsList.add(roleDetails);
                    }
                } else {
                    roleDetails.setMenuCode(menuDetls.getMenuCode());
                    roleDetails.setMenuName(menuDetls.getMenuDesc());
                    roleDetails.setMenuDesc(menuDetls.getMenuDesc());
                    roleDetails.setMenuURL(menuDetls.getMenuURL());
                    roleDetails.setMenuURL(menuDetls.getMenuURL());
                    roleDetails.setIsVisible("0");
                    roleDetails.setRoleCode("0");
                    roleDetailsList.add(roleDetails);
                }
            }
            return roleDetailsList;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String updateMenuMap(MenuRoleMap menuMap) {
        try {
            menuRoleMapRepository.deleteById(menuMap.getId());
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<MenuRoleMap> getUserRoleMenus(String baseRoleCode) {
        try {
            return menuRoleMapRepository.findByRoleCode(baseRoleCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
