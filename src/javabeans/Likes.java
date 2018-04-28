package javabeans;
import java.util.Calendar;

public class Likes {
	private String liker;
	private String likee;
	private Calendar Date_Time;
	
	public Likes() {}
	
	public String getLiker() {
		return liker;
	}
	public void setLiker(String liker) {
		this.liker = liker;
	}
	public String getLikee() {
		return likee;
	}
	public void setLikee(String likee) {
		this.likee = likee;
	}
	public Calendar getDateTime() {
		return Date_Time;
	}
	public void setDateTime(Calendar Date_Time) {
		this.Date_Time = Date_Time;
	}
}
