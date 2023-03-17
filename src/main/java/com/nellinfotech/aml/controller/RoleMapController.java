package com.nellinfotech.aml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.entities.MenuDtls;
import com.nellinfotech.aml.entities.MenuRoleMap;
import com.nellinfotech.aml.model.RoleDetails;
import com.nellinfotech.aml.service.RoleMapService;

@CrossOrigin(origins = "*")
@RestController
public class RoleMapController {
    
    @Autowired
    private RoleMapService roleMapService;
    
    Logger logger = LoggerFactory.getLogger(RoleMapController.class);
    
    @PostMapping("/saveMenuDtls")
    public MenuDtls saveMenuDtls(@RequestBody MenuDtls menuDtls) {
        try {
            logger.info(" inside RoleMapController - saveMenuDtls-" + menuDtls.getMenuCode());
            return roleMapService.saveMenuDtls(menuDtls);
        } catch (Exception e) {
            logger.error(" inside RoleMapController - saveMenuDtls-" + e);
        }
        return null;
        
    }
    
    @PostMapping("/saveRoleMenuMap")
    public String saveRoleMenuMap(@RequestHeader HttpHeaders httpHeaders, @RequestBody List<MenuRoleMap> menuRole) {
        try {
            logger.info(" inside RoleMapController - saveRoleMenuMap-");
            return roleMapService.saveRoleMenuMap(httpHeaders, menuRole);
        } catch (Exception e) {
            logger.error(" inside RoleMapController - saveRoleMenuMap-" + e);
        }
        
        return null;
    }
    
    @GetMapping("/getMenuMap")
    public List<RoleDetails> getMenuMap(@RequestParam String roleCode) {
        try {
            logger.info(" inside RoleMapController - getMenuMap-" + roleCode);
            return roleMapService.getMenuMap(roleCode);
        } catch (Exception e) {
            logger.error(" inside RoleMapController - getMenuMap-" + e);
        }
        return null;
    }
    
    @PostMapping("/updateMenuMap")
    public String updateMenuMap(@RequestBody MenuRoleMap menuMap) {
        try {
            logger.info(" inside RoleMapController - updateMenuMap-" + menuMap.getMenuCode());
            return roleMapService.updateMenuMap(menuMap);
        } catch (Exception e) {
            logger.error(" inside RoleMapController - updateMenuMap-" + e);
        }
        return null;
    }
}
