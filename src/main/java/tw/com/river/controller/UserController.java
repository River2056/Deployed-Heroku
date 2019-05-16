package tw.com.river.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.river.bean.ResponseResult;
import tw.com.river.bean.User;
import tw.com.river.service.IUserService;
import tw.com.river.service.exception.PasswordNotMatchException;
import tw.com.river.service.exception.UserNotFoundException;
import tw.com.river.service.exception.UsernameAlreadyExistsException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping("/register.do")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_register.do")
	@ResponseBody
	public ResponseResult<Void> handleRegister(User user) {
		ResponseResult<Void> rr;
		try {
			userService.register(user);
			rr = new ResponseResult<Void>(1, "註冊成功!");
			
		} catch (UsernameAlreadyExistsException e) {
			rr = new ResponseResult<Void>(0, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_login.do")
	@ResponseBody
	public ResponseResult<Void> handleLogin(String username, String password, HttpSession session) {
		ResponseResult<Void> rr;
		
		try {
			User user = userService.login(username, password);
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			rr = new ResponseResult<Void>(1, "登入成功!");
			
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
			
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(-1, e);
			
		}
		
		return rr;
	}
	
	@RequestMapping("/logout.do")
	public String handleLogout(HttpSession session) {
		session.invalidate();
		String url = "../user/login.do";
		return "redirect:" + url;
	}
	
}
