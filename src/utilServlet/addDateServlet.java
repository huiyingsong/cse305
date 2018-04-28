package utilServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

import javabeans.Dates;
import sql.DateData;
import sql.sqlConnection;

@WebServlet("/addDate")
public class addDateServlet extends HttpServlet{


	public addDateServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletOutputStream out = response.getOutputStream();

		String contextPath = request.getContextPath();

		String profile1 = request.getParameter("Profile1");
		String profile2 = request.getParameter("Profile2");
		String custRep = request.getParameter("CustRep");
		Calendar dateTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateTime.setTime(sdf.parse(request.getParameter("Date_Time")));
		} catch (ParseException e1) {
			System.out.println("Wrong Date Time Format");
		}
		
		String location = request.getParameter("Location");
		double bookingFee = Double.parseDouble(request.getParameter("BookingFee"));
		String comments = request.getParameter("Comments");
		int user1Rating = Integer.parseInt(request.getParameter("user1Rating"));
		int user2Rating = Integer.parseInt(request.getParameter("user2Rating"));
		
		Dates newDate = new Dates();
		newDate.setProfile1(profile1);
		newDate.setProfile2(profile2);
		newDate.setCustRep(custRep);
		newDate.setDateTime(dateTime);
		newDate.setLocation(location);
		newDate.setBookingFee(bookingFee);
		newDate.setComments(comments);
		newDate.setUser1Rating(user1Rating);
		newDate.setUser2Rating(user2Rating);
		
			try {
				sqlConnection sc = new sqlConnection();
				sc.readDatabase();
				
				if(DateData.searchDate(sc.getConnected(), profile1, profile2, dateTime)==null){
					System.out.println("insert");
					DateData.insertDates(sc.getConnected(), newDate);
				}else {
					System.out.println("exists");
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
		
		
		response.sendRedirect(contextPath+"/dashboard-manager/employee_menu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}
