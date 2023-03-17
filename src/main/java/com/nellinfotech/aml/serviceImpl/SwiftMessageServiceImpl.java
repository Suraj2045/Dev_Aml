package com.nellinfotech.aml.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.SwiftMessageParser.MT103DataParser;
import com.nellinfotech.aml.entities.Alert;
import com.nellinfotech.aml.entities.AltAddress;
import com.nellinfotech.aml.entities.Customer;
import com.nellinfotech.aml.entities.OfacAddress;
import com.nellinfotech.aml.entities.OfacComment;
import com.nellinfotech.aml.entities.SdnDetails;
import com.nellinfotech.aml.entities.SwiftMsg;
import com.nellinfotech.aml.repository.AlertRepository;
import com.nellinfotech.aml.repository.AltAddressRepository;
import com.nellinfotech.aml.repository.CustomerRepository;
import com.nellinfotech.aml.repository.OfacAddressRepository;
import com.nellinfotech.aml.repository.OfacCommentSolrRepository;
import com.nellinfotech.aml.repository.OfacSdnRepository;
import com.nellinfotech.aml.repository.SwiftRepository;
import com.nellinfotech.aml.service.SwiftMessageService;
import com.opencsv.CSVReader;
@Service
public class SwiftMessageServiceImpl implements SwiftMessageService {
    
