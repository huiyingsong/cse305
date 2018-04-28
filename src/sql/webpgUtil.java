package sql;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.User;


public class webpgUtil {
	
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	public static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	
	private static final webpgUtil webutil= null;
	
	private static int userlevel = 0;
	private static HttpSession session = null;
	private static boolean idExists = false;
	
	private webpgUtil() {
		
	}
	
	public static int getUserlevel() {
		return userlevel;
	}
	
	public static void setUserlevel(int newlevel){
		userlevel = newlevel;
	}

	public static boolean isIdExists() {
		return idExists;
	}

	public static void setIdExists(boolean idExists) {
		webpgUtil.idExists = idExists;
	}

	public static HttpSession getSession() {
		return session;
	}

	public static void setSession(HttpSession session) {
		webpgUtil.session = session;
	}

	public static void deleteSession() {
		session = null;
	}
	
	public static webpgUtil getWebutil() {
		if(webutil == null) {
			return new webpgUtil();
		}
		return webutil;
	}
	
	
	public static void setConnection(ServletRequest request, Connection connection) {
		request.setAttribute(ATT_NAME_CONNECTION, connection);
    }
	
	public static Connection getConnection(ServletRequest request) {
        Connection connection = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return connection;
    }
	
	public static void remeberMe(HttpSession session, User loginedUser) {
		setSession(session);
		session.setAttribute("loginedUser", loginedUser);
	}
	
	public static User getOnlineUser (HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }
	
	public static void storeInCookie(HttpServletResponse response, User user) {
		Cookie cookie = new Cookie(ATT_NAME_USER_NAME, user.getSSN());
		cookie.setMaxAge(4000);
		response.addCookie(cookie);
		
	}
	
	public static String getUserInCookie (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (ATT_NAME_USER_NAME.equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }
	
	public static void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(ATT_NAME_USER_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
	
	
}
