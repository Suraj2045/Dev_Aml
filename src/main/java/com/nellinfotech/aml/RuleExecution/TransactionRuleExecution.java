package com.nellinfotech.aml.RuleExecution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nellinfotech.aml.constant.RuleConstant;
import com.nellinfotech.aml.entities.Transaction;

@Component
public class TransactionRuleExecution {
	
	public List<String> prepareRules(String acNo,List<Transaction> transaction)
	{	Map<String,List<String>>ruleMessages=new HashMap<String, List<String>>();
		List<String> msg=new ArrayList<String>();
		try
		{	Map<Integer,Date>dateMap=new HashMap<Integer,Date>();
			Map<Integer,Date>MonthMap=new HashMap<Integer,Date>();
			for (Transaction transaction2 : transaction) 
			{
				dateMap.put((transaction2.getTxnDtTm().getDate()+transaction2.getTxnDtTm().getMonth()+transaction2.getTxnDtTm().getYear()), transaction2.getTxnDtTm());
			    MonthMap.put((transaction2.getTxnDtTm().getMonth()+transaction2.getTxnDtTm().getYear()), transaction2.getTxnDtTm());		
			}		
			List<Integer> dateTransactions = new ArrayList<Integer>(dateMap.keySet());	
			List<Integer> monthTransactions = new ArrayList<Integer>(MonthMap.keySet());		
			String rule1=applyRule1(monthTransactions,transaction);
			msg.add(rule1);
			String rule2=applyRule2DailyCreditLimit(dateTransactions,transaction);
			msg.add(rule2);
			String rule3=applyRule3DailyDebitLimit(dateTransactions,transaction);
			msg.add(rule3);
//			String rule4=applyRule4MonthlyCreditLimit(monthTransactions,transaction);
//			msg.add(rule4);
//			String rule5=applyRule5MonthlyDebitLimit(monthTransactions,transaction);
//			msg.add(rule5);
			ruleMessages.put(acNo, msg);
			return msg;
			
		}catch (Exception e) 
		{
			
		}
		return null;
	}
	
	//Rule Application Methods-----
	
	///rule 1 NumberOf transactions>>>>>>>>
		public String applyRule1(List<Integer> monthTransactions, List<Transaction> transaction)
		{	try
			{
				Integer count=0;
				for (Integer monthTrans : monthTransactions) {
				
						for (Transaction transactionMonth : transaction)
						 {
								if(monthTrans==(transactionMonth.getTxnDtTm().getMonth()+transactionMonth.getTxnDtTm().getYear())&&transactionMonth.getCashflowType().contentEquals("cr"))
								{
									count++;
								}
						
								if(monthTrans==(transactionMonth.getTxnDtTm().getMonth()+transactionMonth.getTxnDtTm().getYear())&&transactionMonth.getCashflowType().contentEquals("dr"))
								{
									count++;
								}
						}		
				}
				
				if(count>RuleConstant.rule1_NumberOftransactions)
				{
					return "Rule 1 NumberOftransactions rule Overriden";
				}
				else
				{
					return "ok";
				}
			}catch (Exception e) {
				
			}
			return null;
		}
		
	/// rule 2 Daily Deposit Limit>>>>>>>>>>>>>>
		private String applyRule2DailyCreditLimit(List<Integer> dateTransactions, List<Transaction> transaction) {
			try
			{	Integer count=0;
				for (Integer date : dateTransactions) {
					BigDecimal transactionDailyCreditLimit=new BigDecimal(00);
					for (Transaction transactionDate : transaction) {
						if(date==(transactionDate.getTxnDtTm().getDate()+transactionDate.getTxnDtTm().getMonth()+transactionDate.getTxnDtTm().getYear())&&transactionDate.getCashflowType().contentEquals("cr"))
						{
							transactionDailyCreditLimit=transactionDailyCreditLimit.add(transactionDate.getTxnAmount());	
						}
						
					}
					if(transactionDailyCreditLimit.compareTo(RuleConstant.rule2_DailyDepositeLimit)==1)
					{
						count++;
					}
					System.out.println(date+"ac no:"+"daily Limit:"+transactionDailyCreditLimit);
				}
				if(count>0)
				{
					return "rule 2 Daily Deposit Limit Overriden";
				}
				else
				{
					return "ok";
				}
			}catch (Exception e) {
			
			}
			return null;
		}
		