	 @Autowired
	    SpringSecurityConfig securityConfig;
	    @Autowired
	    OfacCommentSolrRepository ofacCommentrepository;
	    @Autowired
	    OfacAddressRepository ofacAddressRepository;
	    @Autowired
	    AltAddressRepository altAddressRepository;
	    @Autowired
	    OfacSdnRepository ofacSdnRepository;
	    @Autowired
	    SwiftRepository swiftRepository;
	    @Autowired
	    CustomerRepository cutomerRepository;
	    @Autowired
	    SwiftMessageService swiftService;
	    @Autowired
	    AlertRepository alertRepository;
	    @Override
	    public ResponseEntity<Map<String, Object>> uploadSwiftFile(HttpHeaders httpHeaders) {
	        try {
	            BufferedReader br = null;   
	            System.out.println("===="+securityConfig.getPropertyValue("swifthome"));
	            File file = new File(securityConfig.getPropertyValue("root"));  
	           
	           // try {
	                // read in swift messages
	            	
	                File srcIn = new File(securityConfig.getPropertyValue("root").toString()+ File.separator+"In");
	                File srcOut = new File(securityConfig.getPropertyValue("root").toString()+ File.separator+"Out");
	                File dest = new File(securityConfig.getPropertyValue("swifthome").toString());
	          
       	        String message=  readINSwift(srcIn,dest);
     	    	  String message1=  readOUTSwift(srcOut,dest);

	              
//	              Thread t1 = new Thread(){
//	            	    public void run(){
//	            	        String message=  readINSwift(srcIn,dest);
//	            	        //do stuff    
//	            	    }};
//	            	Thread t2 = new Thread(){
//	            	    public void run(){
//	            	    	  String message=  readOUTSwift(srcOut,dest);
//	            	        //do stuff    
//	            	    }};
//
//	            	t1.start();
//	            	t2.start();
	            	
//	                MT103DataParser mt103=new MT103DataParser();
//	                
//	               
//	                ArrayList<SwiftMsg>swftList=new ArrayList<SwiftMsg>();
//	                for (File subFile : srcIn.listFiles()) {
//		                    System.out.println("sub files"+subFile);
//	                		  SwiftMsg swift= new SwiftMsg();
//	                		  swift= mt103.mT103MessageParsing(subFile);
//	                		  List<SdnDetails>lst=ofacSdnRepository.findByFirstNameAndAddress(swift.getNameAddress1(),swift.getNameAddress2());
//	                		  for (SdnDetails sdnList : lst) 
//	                		  {
//	                			  System.out.println(swift.getNameAddress1()+"==="+sdnList.getFirstName()+"&&"+sdnList.getAddress()+"=="+swift.getNameAddress2());
//								if(sdnList.getFirstName().contentEquals(swift.getNameAddress1()) && sdnList.getAddress().contentEquals(swift.getNameAddress2()))
//								{
//									swift.setRemark("black listed");
//								}
//	                		  }
//	                		 
//	                		 
//	                		  if(swift.getRemark().contentEquals("black listed"))
//	                		  {
//	                			  Alert alert=new Alert();
//	                			  alert.setBankCode(swift.getOrderingInstitutionBIC());
//	                			  alert.setAccountId(swift.getOrderingCustomeAccountNumber());
//	                			  alertRepository.save(alert);
//	                			  
//	                			   Path temp = Files.move (Paths.get(subFile.toString()),  
//	       	    	  	               Paths.get(dest.toString()+File.separator+"IN"+File.separator+"Violated"+File.separator+subFile.getName()));
//	       	    	                  System.out.println(dest.toString()+File.separator+"IN"+File.separator+"Violated"+File.separator+subFile.getName());
//	                		  }
//	                		  else
//	                		  {
//	                			  Path temp = Files.move (Paths.get(subFile.toString()),  
//	       	    	  	               Paths.get(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName()));
//	                			  System.out.println(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName());
//	                		  }
//	                		  swftList.add(swift);
//	                		        	   
//		            }
//	              
//	                swiftRepository.saveAll(swftList);
	           // } catch (IOException ex) {
	            //    ex.printStackTrace();
	          //  }  
	            	
	            	
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	    }
	    
	    @Override
	    public ResponseEntity<Map<String, Object>> uploadOFACcomment(HttpHeaders httpHeaders) {
	        
	        try {
	            // Create an object of filereader 
	            // class with CSV file as a parameter.
	        	 String path = securityConfig.getPropertyValue("ofaccomment");
	           // String path = "C:\\Users\\Nell laptop\\Desktop\\aml\\sdn_comments.csv";
	            FileReader filereader = new FileReader(path);
	            
	            // create csvReader object passing 
	            // file reader as a parameter 
	            CSVReader csvReader = new CSVReader(filereader);
	            String[] nextRecord;
	            ArrayList<OfacComment> commentList = new ArrayList<OfacComment>();
	            // we are going to read data line by line 
	            ofacCommentrepository.deleteAll();
	            while ((nextRecord = csvReader.readNext()) != null) {
	                OfacComment comment = new OfacComment();
	                comment.setId(Integer.parseInt(nextRecord[0].toString()));
	                comment.setCommentNumber(Integer.parseInt(nextRecord[0].toString()));
	                comment.setCommentDetails(nextRecord[1].toString());
	                commentList.add(comment);
	            }
	            ofacCommentrepository.saveAll(commentList);
	            System.out.println("upload successfully");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    @Override
	    public ResponseEntity<Map<String, Object>> uploadOFACAddress(HttpHeaders httpHeaders) {
	        try {
	            // Create an object of filereader 
	            // class with CSV file as a parameter.
	            //String path = "C:\\Users\\Nell laptop\\Desktop\\aml\\add.csv";
	            String path = securityConfig.getPropertyValue("ofacaddress");
	            FileReader filereader = new FileReader(path);
	            
	            // create csvReader object passing 
	            // file reader as a parameter 
	            CSVReader csvReader = new CSVReader(filereader);
	            String[] nextRecord;
	            ArrayList<OfacAddress> addressList = new ArrayList<OfacAddress>();
	            ofacAddressRepository.deleteAll();
	            // we are going to read data line by line 
	            while ((nextRecord = csvReader.readNext()) != null) {
	                OfacAddress address = new OfacAddress();
	                try {
	                    address.setSdnUid(Integer.parseInt(nextRecord[0].toString()));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    address.setId(Integer.parseInt(nextRecord[1].toString()));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    address.setLandmark(nextRecord[2].toString());
	                } catch (Exception e) {
	                    
	                }
	                try {
	                    address.setCity(nextRecord[3].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    address.setCountry(nextRecord[4].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                addressList.add(address);
	            }
	            ofacAddressRepository.saveAll(addressList);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    @Override
	    public ResponseEntity<Map<String, Object>> uploadOFACAltAddress(HttpHeaders httpHeaders) {
	        try {
	            // Create an object of filereader 
	            // class with CSV file as a parameter.
	           // String path = "C:\\Users\\Nell laptop\\Desktop\\aml\\alt.csv";
	            String path = securityConfig.getPropertyValue("altAddress");
	            FileReader filereader = new FileReader(path);
	            // create csvReader object passing 
	            // file reader as a parameter 
	            CSVReader csvReader = new CSVReader(filereader);
	            String[] nextRecord;
	            ArrayList<AltAddress> altAddressList = new ArrayList<AltAddress>();
	            altAddressRepository.deleteAll();
	            // we are going to read data line by line 
	            while ((nextRecord = csvReader.readNext()) != null) {
	                AltAddress altAddress = new AltAddress();
	                try {
	                    altAddress.setSdnIud(Integer.parseInt(nextRecord[0].toString()));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    
	                    altAddress.setId(Integer.parseInt(nextRecord[1].toString()));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    altAddress.setAltName(nextRecord[2].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    altAddress.setOrgName(nextRecord[3].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                altAddressList.add(altAddress);
	            }
	            altAddressRepository.saveAll(altAddressList);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    @Override
	    public ResponseEntity<Map<String, Object>> uploadOFACSdn(HttpHeaders httpHeaders) {
	        try {
	            // Create an object of filereader 
	            // class with CSV file as a parameter.
	        	
	   
	        	///
	            String path = securityConfig.getPropertyValue("sdn");
	            FileReader filereader = new FileReader(path);
	            // create csvReader object passing 
	            // file reader as a parameter 
	            CSVReader csvReader = new CSVReader(filereader);
	            String[] nextRecord;
	            ArrayList<SdnDetails> sdnList = new ArrayList<SdnDetails>();
	            // we are going to read data line by line 
	            ofacSdnRepository.deleteAll();
	            while ((nextRecord = csvReader.readNext()) != null) {
	                SdnDetails sdn = new SdnDetails();
	                try {
	                    sdn.setId(Integer.parseInt(nextRecord[0].toString()));
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    
	                    sdn.setFirstName(nextRecord[1].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    sdn.setTranType(nextRecord[2].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                try {
	                    sdn.setCountry(nextRecord[3].toString());
	                } catch (Exception e) {
	                }
	                try {
	                    sdn.setProfesion(nextRecord[4].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setState(nextRecord[5].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setAddress(nextRecord[6].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setCity(nextRecord[7].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setCol4(nextRecord[8].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setClo5(nextRecord[9].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setCol6(nextRecord[10].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                try {
	                    sdn.setCol7(nextRecord[11].toString());
	                } catch (Exception e) {
	                    // TODO: handle exception
	                }
	                
	                sdnList.add(sdn);
	            }
	            ofacSdnRepository.saveAll(sdnList);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
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
	    
	    public static void copyDirectory(File src, File target) throws IOException {
	        if (src.isDirectory()) {
	            if (!target.exists()) {
	                target.mkdir();
	            }
	            
	            String[] children = src.list();
	            for (int i = 0; i < children.length; i++) {
	                copyDirectory(new File(src, children[i]), new File(target, children[i]));
	            }
	        }
	        // if Directory exists then only files copy
	        else {
	            
	            InputStream in = new FileInputStream(src);
	            OutputStream out = new FileOutputStream(target);
	            
	            // Copy the bits from instream to outstream
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	            in.close();
	            out.close();
	            
	        }
	    }
	    
	    @Override
	    public ResponseEntity<Map<String, Object>> ofacSearchList(HttpHeaders httpHeaders, SdnDetails sdn) {
	        try {
//	        	String urlString = "http://localhost:8983/solr/ofac_sdnDetails";
//	        	HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
//	        	solr.setParser(new XMLResponseParser());
//	        	SolrQuery query = new SolrQuery();
//	        	query.set("q", "firstName:CUBA");
//	        	query.set("q", "country:CUBA");
//	        	QueryResponse response = solr.query(query);
//	        	SolrDocumentList docList = response.getResults();
	        	
	            Map<String, Object> json = new HashMap<String, Object>();
	            HttpHeaders headers = new HttpHeaders();
	            Map<String, Object> message = new HashMap<String, Object>();   
	            ResponseEntity<Map<String, Object>> result = null;
	            ArrayList<SdnDetails> sdnList = new ArrayList<SdnDetails>();
	            System.out.println(sdn.toString());
	           try {
				sdnList= ofacSdnRepository.findByFirstNameIgnoreCaseContainingOrCountryIgnoreCaseContainingOrCityIgnoreCaseContainingOrStateIgnoreCaseContainingOrTranTypeIgnoreCaseContaining(sdn.getFirstName(),sdn.getCountry(),sdn.getCity(),sdn.getState(),sdn.getAddress(),sdn.getTranType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            json.put("sdnList", sdnList);
	            headers.add("Content-Type", "application/json; charset=UTF-8");
	            headers.add("X-Fsl-Location", "/");
	            headers.add("X-Fsl-Response-Code", "200(ok)");
	            result = (new ResponseEntity<Map<String, Object>>(json, headers, HttpStatus.OK));
	            return result;
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	        return null;
	    }

		@Override
		public List<SdnDetails> findByFirstName(String string) {
			// TODO Auto-generated method stub
			return ofacSdnRepository.findByFirstName(string);
		}

		
		@Override
		public ResponseEntity<Map<String, Object>> customerReverseScan() {
			try
			{
				 Set<Customer> CustomerSet = new HashSet<Customer>();
				List<Customer> customerList=new ArrayList<Customer>();
				System.out.println("1------");
				customerList=cutomerRepository.findAll();
				System.out.println("2------");
				ArrayList<SdnDetails> sdnList = new ArrayList<SdnDetails>();
				List<SdnDetails> documents = new ArrayList<>();
			       // iterate all documents and add it to list
			       for (SdnDetails doc : this.ofacSdnRepository.findAll()) {
			       documents.add(doc);
			 }
				System.out.println("3------");
				for (Customer customerDetails : customerList) {	
					for (SdnDetails sdnDetails : sdnList) {	
						if(customerDetails.getCustomerFName().contentEquals(sdnDetails.getFirstName()))
						{
							CustomerSet.add(customerDetails);
						}
					}
					
				}
				
				for (Customer sdnDetails : CustomerSet) {
					System.out.println(sdnDetails.getAadhaarNo()+"------");
				}
				
			}catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}

		public String readINSwift(File srcIn,File dest)
		{
			try
			{
				 MT103DataParser mt103=new MT103DataParser();
	                
	               
	                ArrayList<SwiftMsg>swftList=new ArrayList<SwiftMsg>();
	                for (File subFile : srcIn.listFiles()) {
		                    System.out.println("sub files"+subFile);
	                		  SwiftMsg swift= new SwiftMsg();
	                		  swift= mt103.mT103MessageParsing(subFile);
	                		  List<SdnDetails>lst=ofacSdnRepository.findByFirstNameAndAddress(swift.getNameAddress1(),swift.getNameAddress2());
	                		 
	                		  for (SdnDetails sdnList : lst) 
	                		  {
	                			 AltAddress address=altAddressRepository.findBySdnIud(sdnList.getId());
	                			  
	                			 // System.out.println(swift.getNameAddress1()+"==="+sdnList.getFirstName()+"&&"+sdnList.getAddress()+"=="+swift.getNameAddress2());
								if(sdnList.getFirstName().contentEquals(swift.getNameAddress1()) )
								{
									swift.setRemark("black listed");
								}
	                		  }
	                		 
	                		 if(swift.getRemark()!=null)
	                		 {
	                		  if(swift.getRemark().contentEquals("black listed"))
	                		  {
	                			  Alert alert=new Alert();
	                			  alert.setBankCode(swift.getOrderingInstitutionBIC());
	                			  alert.setAccountNo(swift.getOrderingCustomeAccountNumber());
	                			  alert.setAssignedUser("");
	                			  alert.setCustomerName(swift.getSender());
	                			  alert.setAlertCode("pastic card");
	                			  alert.setBankCode(swift.getOrderingInstitutionBIC());
	                			  
	                			  
	                			 
	    		      			  alert.setCustId(swift.getCustomerPartyIdentifier());
	    		      			  alert.setCustomerNo(swift.getCustomerPartyIdentifier());
	    		      			  alert.setAlertCode("swift");
	    		      			  alert.setAlertStatus("black listed");
	    		      			  int v=21;
	    		      			 Long l2=Long.valueOf(v);
	    		      			 alert.setBaseRuleId(l2);
	    		      			  String val=swift.getAmount().substring(0, swift.getAmount().length()-1);
	    		      			Double d = new Double(val); 
	    		      	        BigDecimal b = BigDecimal.valueOf(d); 
	    		      			 
	    		      		    alert.setTxnAmount(b);
	    		      		 
	    		      		    
	    		      			alert.setTxnDtTm(java.util.Calendar.getInstance().getTime());
	    		      			alert.setCashflowType(swift.getRemark());
	    		      			alert.setTxnNo(swift.getBeneficiaryAccountNo());
	    		      			alert.setTxnType(swift.getRemark());
	    		      			alert.setRiskSeverity("high");
	                			  alertRepository.save(alert);
	                			  
	                			   Path temp = Files.move (Paths.get(subFile.toString()),  
	       	    	  	               Paths.get(dest.toString()+File.separator+"IN"+File.separator+"Violated"+File.separator+subFile.getName()));
	       	    	                  System.out.println(dest.toString()+File.separator+"IN"+File.separator+"Violated"+File.separator+subFile.getName());
	                		  }
	                		  else
	                		  {
	                			  Path temp = Files.move (Paths.get(subFile.toString()),  
	       	    	  	               Paths.get(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName()));
	                			  System.out.println(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName());
	                		  }
	                		  swftList.add(swift);
	                		 }
	                		  else
	                		  {
	                			  Path temp = Files.move (Paths.get(subFile.toString()),  
	       	    	  	               Paths.get(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName()));
	                			  System.out.println(dest.toString()+File.separator+"IN"+File.separator+"Clean"+File.separator+subFile.getName());
	                		  }
	                		  swftList.add(swift);
	                		        	   
		            }
	              
	                swiftRepository.saveAll(swftList);
	                System.out.println("swift moved add success");
	                return "success";
			}catch (Exception e) {
				//e.printStackTrace();
			}
			return null;
		}
		
		
		public String readOUTSwift(File srcOut,File destOut)
		{
			try
			{
				 MT103DataParser mt103=new MT103DataParser();
	                
	               
	                ArrayList<SwiftMsg>swftList=new ArrayList<SwiftMsg>();
	                for (File subFile : srcOut.listFiles()) {
		                    System.out.println("sub files"+subFile);
	                		  SwiftMsg swift= new SwiftMsg();
	                		  swift= mt103.mT103MessageParsing(subFile);
	                		  List<SdnDetails>lst=ofacSdnRepository.findByFirstNameAndAddress(swift.getNameAddress1(),swift.getNameAddress2());
	                		  for (SdnDetails sdnList : lst) 
	                		  {
	                			 // System.out.println(swift.getNameAddress1()+"==="+sdnList.getFirstName()+"&&"+sdnList.getAddress()+"=="+swift.getNameAddress2());
								if(sdnList.getFirstName().contentEquals(swift.getNameAddress1()))
								{
									swift.setRemark("black listed");
								}
	                		  }
	                		 
	                		  if(swift.getRemark()!=null)
		                		 {
	                		  if(swift.getRemark().contentEquals("black listed"))
	                		  {
	                			  Alert alert=new Alert();
	                			  alert.setBankCode(swift.getOrderingInstitutionBIC());
	                			  alert.setAccountNo(swift.getOrderingCustomeAccountNumber());
	                			  alert.setAssignedUser("");
	                			  alert.setCustomerName(swift.getSender());
	                			  alert.setAlertCode("pastic card");
	                			  alert.setBankCode(swift.getOrderingInstitutionBIC());
	    		      			  alert.setCustId(swift.getCustomerPartyIdentifier());
	    		      			  alert.setCustomerNo(swift.getCustomerPartyIdentifier());
	    		      			  alert.setAlertCode("swift");
	    		      			  alert.setAlertStatus("black listed");
	    		      			  int v=21;
	    		      			 Long l2=Long.valueOf(v);
	    		      			 alert.setBaseRuleId(l2);
	    		      			  String val=swift.getAmount().substring(0, swift.getAmount().length()-1);
	    		      			Double d = new Double(val); 
	    		      	        BigDecimal b = BigDecimal.valueOf(d); 	 
	    		      		    alert.setTxnAmount(b);    
	    		      			alert.setTxnDtTm(java.util.Calendar.getInstance().getTime());
	    		      			alert.setCashflowType(swift.getRemark());
	    		      			alert.setTxnNo(swift.getBeneficiaryAccountNo());
	    		      			alert.setTxnType(swift.getRemark());
	    		      			alert.setRiskSeverity("high");
	                			  alertRepository.save(alert);
	                			  
	                			   Path temp = Files.move (Paths.get(subFile.toString()),  
	       	    	  	               Paths.get(destOut.toString()+File.separator+"OUT"+File.separator+"Violated"+File.separator+subFile.getName()));
	       	    	                  System.out.println(destOut.toString()+File.separator+"OUT"+File.separator+"Violated"+File.separator+subFile.getName());
	                		  }
	                		  else
	                		  {
	                			  Path temp = Files.move (Paths.get(subFile.toString()),  
	       	    	  	               Paths.get(destOut.toString()+File.separator+"OUT"+File.separator+"Clean"+File.separator+subFile.getName()));
	                			  System.out.println(destOut.toString()+File.separator+"OUT"+File.separator+"Clean"+File.separator+subFile.getName());
	                		  }
	                		  
	                		  swftList.add(swift);
		                		 }
	                		  else
	                		  {
	                			  Path temp = Files.move (Paths.get(subFile.toString()),  
		       	    	  	               Paths.get(destOut.toString()+File.separator+"OUT"+File.separator+"Clean"+File.separator+subFile.getName()));
		                			  System.out.println(destOut.toString()+File.separator+"OUT"+File.separator+"Clean"+File.separator+subFile.getName());
		                	
	                		  }
	                		  swftList.add(swift);
		                		         	   
		            }
	              
	                swiftRepository.saveAll(swftList);
	                return "success";
			}catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		
		
}
