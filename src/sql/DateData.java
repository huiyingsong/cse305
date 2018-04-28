package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javabeans.Dates;
import javabeans.Person;

public class DateData {

	public static void insertDates(Connection connection, Dates date) throws SQLException{
		String sql = "INSERT into hsinlin.Date(Profile1, Profile2, CustRep, Date_Time, Location, BookingFee,  Comments, User1Rating, User2Rating)" 
						+ "VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = date.getDateTime().getTime();

		ps.setString(1, date.getProfile1());
		ps.setString(2, date.getProfile2());
		ps.setString(3, date.getCustRep());
		ps.setString(4, dateFormat.format(d));
		ps.setString(5, date.getLocation());
		ps.setDouble(6, date.getBookingFee());
		ps.setString(7, date.getComments());
		ps.setInt(8, date.getUser1Rating());	
		ps.setInt(9, date.getUser2Rating());	
		ps.executeUpdate();
		ps.close();
	}
	
	public static Dates searchDate(Connection conn, String profile1, String profile2, Calendar Date_Time)throws SQLException{
		String sql = "Select * From hsinlin.Date D Where D.Profile1 = ? AND D.Profile2 = ? AND D.Date_Time = ?";

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

 		ps.setString(1, profile1);
 		ps.setString(2, profile2);
 		ps.setString(3, dateFormat.format(Date_Time.getTime()));
 		
 		//System.out.println(dateFormat.format(Date_Time.getTime()));
 		
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			Dates date = new Dates();
			date.setProfile1(result.getString("Profile1"));
			date.setProfile2(result.getString("Profile2"));
			date.setCustRep(result.getString("CustRep"));
			Calendar cal = Calendar.getInstance();
			cal.setTime(result.getDate("Date_Time"));
			date.setDateTime(cal);
			date.setLocation(result.getString("Location"));
			date.setBookingFee(result.getDouble("BookingFee"));
			date.setComments(result.getString("Comments"));
			date.setUser1Rating(result.getInt("User1Rating"));
			date.setUser2Rating(result.getInt("User2Rating"));
			
			return date;
		}
		return null;
	}
	
	public static void cancelDates(Connection conn, String profile1, String profile2, Calendar Date_Time) throws SQLException{
        String sql = "DELETE FROM hsinlin.Date "
        		+ "WHERE profile1 = ? AND profile2 = ? AND Date_Time = ?";
 
        PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date cancelD = Date_Time.getTime();

	    //System.out.println(dateFormat.format(cancelD));
        
        ps.setString(1, profile1);
        ps.setString(2, profile2);
        ps.setString(3, dateFormat.format(cancelD));
            // execute the delete statement
            ps.execute();
	}
	
public static void updateComment(Connection conn, Dates updatedDate, String comment) throws SQLException{
		
    	String sql = "UPDATE hsinlin.Date D SET D.Comments = ? WHERE D.Profile1 = ? AND D.Profile2 = ? AND D.Date_Time = ?";
    	PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date updateD = updatedDate.getDateTime().getTime();

		ps.setString(1, comment);
    	ps.setString(2, updatedDate.getProfile1());
		ps.setString(3, updatedDate.getProfile2());
		ps.setString(4, dateFormat.format(updateD));
		ps.executeUpdate();
		
		ps.close();
    }
}