		/// rule 3 Daily withdraw Limit>>>>>>>>>>>>>>
				private String applyRule3DailyDebitLimit(List<Integer> dateTransactions, List<Transaction> transaction) {
					try
					{	Integer count=0;
						for (Integer date : dateTransactions) {
							BigDecimal transactionDailyDebitLimit=new BigDecimal(00);
							for (Transaction transactionDate : transaction) {
								if(date==(transactionDate.getTxnDtTm().getDate()+transactionDate.getTxnDtTm().getMonth()+transactionDate.getTxnDtTm().getYear())&&transactionDate.getCashflowType().contentEquals("dr"))
								{
									transactionDailyDebitLimit=transactionDailyDebitLimit.add(transactionDate.getTxnAmount());	
								}
							}
							if(transactionDailyDebitLimit.compareTo(RuleConstant.rule3_DailyWithDrowLimit)==1)
							{
								count++;
							}
							System.out.println(date+"ac no:"+"daily Limit:"+transactionDailyDebitLimit);
						}
						if(count>0)
						{
							return "rule 3 Daily Withdraw Limit Overriden";
						}
						else
						{
							return "ok";
						}
					}catch (Exception e) {
					
					}
					return null;
				}
				
//		/// rule 4 Monthly Deposit Limit>>>>>>>>>>>>>>
//	private String applyRule4MonthlyCreditLimit(List<Integer> monthTransactions, List<Transaction> transaction) {
//		try
//		{	Integer count=0;
//			for (Integer monthTrans : monthTransactions) {
//				BigDecimal transactionMonthlyCreditLimit=new BigDecimal(00);
//					for (Transaction transactionMonth : transaction)
//					 {
//							if(monthTrans==(transactionMonth.getTxnDtTm().getMonth()+transactionMonth.getTxnDtTm().getYear())&&transactionMonth.getCashflowType().contentEquals("cr"))
//							{
//								transactionMonthlyCreditLimit=transactionMonthlyCreditLimit.add(transactionMonth.getTxnAmount());	
//							}
//					}
//					if(transactionMonthlyCreditLimit.compareTo(RuleConstant.rule4_MonthlyDepositeLimit)==1)
//					{
//						count++;
//					}
//									System.out.println(monthTrans+"ac no:"+"monthly Limit:"+transactionMonthlyCreditLimit);
//					
//			}
//			if(count>0)
//			{
//				return "rule 4 Monthly Deposit Limit Overriden";
//			}
//			else
//			{
//				return "ok";
//			}
//		}catch (Exception e) {
//			
//		}
//		return null;
//	}
//	/// rule 5 Monthly withdraw Limit>>>>>>>>>>>>>>
//	private String applyRule5MonthlyDebitLimit(List<Integer> monthTransactions, List<Transaction> transaction) {
//		try
//		{	Integer count=0;
//			for (Integer monthTrans : monthTransactions) {
//				BigDecimal transactionMonthlyDebitLimit=new BigDecimal(00);
//					for (Transaction transactionMonth : transaction)
//					 {
//							if(monthTrans==(transactionMonth.getTxnDtTm().getMonth()+transactionMonth.getTxnDtTm().getYear())&&transactionMonth.getCashflowType().contentEquals("dr"))
//							{
//								transactionMonthlyDebitLimit=transactionMonthlyDebitLimit.add(transactionMonth.getTxnAmount());	
//							}
//					}
//					if(transactionMonthlyDebitLimit.compareTo(RuleConstant.rule5_MonthlyWithDrowLimit)==1)
//					{
//						count++;
//					}
//							System.out.println(monthTrans+"ac no:"+"monthly Limit:"+transactionMonthlyDebitLimit);
//					
//			}
//			if(count>0)
//			{
//				return "rule 5 Monthly withdraw Limit Overriden";
//			}
//			else
//			{
//				return "ok";
//			}
//		}catch (Exception e) {
//			
//		}
//		return null;
//	}
//	
//	
	
}
