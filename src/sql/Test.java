package sql;

import sql.sqlConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javabeans.Dates;
import javabeans.Likes;
import javabeans.User;
import sql.EmployeeData;
import sql.DateData;
import sql.LikeData;

public class Test {
	
	
	public static void main(String[] args) throws Exception {
		
		sqlConnection dd = new sqlConnection();
		dd.readDatabase();
		EmployeeData ed = new EmployeeData();
		DateData d = new DateData();
		uersData ud = new uersData();
		LikeData ld = new LikeData();
		
		//Date today = new Date(2018, 4, 24);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateRepresentation = cal.getTime();
	    //System.out.println(dateFormat.format(dateRepresentation));
		
	    Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 2014);
		cal1.set(Calendar.MONTH, Calendar.OCTOBER);
		cal1.set(Calendar.DAY_OF_MONTH, 30);
		Date date1 = cal1.getTime();
	    //System.out.println(dateFormat.format(date1));
		
		Calendar cancelD = Calendar.getInstance();
		cancelD.set(2014, 9, 04, 21, 39, 42);
		Date date2 = cancelD.getTime();
	    //System.out.println(dateFormat.format(date2));
		

		Calendar dateD = Calendar.getInstance();
		dateD.set(2017, 7, 06, 16, 21, 06);
		Date date4 = cancelD.getTime();
		/*
		Dates newDate = new Dates();
		newDate.setProfile1("Fletcher2013");
		newDate.setProfile2("DesiraeBerg");
		newDate.setDateTime(dateD);
		newDate.setBookingFee(50.5);
		newDate.setUser1Rating(5);
		newDate.setUser2Rating(5);
		
		System.out.println(dateFormat.format(date4));
		d.insertDates(dd.getConnected(), newDate);

		d.cancelDates(dd.getConnected(), "Isabelle2013", "DesiraeBerg", cancelD);
		d.updateComment(dd.getConnected(), newDate, "123");
		
		ArrayList<Dates> pendingDates = new ArrayList<Dates>();
		Calendar pendingD = Calendar.getInstance();
		pendingD.set(2013, 0, 04, 21, 39, 42);
		Date date3 = pendingD.getTime();*/
		
		/*
		pendingDates = ud.searchPendingDates(dd.getConnected(), "Isabelle2013", pendingD);
		
		System.out.println(pendingDates.size());
		for(Dates i: pendingDates) {
			System.out.print(i.getProfile2());
		}*/
		

		Calendar likeD = Calendar.getInstance();
		likeD.set(2014, 11, 04, 00, 00, 00);
		
		Likes like = new Likes();
		like.setDateTime(likeD);
		like.setLikee("Brenna_Berlin");
		like.setLiker("Isabelle2013");
		//ld.insertLikes(dd.getConnected(), like);
		
		Calendar cld = Calendar.getInstance();
		cld.set(2018, 4, 26, 14, 22, 02);
		/*cld.set(Calendar.YEAR, 2018);
		cld.set(Calendar.MONTH, Calendar.APRIL);
		cld.set(Calendar.DAY_OF_MONTH, 26);
		cld.set(Calendar.HOUR_OF_DAY, 14);
		cld.set(Calendar.MINUTE, 22);
		cld.set(Calendar.SECOND, 02);*/
		
		/*
		Dates dt = new Dates();
		dt.setBookingFee(100.02);
		dt.setComments("WAT");
		dt.setCustRep("444-44-4444");
		dt.setDateTime(cld);
		dt.setLocation("Restroom");
		dt.setProfile1("Brenna_Berlin");
		dt.setProfile2("Fletcher_Trujillo");
		dt.setUser1Rating(4);
		dt.setUser2Rating(3);
		ed.recordDates(dd.getConnected(), dt);*/

		
		//ed.searchHighestRatedUser(dd.getConnected());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2014, 4, 6, 12, 0, 0);
		//Dates searchedD = DateData.searchDate(dd.getConnected(), "Fletcher2013", "DesiraeBerg", cal2);
		//System.out.println(searchedD.getLocation());
		
		User u = uersData.searchUser(dd.getConnected(), "555-55-5555");
		System.out.println(u.getPPP());
	}
}
