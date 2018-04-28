package utilServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Account;
import javabeans.User;
import sql.EmployeeData;
import sql.sqlConnection;
import sql.uersData;
import sql.webpgUtil;

public class userSignUpServlet extends HttpServlet{

	public userSignUpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("USER SIGNUP PAGE");

		String contextPath = request.getContextPath();

		User newUser = new User();
		Account newUserAccount = new Account();

		String SSN = request.getParameter("SSN");
		String password = request.getParameter("Password");
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		String street = request.getParameter("Street");
		String city = request.getParameter("City");
		String state = request.getParameter("State");
		int zipcode = Integer.parseInt(request.getParameter("Zipcode"));
		String email = request.getParameter("Email");
		String telephone = request.getParameter("Telephone");
		String PPP = request.getParameter("PPP");
		int rating = Integer.parseInt(request.getParameter("Rating"));
		String dateOfLastAct = request.getParameter("DateOfLastAct");
		
		String cardNum = request.getParameter("CreditCardNo");
		String acctNum = request.getParameter("AccountNumber");
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);

		newUser.setSSN(SSN);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setStreet(street);
		newUser.setCity(city);
		newUser.setState(state);
		newUser.setzip(zipcode);
		newUser.setemail(email);
		newUser.setTelephone(telephone);
		newUser.setPPP(PPP);
		newUser.setRating(rating);

		newUserAccount.setAcctCreationDate(cal);
		newUserAccount.setCardNum(cardNum);
		newUserAccount.setAcctNum(acctNum);
		newUserAccount.setSSN(SSN);;


		boolean submitted = request.getParameter("submitBtn") != null;

		//Insert new user info to Database, This code will insert the sign up information into Person and Customer table.
		try {
			sqlConnection sc = new sqlConnection();
			sc.readDatabase();
			
			if(uersData.searchPerson(sc.getConnected(), SSN)==null) {
				uersData.insertPerson(sc.getConnected(), newUser);
				uersData.insertUser(sc.getConnected(), newUser);
				// Insert new person and user info to BackUp Database 
			}else if(uersData.searchUser(sc.getConnected(), SSN) == null) {
				uersData.insertUser(sc.getConnected(), newUser);
			}
			else {
				webpgUtil.setIdExists(true);
			}
			
			sc.close();
		} catch(SQLException e) {
			response.sendRedirect(contextPath + "/errorpage.jsp");
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			response.sendRedirect(contextPath + "/errorpage.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect(contextPath + "/errorpage.jsp");
		}

		//Redirection to index.jsp
		
		response.sendRedirect(contextPath+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
