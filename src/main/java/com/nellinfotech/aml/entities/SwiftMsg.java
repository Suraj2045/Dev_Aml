package com.nellinfotech.aml.entities;

import javax.persistence.Id;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "swiftMessages")
public class SwiftMsg {
    @Id    
    @Field
    private int id;
    @Field
    private String name;
    @Field
    private String sender;
    @Field
    private String receiver;
    @Field
    private String sendersReference;
    @Field
    private String bankOperationCodeType;
    @Field
    private String customerPartyIdentifier;
    @Field
    private String orderingCustomeAccountNumber;
    @Field
    private String orderingInstitutionBIC;
    @Field
    private String beneficiaryAccountNo;
    @Field
    private String detailsofCharges;
    @Field
    private String valueDate;
    @Field
    private String amount;
    @Field
    private String nameAddress1;
    @Field
    private String nameAddress2;
    @Field
    private String nameAddress3;
    @Field
    private String nameAddress4;  
    @Field
    private String remark;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSendersReference() {
		return sendersReference;
	}

	public void setSendersReference(String sendersReference) {
		this.sendersReference = sendersReference;
	}

	public String getBankOperationCodeType() {
		return bankOperationCodeType;
	}

	public void setBankOperationCodeType(String bankOperationCodeType) {
		this.bankOperationCodeType = bankOperationCodeType;
	}

	public String getCustomerPartyIdentifier() {
		return customerPartyIdentifier;
	}

	public void setCustomerPartyIdentifier(String customerPartyIdentifier) {
		this.customerPartyIdentifier = customerPartyIdentifier;
	}

	public String getOrderingCustomeAccountNumber() {
		return orderingCustomeAccountNumber;
	}

	public void setOrderingCustomeAccountNumber(String orderingCustomeAccountNumber) {
		this.orderingCustomeAccountNumber = orderingCustomeAccountNumber;
	}

	public String getOrderingInstitutionBIC() {
		return orderingInstitutionBIC;
	}

	public void setOrderingInstitutionBIC(String orderingInstitutionBIC) {
		this.orderingInstitutionBIC = orderingInstitutionBIC;
	}

	public String getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}

	public String getDetailsofCharges() {
		return detailsofCharges;
	}

	public void setDetailsofCharges(String detailsofCharges) {
		this.detailsofCharges = detailsofCharges;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNameAddress1() {
		return nameAddress1;
	}

	public void setNameAddress1(String nameAddress1) {
		this.nameAddress1 = nameAddress1;
	}

	public String getNameAddress2() {
		return nameAddress2;
	}

	public void setNameAddress2(String nameAddress2) {
		this.nameAddress2 = nameAddress2;
	}

	public String getNameAddress3() {
		return nameAddress3;
	}

	public void setNameAddress3(String nameAddress3) {
		this.nameAddress3 = nameAddress3;
	}

	public String getNameAddress4() {
		return nameAddress4;
	}

	public void setNameAddress4(String nameAddress4) {
		this.nameAddress4 = nameAddress4;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SwiftMsg [id=" + id + ", name=" + name + ", sender=" + sender + ", receiver=" + receiver
				+ ", sendersReference=" + sendersReference + ", bankOperationCodeType=" + bankOperationCodeType
				+ ", customerPartyIdentifier=" + customerPartyIdentifier + ", orderingCustomeAccountNumber="
				+ orderingCustomeAccountNumber + ", orderingInstitutionBIC=" + orderingInstitutionBIC
				+ ", beneficiaryAccountNo=" + beneficiaryAccountNo + ", detailsofCharges=" + detailsofCharges
				+ ", valueDate=" + valueDate + ", amount=" + amount + ", nameAddress1=" + nameAddress1
				+ ", nameAddress2=" + nameAddress2 + ", nameAddress3=" + nameAddress3 + ", nameAddress4=" + nameAddress4
				+ ", remark=" + remark + "]";
	}



	
    
   
}
