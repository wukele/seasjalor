package com.xxx.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.xh.entity.User;
import com.xxx.dao.UserDao;
import com.xxx.service.UserService;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private boolean checkbox = false;

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	RequestAware aware;

	ActionContext context = ActionContext.getContext();

	HttpServletRequest request = ServletActionContext.getRequest();

	JSONArray array = new JSONArray();

	UserDao ud = new UserService();
	List<User> list = new ArrayList<User>();
	User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	public String execute() throws Exception {
		User result = ud.getUser(user);
		if (null != result) {
			list = ud.getList();

			return "success";
		} else {
			return "error";
		}
	}

	public String save() {
		System.out.println(this.getUser().getAddress());
		Integer result = ud.save(user);
		System.out.println(result);
		list = ud.getList();
		return "success";
	}

	public void update() {
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("---------update---------");

		User user1 = new User();
		user1.setAddress("����");
		user1.setId(1);
		user1.setEmail("aaaa");
		user1.setAgePassword("1111");
		user1.setLoginName("admin");
		user1.setName("ssss");
		user1.setPassword("aaa");
		user1.setRole(1);
		// System.out.println("{'id':'1'}");
		// System.out.println(response);
		try {
			response.getWriter().print("{'id':1}");
			// System.out.println(array.fromObject(user1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(array.fromObject(user));

		// return "error";

	}

	public String getUserList() {
		list = ud.getList();
		return "success";
	}

	public void del() {
		request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		System.out.println("......................" + id);
		Integer rs = ud.delete(new Integer(id).intValue());
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if (rs == 0) {
				response.getWriter().println("{result:true}");
			} else {
				response.getWriter().println("{result:false}");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public String printJson() {

		return "success";
	}

}
