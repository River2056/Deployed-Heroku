package tw.com.river.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.river.bean.Todo;
import tw.com.river.service.ITodoService;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
	
	@Resource(name="todoService")
	private ITodoService todoService;
	
	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap, HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<Todo> list = todoService.getAllItems(uid);
		modelMap.addAttribute("todoList", list);
		
		return "index";
	}
}
