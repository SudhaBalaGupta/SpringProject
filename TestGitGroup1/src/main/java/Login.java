import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String a = request.getParameter("userEmail");
		String b = request.getParameter("userPass");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exp", "Sudha", "12345");

			PreparedStatement ps = con
					.prepareStatement("Select userEmail, userPass from login where userEmail=? and userPass=?");

			ps.setString(1, a);
			ps.setString(2, b);

			boolean result = ps.execute();
			ResultSet set = ps.getResultSet();
			if (result) {
				System.out.println("The user has successfully logged-in");

				RequestDispatcher rd = request.getRequestDispatcher("/home_page.html");
				rd.include(request, resp);
			}
			System.out.println(set);

//			PrintWriter out = resp.getWriter();
//			out.println("<input style='text-align: centre;margin-left: 7cm;' type='submit' value='logout'>");
//			out.println("</form>");
		} catch (Exception b2) {
			System.out.println(b2);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

	}
//		out.println("<form action='uportal' method='post' style='text-align: centre;margin-left: 10cm;'>");
//		out.println("<h1 >Welcome to User Management Systems</h1>");
//		out.println("<input style='text-align: centre;margin-left: 7cm;' type='submit' value='logout'>");
//		out.println("</form>");

}
