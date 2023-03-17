package com.nellinfotech.aml.commonUtility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DateValidator {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
    public Date getStrToDate(String date) {
        
        Date convertDate = null;
        try {
            convertDate = dateFormat.parse(date);
        } catch (Exception e) {
            
        }
        return convertDate;
    }
    
}
