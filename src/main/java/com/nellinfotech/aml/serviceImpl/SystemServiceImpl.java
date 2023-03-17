package com.nellinfotech.aml.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.CityMst;
import com.nellinfotech.aml.entities.CountryMst;
import com.nellinfotech.aml.entities.CurrencyMst;
import com.nellinfotech.aml.entities.StateMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.CityRepository;
import com.nellinfotech.aml.repository.CountryRepository;
import com.nellinfotech.aml.repository.CurrencyRepository;
import com.nellinfotech.aml.repository.StateRepository;
import com.nellinfotech.aml.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
    
    @Autowired
    CountryRepository countryRepository;
    
    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    CityRepository cityRepository;
    
    @Autowired
    CurrencyRepository currencyRpository;
    
    @Autowired
    DateValidator dateValidator;
    
    Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);
    
    @Override
    public CountryMst saveCountry(Header header, CountryMst countryMst) {
        try {
            logger.info(" inside SystemServiceImpl - saveCountry-" + countryMst.getCountryName());
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            countryMst.setCreatedBy(header.getUserId());
            countryMst.setCreatedDate(currentDate);
            countryMst.setBankCode(header.getBankCode());
            return countryRepository.save(countryMst);
        } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - saveCountry" + e);
        }
        return null;
    }
    
    @Override
    public List<CountryMst> getCountry(String bankCode) {
        try {
            logger.info(" inside SystemServiceImpl - getCountry-");
            Integer isActive=1;
            return countryRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
            
        } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - getCountry" + e);
        }
        return null;
    }
    
    @Override
    public CountryMst updateCountry(Header header, CountryMst contryMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            
            CountryMst existingCountry = countryRepository.findById(contryMst.getId()).orElse(null);
            existingCountry.setCountryCode(contryMst.getCountryCode());
            existingCountry.setCountryName(contryMst.getCountryName());
            existingCountry.setModifiedBy(header.getUserId());
            existingCountry.setModifiedDate(currentDate);
            return countryRepository.save(existingCountry);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String deleteCountry(CountryMst contryMst) {
        try {
            CountryMst existingCountry = countryRepository.findById(contryMst.getId()).orElse(null);
            existingCountry.setIsActive(0);
            countryRepository.save(existingCountry);
            return ResponseStatus.SUCCESS;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public StateMst saveState(Header header, StateMst stateMst) {
        
        try {
            
            logger.info(" inside SystemServiceImpl - saveState-" + stateMst.getStateName());
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            stateMst.setCreatedBy(header.getUserId());
            stateMst.setCreatedDate(currentDate);
            stateMst.setBankCode(header.getBankCode());
            return stateRepository.save(stateMst);
        } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - saveState" + e);
        }
        return null;
    }
    
    @Override
    public List<StateMst> getState(String bankCode) {
        try {
            logger.info(" inside SystemServiceImpl - getState-");
            Integer isActive=1;
            return stateRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
            
        } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - getState" + e);
        }
        return null;
    }
    
    @Override
    public StateMst updateState(Header header, StateMst stateMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            StateMst existingState = stateRepository.findById(stateMst.getId()).orElse(null);
            existingState.setStateCode(stateMst.getStateCode());
            existingState.setStateName(stateMst.getStateName());
            existingState.setCountryCode(stateMst.getCountryCode());
            existingState.setBankCode(header.getBankCode());
            existingState.setCreatedBy(header.getUserId());
            existingState.setCreatedDate(currentDate);
            return stateRepository.save(existingState);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String deleteState(StateMst stateMst) {
        try {
            StateMst existingState = stateRepository.findById(stateMst.getId()).orElse(null);
            existingState.setIsActive(0);
            stateRepository.save(existingState);
            return ResponseStatus.SUCCESS;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public CityMst saveCity(Header header, CityMst cityMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            logger.info(" inside SystemServiceImpl - saveCity-" + cityMst.getCityName());
            cityMst.setCreatedBy(header.getUserId());
            cityMst.setBankCode(header.getBankCode());
            cityMst.setCreatedDate(currentDate);
            return cityRepository.save(cityMst);
        } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - saveCity" + e);
        }
        return null;
    }
    
    @Override
    public List<CityMst> getCity(String bankCode) {
        try {
            logger.info(" inside SystemServiceImpl - getState-");
            Integer isActive=1;
            return cityRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
            } catch (Exception e) {
            logger.info(" inside SystemServiceImpl - getCity" + e);
        }
        
        return null;
    }
    
    @Override
    public CityMst updateCity(Header header, CityMst cityMst) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            CityMst existingCity = cityRepository.findById(cityMst.getId()).orElse(null);
            existingCity.setCountryCode(cityMst.getCountryCode());
            existingCity.setStateCode(cityMst.getStateCode());
            existingCity.setCityCode(cityMst.getCityCode());
            existingCity.setCityName(cityMst.getCityName());
            existingCity.setModifiedBy(header.getUserId());
            existingCity.setModifiedDate(currentDate);
            return cityRepository.save(existingCity);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String deleteState(CityMst cityMst) {
        try {
            CityMst existingCity = cityRepository.findById(cityMst.getId()).orElse(null);
            existingCity.setIsActive(0);
            cityRepository.save(existingCity);
            return ResponseStatus.SUCCESS;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public CurrencyMst saveCurrency(Header header, CurrencyMst currency) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            currency.setBankCode(header.getBankCode());
            currency.setCreatedBy(header.getUserId());
            currency.setCreatedDate(currentDate);
            return currencyRpository.save(currency);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<CurrencyMst> getCurrencyList(String bankCode) {
        try {
        	
        	Integer isActive=1;
            return currencyRpository.findAllByIsActiveAndBankCode(isActive,bankCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public CurrencyMst updateCurrency(Header header, CurrencyMst currency) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            CurrencyMst existingcCurrencyMst = currencyRpository.findById(currency.getId()).orElse(null);
            existingcCurrencyMst.setCurrencyCode(currency.getCurrencyCode());
            existingcCurrencyMst.setCurrencyName(currency.getCurrencyName());
            existingcCurrencyMst.setDecimalUnit(currency.getDecimalUnit());
            existingcCurrencyMst.setMajorUnit(currency.getMajorUnit());
            existingcCurrencyMst.setMinorUnit(currency.getMinorUnit());
            existingcCurrencyMst.setModifiedBy(header.getUserId());
            existingcCurrencyMst.setModifiedDate(currentDate);
            return currencyRpository.save(existingcCurrencyMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String deleteCurrency(CurrencyMst currency) {
        try {
            CurrencyMst existingcCurrencyMst = currencyRpository.findById(currency.getId()).orElse(null);
            existingcCurrencyMst.setIsActive(0);
            currencyRpository.save(existingcCurrencyMst);
            return ResponseStatus.SUCCESS;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
