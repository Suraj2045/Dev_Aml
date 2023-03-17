package com.nellinfotech.aml.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.entities.MenuMst;
import com.nellinfotech.aml.repository.MenuMstRepository;
import com.nellinfotech.aml.service.MenuMstService;

/**
 * @author ASHWIN
 */

@Service
public class MenuMstServiceImpl implements MenuMstService {
    
    @Autowired
    MenuMstRepository menuMstRepository;
    
    /**
     * @author ASHWIN
     * @param menuMst
     */
    @Override
    public MenuMst saveMenuMst(MenuMst menuMst) {
        return menuMstRepository.save(menuMst);
    }
    
    /**
     * @author ASHWIN
     * @param menuCode
     */
    @Override
    public MenuMst getMenuMstByMenuCode(String menuCode) {
        return menuMstRepository.findByMenuCode(menuCode);
    }
    
}