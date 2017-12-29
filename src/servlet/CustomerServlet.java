package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import model.Customer;
import model.PageBean;
import service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



public class CustomerServlet extends BaseServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        if(name == null ||"".equals(name)||phone==null||"".equals(phone))
        {
        	String message="客户名称和手机号码不能为空！";
        	request.setAttribute("message", message);
        }
        else {
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customer = encoding(customer);
        customer.setId(CommonUtils.uuid());
        customerService.add(customer);
        request.setAttribute("message", "恭喜，成功添加客户");
		}
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }



    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /*
        *1.获取页面传递的pc
        * 2.给定pr的值
        * 3.使用pc和pr调用service方法，得到pageBean，保存到request域
        * 4.转发到list.jsp
        */
       /*
        * 1.得到pc
        *   如果pc参数不存在，说明pc＝1
        *   如果pc参数存在，需要转换成int类型
        */
        int pc = getPc(request);//获取当前的页数

        int pr = 10;//给定pr的值，每页10行纪录

        PageBean<Customer> pb = customerService.findAll(pc, pr);
        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
     
    }

    private int getPc(HttpServletRequest request) {
        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(value);
    }

    public void preEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Customer customer = customerService.find(id);

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
       
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);

        customerService.edit(customer);

        request.setAttribute("msg", "恭喜，编辑客户成功");
        request.getRequestDispatcher("/msg.jsp").forward(request, response);
        
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        customerService.delete(id);

        request.setAttribute("msg", "恭喜，删除客户成功");
        request.getRequestDispatcher("/msg.jsp").forward(request, response);
        
    }



    public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);


        customer = encoding(customer);

        int pc = getPc(request);
        int pr = 10;

        PageBean<Customer> pb = customerService.query(customer, pc, pr);

        pb.setUrl(getUrl(request));

        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
        

    }

    private Customer encoding(Customer customer) throws UnsupportedEncodingException {
        String name = customer.getName();
        String gender = customer.getGender();
        String phone = customer.getPhone();
        String email = customer.getEmail();

        if (name != null && !name.trim().isEmpty()) {
            name = new String(name.getBytes("ISO-8859-1"), "utf-8");
            customer.setName(name);
        }
        if (gender != null && !gender.trim().isEmpty()) {
            gender = new String(gender.getBytes("ISO-8859-1"), "utf-8");
            customer.setGender(gender);
        }
        if (phone != null && !phone.trim().isEmpty()) {
            phone = new String(phone.getBytes("ISO-8859-1"), "utf-8");
            customer.setPhone(phone);
        }
        if (email != null && !email.trim().isEmpty()) {
            email = new String(email.getBytes("ISO-8859-1"), "utf-8");
            customer.setEmail(email);
        }
        return customer;
    }

    private String getUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();//返回当前页面所在的应用的名字
        String servletPath = request.getServletPath();//返回当前页面所在目录下全名称
        String queryString = request.getQueryString();//获取查询字符串,post方法传的参数，getQueryString（）得不到.它只对get方法得到的数据有效。


        if (queryString.contains("&pc=")) {
            int index = queryString.lastIndexOf("&pc=");//返回指定字符在此字符串中最后一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
            queryString = queryString.substring(0, index);//第一个int为开始的索引，对应String数字中的开始位置，第二个是截止的索引位置，对应String中的结束位置
        }

        return contextPath + servletPath + "?" + queryString;
    }
}
