package tw.com.river.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.river.bean.ResponseResult;
import tw.com.river.bean.Todo;
import tw.com.river.service.ITodoService;
import tw.com.river.service.exception.DataNotFoundException;

@Controller
@RequestMapping("/todo")
public class TodoController extends BaseController {
	
	@Resource(name="todoService")
	private ITodoService todoService;

	@RequestMapping("/list.do")
	public String showTodoList() {
		return "todos";
	}
	
	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Todo>> getTodoList(HttpSession session) {
		ResponseResult<List<Todo>> rr;
		Integer uid = getUidFromSession(session);
		List<Todo> todoList = todoService.getAllItems(uid);
		rr = new ResponseResult<List<Todo>>(1, todoList);
		return rr;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/handle_addTodo.do")
	@ResponseBody
	public ResponseResult<Void> handleAddTodo(Todo todo, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		try {
			todo.setUid(uid);
			todoService.add(todo);
			rr = new ResponseResult<Void>(1);
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0);
		}
		return rr;
	}
	
	@RequestMapping("/handle_delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDelete(Integer id, HttpSession session) {
		ResponseResult<Void> rr;
		Integer uid = getUidFromSession(session);
		todoService.deleteItem(uid, id);
		rr = new ResponseResult<Void>(1);
		return rr;
	}
	
	@RequestMapping("/handle_clear_list.do")
	@ResponseBody
	public ResponseResult<Void> handleClearList(HttpSession session) {
		Integer uid = getUidFromSession(session);
		todoService.clearList(uid);
		return new ResponseResult<Void>(1);
	}
}
