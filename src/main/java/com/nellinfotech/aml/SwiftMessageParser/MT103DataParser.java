package com.nellinfotech.aml.SwiftMessageParser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.nellinfotech.aml.entities.SwiftMsg;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field23B;
import com.prowidesoftware.swift.model.field.Field23E;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field50F;
import com.prowidesoftware.swift.model.field.Field50K;
import com.prowidesoftware.swift.model.field.Field52A;
import com.prowidesoftware.swift.model.field.Field53A;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.field.Field71A;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
@Component
public class MT103DataParser {
	
	
	public SwiftMsg  mT103MessageParsing(File subFile) {
		MT103 mt;
		 SwiftMsg swift = new SwiftMsg();
		try {
			mt = MT103.parse(subFile);
		/* * Print header information*/
		System.out.println("Sender: " + mt.getSender());
		System.out.println("Receiver: " + mt.getReceiver());
		/* * Print details of a specific fields */
		// Field20 ref = mt.getField20();
		Field50F ref50F = mt.getField50F();
		Field20 ref20 = mt.getField20();
		List<Field13C> ref13c = mt.getField13C();
		Field23B ref23b = mt.getField23B();
		List<Field23E> ref23e = mt.getField23E();
		Field32A ref32a = mt.getField32A();
		Field33B ref33b = mt.getField33B();
		Field50K ref50k = mt.getField50K();
		Field52A ref52a = mt.getField52A();
		Field53A ref53a = mt.getField53A();
		Field57A ref57a = mt.getField57A();
		Field59 ref59 = mt.getField59();
		Field71A ref71a = mt.getField71A();
		Field72 ref72 = mt.getField72();
		System.out.println(Field.getLabel(ref20.getName(), mt.getMessageType(), null) + ": "+ ref20.getComponent(Field20.REFERENCE));
		System.out.println("Bank operation code Type:  " + ref23b.getComponent(Field23B.TYPE));
		System.out.println("50F- Ordering customer Party Identifier:  " + ref50F.getComponent(Field50F.PARTY_IDENTIFIER));
		System.out.println("50F- Ordering customer Name and Address 1:  " + ref50F.getComponent(Field50F.NAME_AND_ADDRESS_1));
		System.out.println("50F- Ordering customer Name and Address 2:  " + ref50F.getComponent(Field50F.NAME_AND_ADDRESS_2));
		System.out.println("50F- Ordering customer Name and Address 3:  " + ref50F.getComponent(Field50F.NAME_AND_ADDRESS_3));
		System.out.println("50F- Ordering customer Name and Address 4:  " + ref50F.getComponent(Field50F.NAME_AND_ADDRESS_4));
		System.out.println("Ordering custome Account Number:  " + ref50k.getComponent(Field50K.ACCOUNT));
		System.out.println("Ordering custome Account Name and Address:  " + ref50k.getComponent(Field50K.NAME_AND_ADDRESS));
		System.out.println("Ordering custome Ordering institution Account:  " + ref52a.getComponent(Field52A.ACCOUNT));
		System.out.println("Ordering institution BIC:  " + ref52a.getComponent(Field52A.BIC));
		System.out.println("Account with institution (beneficiary's bank) BIC:  " + ref57a.getComponent(Field57A.BIC));
		System.out.println("Beneficiary 4x35 Account:  " + ref59.getComponent(Field59.ACCOUNT));
		System.out.println("Beneficiary 4x35 Name and Address:  " + ref59.getComponent(Field59.NAME_AND_ADDRESS));
		System.out.println("Details of charges (OUR/SHA/BEN): " + ref71a.getComponent(Field71A.CODE));
		System.out.println("Sender to receiver information SRU: " + ref72.getComponent(Field72.SRU));
		Field32A f = mt.getField32A();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("Value Date: " + sdf.format(f.getDateAsCalendar().getTime()));
		System.out.println("Amount: " + f.getCurrency() + " " + f.getAmount());
		  Random rand = new Random(); 
          int id = rand.nextInt(1000); 
		swift.setName(mt.getSender());
		swift.setSender(mt.getSender());
		swift.setReceiver(mt.getReceiver());
		swift.setBankOperationCodeType(ref23b.getComponent(Field23B.TYPE));
		swift.setCustomerPartyIdentifier(ref50F.getComponent(Field50F.PARTY_IDENTIFIER));
		swift.setNameAddress1(ref50F.getComponent(Field50F.NAME_AND_ADDRESS_1));
		swift.setNameAddress2(ref50F.getComponent(Field50F.NAME_AND_ADDRESS_2));
		swift.setNameAddress3(ref50F.getComponent(Field50F.NAME_AND_ADDRESS_3));
		swift.setNameAddress4(ref50F.getComponent(Field50F.NAME_AND_ADDRESS_4));
		swift.setOrderingCustomeAccountNumber(ref50k.getComponent(Field50K.ACCOUNT));	
		swift.setOrderingInstitutionBIC(ref52a.getComponent(Field52A.BIC));
		swift.setBeneficiaryAccountNo(ref59.getComponent(Field59.ACCOUNT));
		swift.setValueDate(sdf.format(f.getDateAsCalendar().getTime()));
		swift.setAmount(f.getAmount());
		
		swift.setId(id);
		//swiftRepository.save(swift);
		return swift;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
