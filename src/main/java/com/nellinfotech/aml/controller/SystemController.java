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

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.CityMst;
import com.nellinfotech.aml.entities.CountryMst;
import com.nellinfotech.aml.entities.CurrencyMst;
import com.nellinfotech.aml.entities.StateMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.SystemService;

/**
 * @author Tushar
 */
@CrossOrigin(origins = "*")
@RestController
public class SystemController {
    
    @Autowired
    private SystemService systemService;
    
    Logger logger = LoggerFactory.getLogger(SystemController.class);
    
    //----------------------------------------Country---------------------------------
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/saveCountry")
    public CountryMst saveCountry(@RequestHeader HttpHeaders httpHeaders, @RequestBody CountryMst contryMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + contryMst.getCountryName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.saveCountry(header, contryMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @return
     */
    @GetMapping("/getCountry")
    public List<CountryMst> getCountry(@RequestParam String bankCode) {
        try {
            logger.info(" inside SystemController - getCountry-");
            return systemService.getCountry(bankCode);
        } catch (Exception e) {
            logger.error("SystemController  getCountry" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/updateCountry")
    public CountryMst updateCountry(@RequestHeader HttpHeaders httpHeaders, @RequestBody CountryMst contryMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + contryMst.getCountryName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.updateCountry(header, contryMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/deleteCountry")
    public String deleteCountry(@RequestBody CountryMst contryMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + contryMst.getCountryName());
            return systemService.deleteCountry(contryMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    //----------------------------------------State---------------------------------
    
    /**
     * @author TUSHAR
     * @param stateMst
     * @return
     */
    @PostMapping("/saveState")
    public StateMst saveState(@RequestHeader HttpHeaders httpHeaders, @RequestBody StateMst stateMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + stateMst.getStateName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.saveState(header, stateMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveState" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @return
     */
    @GetMapping("/getState")
    public List<StateMst> getState(@RequestParam String bankCode) {
        try {
            logger.info(" inside SystemController - getState-");
            return systemService.getState(bankCode);
        } catch (Exception e) {
            logger.error("SystemController  getState" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/updateState")
    public StateMst updateState(@RequestHeader HttpHeaders httpHeaders, @RequestBody StateMst stateMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + stateMst.getStateName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.updateState(header, stateMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/deleteState")
    public String deleteState(@RequestBody StateMst stateMst) {
        try {
            logger.info(" inside SystemController - saveCountry-");
            return systemService.deleteState(stateMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    //----------------------------------------City---------------------------------
    
    /**
     * @author TUSHAR
     * @param cityMst
     * @return
     */
    @PostMapping("/saveCity")
    public CityMst saveCity(@RequestHeader HttpHeaders httpHeaders, @RequestBody CityMst cityMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + cityMst.getCityName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.saveCity(header, cityMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCity" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @return
     */
    @GetMapping("/getCity")
    public List<CityMst> getCity(@RequestParam String bankCode) {
        try {
            logger.info(" inside SystemController - getCity-");
            return systemService.getCity(bankCode);
        } catch (Exception e) {
            logger.error("SystemController  getCity" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/updateCity")
    public CityMst updateCity(@RequestHeader HttpHeaders httpHeaders, @RequestBody CityMst cityMst) {
        try {
            logger.info(" inside SystemController - saveCountry-" + cityMst.getCityName());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.updateCity(header, cityMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param contryMst
     * @return
     */
    @PostMapping("/deleteCity")
    public String deleteState(@RequestBody CityMst cityMst) {
        try {
            logger.info(" inside SystemController - saveCountry-");
            return systemService.deleteState(cityMst);
            
        } catch (Exception e) {
            logger.error("SystemController  saveCountry" + e);
        }
        return null;
    }
    
    //----------------------------------------Currency---------------------------------
    
    /**
     * @author TUSHAR
     * @param currency
     * @return
     */
    @PostMapping("/saveCurrency")
    public CurrencyMst saveCurency(@RequestHeader HttpHeaders httpHeaders, @RequestBody CurrencyMst currency) {
        try {
            logger.info(" inside SystemController - saveCurrency-" + currency.getCurrencyCode());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.saveCurrency(header, currency);
        } catch (Exception e) {
            logger.error("SystemController  saveCurrency" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @return
     */
    @GetMapping("/getCurrency")
    public List<CurrencyMst> getCurrencyList(@RequestParam String bankCode) {
        try {
            logger.info(" inside SystemController - getCurrency-" + bankCode);
            return systemService.getCurrencyList(bankCode);
        } catch (Exception e) {
            logger.error("SystemController  getCurrency" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param currency
     * @return
     */
    @PostMapping("/updateCurrency")
    public CurrencyMst updateCurrency(@RequestHeader HttpHeaders httpHeaders, @RequestBody CurrencyMst currency) {
        try {
            logger.info(" inside SystemController - updateCurrency-" + currency.getCurrencyCode());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return systemService.updateCurrency(header, currency);
        } catch (Exception e) {
            logger.error("SystemController  updateCurrency" + e);
        }
        return null;
    }
    
    /**
     * @author TUSHAR
     * @param currency
     * @return
     */
    @PostMapping("/deleteCurrency")
    public String deleteCurrency(@RequestBody CurrencyMst currency) {
        try {
            logger.info(" inside SystemController - deleteCurrency-" + currency.getCurrencyCode());
            return systemService.deleteCurrency(currency);
        } catch (Exception e) {
            logger.error("SystemController  deleteCurrency" + e);
        }
        return null;
    }
}
