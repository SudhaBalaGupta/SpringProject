import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

@WebServlet("/login")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String n = request.getParameter("userName");
		String p = request.getParameter("userPass");
		String e = request.getParameter("userEmail");

		System.out.println(n + " " + p + " " + e);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Exp", "Sudha", "12345");

			PreparedStatement ps = con.prepareStatement("Insert into login values(?,?,?)");

			ps.setString(1, n);
			ps.setString(2, p);
			ps.setString(3, e);

			int i = ps.executeUpdate();
			if (i > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/home2.html");
				rd.include(request, resp);
			}

			else
				out.print("You are already registered....");

		} catch (Exception e2) {
			System.out.println(e2);
		}

		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

	}
//		out.println("<br>");
//		out.println("<br>");
//		out.println("<br>");
//		out.println("<br>");
//		out.println("<form action='uportal' method='post' style='text-align: centre;margin-left: 10cm;'>");
//		out.println("<h1>Welcome to User Management Systems</h1>");
//		out.println("<input style='text-align: centre;margin-left: 6cm;' type='submit' value='log In'>");
//		out.println("<br>");
//		out.println(
//				"<br><input style='text-align: centre;margin-left: 5cm;' type='submit' value='New User? Sign up!'>");
//		out.println("</form>");

}
