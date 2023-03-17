package com.nellinfotech.aml.service;

import com.nellinfotech.aml.entities.MenuMst;

/**
 * @author ASHWIN
 */
public interface MenuMstService {
    
    public MenuMst saveMenuMst(MenuMst menuMst);
    
    public MenuMst getMenuMstByMenuCode(String menuCode);
    
}