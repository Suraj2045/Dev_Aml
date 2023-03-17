package com.nellinfotech.aml.service;

import java.util.List;

import com.nellinfotech.aml.entities.CityMst;
import com.nellinfotech.aml.entities.CountryMst;
import com.nellinfotech.aml.entities.CurrencyMst;
import com.nellinfotech.aml.entities.StateMst;
import com.nellinfotech.aml.model.Header;

public interface SystemService {
    
    CountryMst saveCountry(Header header, CountryMst contryMst);
    
    List<CountryMst> getCountry(String bankCode);
    
    StateMst saveState(Header header, StateMst stateMst);
    
    List<StateMst> getState(String bankCode);
    
    CityMst saveCity(Header header, CityMst cityMst);
    
    List<CityMst> getCity(String bankCode);
    
    CountryMst updateCountry(Header header, CountryMst contryMst);
    
    String deleteCountry(CountryMst contryMst);
    
    StateMst updateState(Header header, StateMst stateMst);
    
    String deleteState(StateMst stateMst);
    
    CityMst updateCity(Header header, CityMst cityMst);
    
    String deleteState(CityMst cityMst);
    
    CurrencyMst saveCurrency(Header header, CurrencyMst currency);
    
    List<CurrencyMst> getCurrencyList(String bankCode);
    
    CurrencyMst updateCurrency(Header header, CurrencyMst currency);
    
    String deleteCurrency(CurrencyMst currency);
    
}
