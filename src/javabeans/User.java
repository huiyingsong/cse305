package javabeans;

import java.util.Calendar;

public class User extends Person{
	private String PPP;
	private int rating;
	private Calendar dateOfLastAct;
	
	public User() {
		super();
	}
	
	public String getPPP() {
		return PPP;
	}
	public void setPPP(String PPP) {
		this.PPP = PPP;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Calendar getLastAct() {
		return dateOfLastAct;
	}
	public void setLastAct(Calendar dateOfLastAct) {
		this.dateOfLastAct = dateOfLastAct;
	}
}
