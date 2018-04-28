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

//import com.mysql.cj.jdbc.Driver;
import javabeans.Person;
import javabeans.Profile;
import javabeans.Account;
import javabeans.Dates;
import javabeans.Login;
import javabeans.User;
import javabeans.salesReport;

public class uersData {
	
	public static void insertPerson(Connection connection, Person person) throws SQLException{
		String sql = "insert into hsinlin.Person(SSN,Password,FirstName,LastName,Street,City,State,Zipcode,Email,Telephone)" 
						+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);
		
		ps.setString(1, person.getSSN());
		ps.setString(2, person.getpassword());
		ps.setString(3, person.getfirstName());
		ps.setString(4, person.getLastName());
		ps.setString(5, person.getStreet());
		ps.setString(6, person.getCity());
		ps.setString(7, person.getState());
		ps.setInt(8, person.getzip());
		ps.setString(9, person.getemail());
		ps.setString(10, person.gettelephone());	
		ps.executeUpdate();
	}
	
	public static void insertUser(Connection connection, User user) throws SQLException{
		String sql = "insert into hsinlin.User(SSN,PPP,Rating,DateOfLastAct)" 
						+ "values(?,?,?,?)";
		
		PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);
		
		ps.setString(1, user.getSSN());
		ps.setString(2, user.getPPP());
		ps.setInt(3, user.getRating());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = user.getLastAct().getTime();
		ps.setString(4, dateFormat.format(d));	
		ps.executeUpdate();
	}
	
	public static Person searchPerson(Connection connection, String SSN) throws SQLException{

		String sql = "Select * From hsinlin.Person p Where p.SSN = ?";

 		

		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

 		ps.setString(1, SSN);

		ResultSet result = ps.executeQuery();

		while(result.next()) {

			Person person = new Person();

			person.setSSN(result.getString("SSN"));

			person.setPassword(result.getString("Password"));

			person.setFirstName(result.getString("FristName"));

			person.setLastName(result.getString("LastName"));

			person.setStreet(result.getString("Street"));

			person.setCity(result.getString("City"));

			person.setState(result.getString("State"));

			person.setzip(result.getInt("Zipcode"));

			person.setTelephone(result.getString("Telephone"));

			

			return person;

		}

		return null;

	}
	
	public static User searchUser(Connection connection, String SSN) throws SQLException{

		String sql = "Select * From hsinlin.User U Where U.SSN = ?";

		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

 		ps.setString(1, SSN);

		ResultSet result = ps.executeQuery();

		while(result.next()) {
			User person = new User();
			person.setSSN(result.getString("SSN"));
			
			/*
			person.setPassword(result.getString("Password"));
			person.setFirstName(result.getString("FristName"));
			person.setLastName(result.getString("LastName"));
			person.setStreet(result.getString("Street"));
			person.setCity(result.getString("City"));
			person.setState(result.getString("State"));
			person.setzip(result.getInt("Zipcode"));
			person.setTelephone(result.getString("Telephone"));*/
			
			person.setPPP(result.getString("PPP"));			
			person.setRating(result.getInt("Rating"));
			Date d = result.getDate("DateOfLastAct");
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			person.setLastAct(c);
			return person;

		}

		return null;

	}
	
	public static Account searchAccount(Connection connection, String acctNum) throws SQLException{

		String sql = "Select * From hsinlin.Account A Where A.AcctNum = ?";

		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);

 		ps.setString(1, acctNum);

		ResultSet result = ps.executeQuery();

		while(result.next()) {
			Account person = new Account();
			person.setSSN(result.getString("OwnerSSN"));			
			person.setAcctNum(result.getString("AcctNum"));			
			person.setCardNum(result.getString("CardNumber"));
			Date d = result.getDate("AcctCreationDate");
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			person.setAcctCreationDate(c);
			return person;
		}

		return null;

	}
	
	public static void insertAcct(Connection connection, Account user) throws SQLException{
		String sql = "insert into hsinlin.Account(OwnerSSN,CardNumber,AcctNum,AcctCreationDate)" 
						+ "values(?,?,?,?)";
		
		PreparedStatement ps = (PreparedStatement)connection.prepareStatement(sql);
		
		ps.setString(1, user.getSSN());
		ps.setString(2, user.getCardNum());
		ps.setString(3, user.getAcctNum());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = user.getAcctCreationDate().getTime();
		ps.setString(4, dateFormat.format(d));	
		ps.executeUpdate();
	}
	


    public static Profile searchProfile(Connection connection, String profileID) throws SQLException {

		String sql = "Select * From hsinlin.Profile P Where P.ProfileID = ?";
		PreparedStatement pstm = (PreparedStatement) connection.prepareStatement(sql);

		pstm.setString(1, profileID);

		ResultSet rs = pstm.executeQuery();

		rs.next();
		Profile p = new Profile();
		p.setAge(rs.getInt("Age"));
		p.setProfileID(profileID);

		Date dat = rs.getDate("CreationDate");
		Calendar c = Calendar.getInstance();
		c.setTime(dat);
		p.setCreationDate(c);

		p.setDatingAgeRangeEnd(rs.getInt("DatingAgeRangeStart"));
		p.setDatingAgeRangeEnd(rs.getInt("DatingAgeRangeEnd"));
		p.setGender(rs.getString("Gender"));
		p.setHobbies(rs.getString("Hobbies"));
		p.setHeight(rs.getDouble("Height"));
		p.setWeight(rs.getInt("Weight"));
		p.setHairColor(rs.getString("HairColor"));
		Date date = rs.getDate("LastModDate");
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		p.setLastModDate(c1);
		p.setDatingGeoRange(rs.getInt("DatingGeoRange"));
		System.out.println("profile id is " + profileID + " age is: " + p.getAge() 

						 + " height is: " + p.getHeight() + " weight is: " + p.getWeight());

		return p;

	}
	
	public ArrayList<Dates> searchPendingDates(Connection connection, String profile, Calendar date) throws SQLException{
		String sql = "Select D.Profile2, D.Location, D.Date_Time, D.BookingFee"
				+ " From hsinlin.Date D "
				+ "Where D.Date_Time >= ? AND D.Profile1 = ?";
		
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date pendingD = date.getTime();

	    System.out.println(dateFormat.format(pendingD));

		ps.setString(1, dateFormat.format(pendingD));
 		ps.setString(2, profile);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Dates> pendingDates = new ArrayList<Dates>();
		
		while(rs.next()) {
			   Dates d = new Dates();
			   d.setProfile2(rs.getString("Profile2"));
			   
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(rs.getDate("Date_Time"));
			   d.setDateTime(cal);
			   //System.out.println(rs.getDate("Date_Time"));
			   
			   d.setLocation(rs.getString("Location"));
			   //System.out.println(rs.getString("Profile2"));
			   
			   d.setBookingFee(rs.getDouble("BookingFee"));
			   //System.out.println(rs.getDouble("BookingFee"));
			   
			   pendingDates.add(d);
			   //System.out.println("in while");
			  
		  }
		  return pendingDates;
	}
	
	public static ArrayList<Dates> searchPastDates(Connection connection, String profile, Calendar date) throws SQLException{
		String sql = "Select D.Profile2, D.Location, D.Date_Time"
				+ " From hsinlin.Date D "
				+ "Where D.Date_Time <= ? AND D.Profile1 = ?";
		
		PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
 		ps.setString(1, profile);
		ps.setString(2, dateFormat.format(date));
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Dates> pastDates = new ArrayList<Dates>();
		
		while(rs.next()) {
			   Dates d = new Dates();
			   d.setProfile1(rs.getString("Profile1"));
			   System.out.println(rs.getString("Profile1"));

			   d.setProfile2(rs.getString("Profile2"));
			   
			   d.setCustRep(rs.getString("CustRep"));
			   System.out.println(rs.getString("custRep"));
			   
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(rs.getDate("Date_Time"));
			   d.setDateTime(cal);
			   System.out.println(rs.getDate("Date_Time"));
			   
			   d.setLocation(rs.getString("Location"));
			   System.out.println(rs.getString("Location"));
			   
			   d.setBookingFee(rs.getDouble("BookingFee"));
			   //System.out.println(rs.getDouble("BookingFee"));
			   
			   d.setComments(rs.getString("Comments"));
			   //System.out.println(rs.getString("Profile2"));
			   

			   d.setUser1Rating(rs.getInt("User1Rating"));
			   

			   d.setUser2Rating(rs.getInt("User2Rating"));
			   
			   pastDates.add(d);
			   //System.out.println("in while");
			  
		  }
		  return pastDates;
	}

}
