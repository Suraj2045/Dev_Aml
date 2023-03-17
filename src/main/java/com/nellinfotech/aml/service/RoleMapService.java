package com.nellinfotech.aml.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.nellinfotech.aml.entities.MenuDtls;
import com.nellinfotech.aml.entities.MenuRoleMap;
import com.nellinfotech.aml.model.RoleDetails;

public interface RoleMapService {
    
    MenuDtls saveMenuDtls(MenuDtls menuDtls);
    
    String saveRoleMenuMap(HttpHeaders httpHeaders, List<MenuRoleMap> menuMap);
    
    List<RoleDetails> getMenuMap(String roleCode);
    
    String updateMenuMap(MenuRoleMap menuMap);
    
    List<MenuRoleMap> getUserRoleMenus(String baseRoleCode);
    
}
