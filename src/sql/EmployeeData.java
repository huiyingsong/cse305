package sql;

import javabeans.Dates;
import javabeans.Employee;
import javabeans.Profile;
import javabeans.User;
import javabeans.salesReport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.PreparedStatement;
import java.util.Date;


public class EmployeeData {
	
	public static ArrayList<salesReport> searchSalesReport(Connection conn, Calendar startDate, Calendar endDate) throws SQLException{
		   //System.out.println(startDate.toString());
		   String sql = "Select D.Profile1, D.Profile2, D.Date_Time, D.BookingFee, D.CustRep"
				   		+ " FROM hsinlin.Date D"
				        + " Where D.Date_Time BETWEEN ? AND ?";
		   PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		   
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   
		   Date date1 = startDate.getTime();
		   Date date2 = endDate.getTime();
		   
		   pstm.setString(1, dateFormat.format(date1));
		   pstm.setString(2, dateFormat.format(date2));
		   
		   ResultSet rs = pstm.executeQuery();
		   
		   ArrayList<salesReport> rep = new ArrayList<salesReport>();
		   
		   //System.out.println("Before while");
		   while(rs.next()) {
			   salesReport report = new salesReport();
			   report.setCustRepSSN(rs.getString("CustRep"));
			   //System.out.println(rs.getString("CustRep"));
			   
			   report.setFees(rs.getInt("BookingFee"));
			   //System.out.println(rs.getInt("BookingFee"));
			   
			   report.setProfileA(rs.getString("Profile1"));
			   //System.out.println(rs.getString("Profile1"));
			   
			   report.setProfileB(rs.getString("Profile2"));
			   //System.out.println(rs.getString("Profile2"));
			   
			   report.setDate(rs.getDate("Date_Time"));
			   //System.out.println(rs.getDate("Date_Time"));
			   
			   rep.add(report);
			   //System.out.println("in while");
			  
		  }
		  return rep;
	}
	
