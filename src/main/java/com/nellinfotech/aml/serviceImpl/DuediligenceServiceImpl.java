package com.nellinfotech.aml.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.nellinfotech.aml.commonUtility.DateValidator;

import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.controller.DuediligenceController;
import com.nellinfotech.aml.entities.AccountFail;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.DueDiligenceMst;
import com.nellinfotech.aml.entities.HolidayMst;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.repository.BranchHolidayRepository;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.DuediligenceMstRepository;
import com.nellinfotech.aml.service.DuediligenceService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * @author TUSHAR
 */

@Service
public class DuediligenceServiceImpl implements DuediligenceService {
    
    @Autowired
    DuediligenceMstRepository duediligenceMstRepository;
    
  @Autowired
  CustomerRepository customerRepository;
    
    @Autowired
    DateValidator dateValidator;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private JavaMailSender mailSender;
    
    Logger logger = LoggerFactory.getLogger(DuediligenceServiceImpl.class); 
    
    /**
     * @author PARESH K
     * @param dueDiligenceMst
     *            return
     */
    
    @Override
	public DueDiligenceMst saveDueDiligenceMaster(Header header, DueDiligenceMst dueDiligenceMst) {
    	try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            dueDiligenceMst.setBankCode(header.getBankCode());
            dueDiligenceMst.setCreatedBy(header.getUserId());
            dueDiligenceMst.setCreatedDate(currentDate);
            return duediligenceMstRepository.save(dueDiligenceMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
		return null;
	}

	@Override
	public List<DueDiligenceMst> getDueDiligencelist(String bankhCode) {
		try {
            return duediligenceMstRepository.getDueDiligencelist(bankhCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
		return null;
	}

	@Override
	public DueDiligenceMst updateDueDiligenceMaster(Header header, DueDiligenceMst dueDiligenceMst) {
		try {
            DueDiligenceMst existingDueDiligenceMst = duediligenceMstRepository.findById(dueDiligenceMst.getId()).orElse(null);
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            existingDueDiligenceMst.setModifiedBy(header.getUserId());
            existingDueDiligenceMst.setModifiedDate(currentDate);
            existingDueDiligenceMst.setBranchCode(dueDiligenceMst.getBankCode());
            existingDueDiligenceMst.setEntityCode(dueDiligenceMst.getEntityCode());
            existingDueDiligenceMst.setFieldname(dueDiligenceMst.getFieldname());
            existingDueDiligenceMst.setBankCode(existingDueDiligenceMst.getBankCode());
            return duediligenceMstRepository.save(existingDueDiligenceMst);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
		return null;
	}

	@Override
	public String deleteDueDiligenceMaster(Long id) {
		try {
			DueDiligenceMst existingDueDiligenceMst = duediligenceMstRepository.findById(id).orElse(null);
			existingDueDiligenceMst.setIsActive(2);
			duediligenceMstRepository.save(existingDueDiligenceMst);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
		return null;
	}

			 @Override
		    public ResponseEntity<Resource> runDueDiligenceJob(String bankCode) {
				 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
			     String currentDateTime = dateFormatter.format(new Date());
			    
		        try {
		            String path = env.getProperty("Root_Path");//"C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps";
		            List<Customer> customerList = new ArrayList<Customer>();
		            Customer customerHeader = new Customer();
		            customerHeader.setCustCode("Customer Code");
		            customerHeader.setCustomerFName("Customer FName");
		            customerHeader.setCustomerLName("Customer Lname");
		            customerHeader.setBankCode("Bank Code");
		            customerHeader.setAadhaarNo("Adhaar No");
		            customerHeader.setPanNo("PAN");
		            customerHeader.setMobileNo("Mobile No");
		            customerHeader.setCustomerGender("Gender");
		            
		            customerList = customerRepository.getNonComplientRecords(bankCode);//Customer.findByStatus(status);
		            XSSFWorkbook workbook = new XSSFWorkbook();
		            String dir = env.getProperty("DueDiligenceFilePath");//"C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/Customer";
		            File file = new File(dir);
		            if (!file.exists()) {
		                file.mkdir();
		                logger.info("Created New Directory");
		            } 
		            //Create a blank sheet
		            XSSFSheet sheet = workbook.createSheet(" NonComplientRecords ");
		            //Create row object
		            //  XSSFRow row;  
		            int rowCount=0;
		            for (int i = 0; i < customerList.size(); i++) {
		                if (i == 0) {
		                    XSSFRow row = sheet.createRow(++rowCount);
		                    writeBook(customerHeader, row);
		                } else {
		                	
		                    XSSFRow row = sheet.createRow(++rowCount);
		                    writeBook(customerList.get(i), row);
		                }
		            }
		            FileOutputStream out = new FileOutputStream(new File(dir + "/DueDiligenceMaster-"+currentDateTime+".xlsx"));
		            workbook.write(out);
		            out.close();
		            String fileName="DueDiligenceMaster-"+currentDateTime+".xlsx";
		            File file1 = new File(dir + "/"+fileName);
		            
		            HttpHeaders header = new HttpHeaders();
		            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=account.xlsx");
		            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		            header.add("Pragma", "no-cache");
		            header.add("Expires", "0");
		            
		            Path path1 = Paths.get(file1.getAbsolutePath());
		            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path1));
		            
		            //String filePath=path1.toString();
			           
		           // sendDueDiligenceEmail(filePath, fileName);
		            
		            return ResponseEntity.ok().headers(header).contentLength(file1.length())
		                    .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		            
		            
		        } catch (Exception e) {
		            logger.error("Excel File Export Failed"+e);
		            e.printStackTrace();
		        }
		        return null;
		    }
		    
		    private void writeBook(Customer customer, Row row) {
		        Cell cell = row.createCell(0);
		        cell.setCellValue(customer.getCustCode());
		        
		        cell = row.createCell(1);
		        cell.setCellValue(customer.getCustomerFName());
		        
		        cell = row.createCell(2);
		        cell.setCellValue(customer.getCustomerLName());
		        
		        cell = row.createCell(3);
		        cell.setCellValue(customer.getCustomerLName());
		        
		        cell = row.createCell(4);
		        cell.setCellValue(customer.getBankCode());
		        
		        cell = row.createCell(5);
		        cell.setCellValue(customer.getAadhaarNo());
		        
		        cell = row.createCell(6);
		        cell.setCellValue(customer.getPanNo());
		        
		        cell = row.createCell(7);
		        cell.setCellValue(customer.getMobileNo());
		        
		        cell = row.createCell(8);
		        cell.setCellValue(customer.getCustomerGender());
		        
		        
		    }
		    
		    //-------------
		    public void deleteFolder(File file) {
		        for (File subFile : file.listFiles()) {
		            if (subFile.isDirectory()) {
		                deleteFolder(subFile);
		            } else {
		                subFile.delete();
		            }
		        }
		        file.delete();
		    }
		    
		    public void sendDueDiligenceEmail(String filePath, String fileName) 
		    {
		    	 String toEmail=env.getProperty("DueDiligenceToEmail");
			     String subject=env.getProperty("DueDiligenceSubject");
		        MimeMessagePreparator preparator = new MimeMessagePreparator() 
		        {
		            public void prepare(MimeMessage mimeMessage) throws Exception 
		            {
		                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		                mimeMessage.setFrom(new InternetAddress("paresh.nell@gmail.com"));
		                mimeMessage.setSubject(subject);
		                mimeMessage.setText("PFA Due Diligence Report "+fileName);
		                 
		                FileSystemResource file = new FileSystemResource(new File(filePath));
		                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		                helper.addAttachment(fileName, file);
		               
		            }
		        };
		         
		        try {
		        	mailSender.send(preparator);
		        }
		        catch (MailException ex) {
		            // simply log it and go on...
		            System.err.println(ex.getMessage());
		        }
		    }
 
 
   
}