package javabeans;

import java.util.Calendar;

public class Account {
	private String SSN;
	private String cardNum;
	private String acctNum;
	private Calendar acctCreationDate;
	
	public Account() {}

	public Calendar getAcctCreationDate() {
		return acctCreationDate;
	}

	public void setAcctCreationDate(Calendar acctCreationDate) {
		this.acctCreationDate = acctCreationDate;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}
	
	
}
