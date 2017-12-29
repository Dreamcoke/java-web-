package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import cn.itcast.servlet.BaseServlet;
import service.CustomerService;
import service.UserService;
import dao.UserDao;
import model.Customer;
import model.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -218057050017718904L;
	String message="用户名或者密码错误";
	UserDao userDao = new UserDao();
	private UserService userService = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		User user=userService.find(username);
		if(user!=null&&user.getpassword().equals(password)){
			
			request.getRequestDispatcher("/frame.jsp").forward(request, response);
		}else{
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
	}
	
}