	public static void recordDates(Connection conn, Dates dates) throws SQLException{
		
		String sql = "Update hsinlin.Date D" 
					+ " Set D.BookingFee = ?, D.CustRep = ?, D.Location = ?" 
					+ " Where D.Profile1 = ? AND D.Profile2 = ?"
					+ " AND D.Date_Time = ?";
	
	PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
	pstm.setDouble(1, dates.getBookingFee());
	pstm.setString(2, dates.getCustRep());
	pstm.setString(3, dates.getLocation());
	pstm.setString(4, dates.getProfile1());
	pstm.setString(5, dates.getProfile2());
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date date1 = dates.getDateTime().getTime();
	pstm.setString(6, dateFormat.format(date1));
	pstm.executeUpdate();
	System.out.println("after update");
	pstm.close();
	//return dates;
	
}

	
	public static ArrayList<Dates> searchDatesByDate(Connection conn, Calendar date) throws SQLException {
			String sql = "Select *"
					+ " From hsinlin.Date D"
			        + " Where D.Date_Time = ?";
			PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   
			Date date1 = date.getTime();
			pstm.setString(1, dateFormat.format(date1));
			
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Dates> dates = new ArrayList<Dates>();
			
			System.out.println("here");
			
			
			while(rs.next()) {
				Dates d = new Dates();
				d.setComments(rs.getString("Comments"));
				System.out.println(rs.getString("Comments"));
				
				d.setCustRep(rs.getString("CustRep"));
				System.out.println(rs.getString("CustRep"));
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate("Date_Time"));
				d.setDateTime(cal);
				System.out.println(rs.getDate("Date_Time"));
				
				d.setLocation(rs.getString("Location"));
				System.out.println(rs.getString("Location"));
				
				d.setProfile1(rs.getString("Profile1"));
				System.out.println(rs.getString("Profile1"));
				
				d.setProfile2(rs.getString("Profile2"));
				System.out.println(rs.getString("Profile2"));
				
				d.setUser1Rating(rs.getInt("User1Rating"));
				System.out.println(rs.getInt("User1Rating"));
				
				d.setUser2Rating(rs.getInt("User2Rating"));
				System.out.println(rs.getInt("User2Rating"));
				
				
				dates.add(d);
				//return dates;
			}
			return dates;
	}
	
	public static ArrayList<Dates> searchByName(Connection conn, String name) throws SQLException{
		String sql = "Select *" 
					+ " From Date D" 
					+ " Where D.Profile1 = ? OR D.Profile2 = ?";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, name);
		pstm.setString(2, name);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Dates> dates = new ArrayList<Dates>();
		
		while(rs.next()) {
			Dates d = new Dates();
			d.setComments(rs.getString("Comments"));
			System.out.println(rs.getString("Comments"));
			
			d.setCustRep(rs.getString("CustRep"));
			System.out.println(rs.getString("CustRep"));
			
			Calendar cal = Calendar.getInstance();
			   cal.setTime(rs.getDate("Date_Time"));
			   d.setDateTime(cal);
			System.out.println(rs.getDate("Date_Time"));
			
			d.setLocation(rs.getString("Location"));
			System.out.println(rs.getString("Location"));
			
			d.setProfile1(rs.getString("Profile1"));
			System.out.println(rs.getString("Profile1"));
			
			d.setProfile2(rs.getString("Profile2"));
			System.out.println(rs.getString("Profile2"));
			
			d.setUser1Rating(rs.getInt("User1Rating"));
			System.out.println(rs.getInt("User1Rating"));
			
			d.setUser2Rating(rs.getInt("User2Rating"));
			System.out.println(rs.getInt("User2Rating"));
			
			
			dates.add(d);
		}
		return dates;	
	}
	
	public static Employee searchCRByRevenue(Connection conn) throws SQLException {
		String sql = "Select E.SSN, E.role, E.startDate, E.hourlyRate"
					  + " From hsinlin.Employee E, hsinlin.Date D" 
					  + " Where D.CustRep = E.SSN GROUP BY E.SSN" 
					  + " ORDER BY SUM(BookingFee) DESC"
					  + " LIMIT 1";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
	    
		Employee e = new Employee();
	    rs.next();
		e.setSSN(rs.getString("SSN"));
		System.out.println(rs.getString("SSN"));
		e.setHourlyRate(rs.getInt("hourlyRate"));
		System.out.println(rs.getInt("hourlyRate"));
		e.setRole("CustRep");
		Calendar c = Calendar.getInstance();
		c.setTime(rs.getDate("Date"));
		e.setStartDate(c);
		System.out.println(rs.getDate("startDate"));
		
		return e;
	}
	
	//Produce a list of top 3 active customers
	public static ArrayList<Profile> searchActiveUser(Connection conn) throws SQLException{
		String sql = "Select *" 
					+ " From hsinlin.Profile P"
					+ " Order BY 'DateOfLastAct' DESC" 
					+ " LIMIT 3";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Profile> p = new ArrayList<Profile>();
		
		while(rs.next()) {
			Profile pro = new Profile();
			pro.setAge(rs.getInt("Age"));
			System.out.println(rs.getInt("Age"));
			
			pro.setCreationDate(rs.getDate("CreationDate"));
			System.out.println(rs.getDate("CreationDate"));
			
			pro.setDatingAgeRangeStart(rs.getInt("DatingAgeRangeStart"));
			System.out.println(rs.getInt("DatingAgeRangeStart"));
			
			pro.setDatingAgeRangeEnd(rs.getInt("DatingAgeRangeEnd"));
			System.out.println(rs.getInt("DatingAgeRangeEnd"));
			
			pro.setDatingGeoRange(rs.getInt("DatingGeoRange"));
			System.out.println(rs.getInt("DatingGeoRange"));
			
			pro.setGender(rs.getString("Gender"));
			System.out.println(rs.getString("Gender"));
			
			pro.setHairColor(rs.getString("HairColor"));
			System.out.println(rs.getString("HairColor"));
			
			pro.setHeight(rs.getInt("Height"));
			System.out.println(rs.getInt("Height"));
			
			pro.setHobbies(rs.getString("Hobbies"));
			System.out.println(rs.getString("Hobbies"));
			
			pro.setLastModDate(rs.getDate("LastModDate"));
			System.out.println(rs.getDate("LastModDate"));
			
			pro.setOwnerSSN(rs.getString("OwnerSSN"));
			System.out.println(rs.getString("OwnerSSN"));
			
			pro.setProfileID(rs.getString("ProfileID"));
			System.out.println(rs.getString("ProfileID"));
			
			pro.setWeight(rs.getInt("Weight"));
			System.out.println(rs.getInt("Weight"));
			
			System.out.println("END OF THE PROFILE");
			
			p.add(pro);
				
		}
		return p;
	}
	
	//Produce a list of the highest-rated customers????
	public ArrayList<User> searchHighestRatedUser(Connection conn) throws SQLException{
		String sql = "Select *" 
					+ " From hsinlin.User U" 
					+ " ORDER BY U.Rating DESC" 
					+ " LIMIT 3";
		PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<User> usr = new ArrayList<User>();
		while(rs.next()) {
			User u = new User();
			u.setSSN(rs.getString("SSN"));
			System.out.println(rs.getString("SSN"));
			
			u.setPPP(rs.getString("PPP"));
			System.out.println(rs.getString("PPP"));
			
			u.setRating(rs.getInt("Rating"));
			System.out.println(rs.getInt("Rating"));
			
			u.setLastAct(rs.getDate("DateOfLastAct"));
			System.out.println(rs.getDate("DateOfLastAct"));
			
			usr.add(u);
				
		}
		return usr;
	}
	
	public static Employee searchEmployee (Connection connection, String SSN) throws SQLException {
		String sql = "Select * From hsinlin.Employee e Where e.SSN = ?";
		
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
		
		
		
		ps.setString(1, SSN);
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			Employee empl = new Employee();
			empl.setSSN(result.getString("SSN"));
			empl.setRole(result.getString("Role"));
			Date d = result.getDate("StartDate");
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			empl.setStartDate(c);
			empl.setHourlyRate(result.getInt("HourlyRate"));
			
			return empl;
		}
		
		return null;
	}
	
}
