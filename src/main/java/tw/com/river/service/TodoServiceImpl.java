package tw.com.river.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.river.bean.Todo;
import tw.com.river.mapper.TodoMapper;
import tw.com.river.service.exception.DataNotFoundException;

@Service("todoService")
public class TodoServiceImpl implements ITodoService {

	@Resource(name="todoMapper")
	private TodoMapper todoMapper;
	
	public Integer add(Todo todo) {
		if(todo.getTodo() == null || todo.getTodo().isEmpty()) {
			throw new DataNotFoundException("待辦事項不可為空!");
		}
		todoMapper.insert(todo);
		Integer id = todo.getId();
		return id;
	}

	public List<Todo> getAllItems(Integer uid) {
		return todoMapper.getAllItems(uid);
	}
	
	public Todo getSingleItem(Integer uid, Integer id) {
		return todoMapper.getSingleItem(uid, id);
	}

	public Integer deleteItem(Integer uid, Integer id) {
		Todo todoItem = getSingleItem(uid, id);
		if(todoItem == null) {
			throw new DataNotFoundException("該筆紀錄已刪除!");
		}
		Integer deletedItemId = todoItem.getId();
		todoMapper.delete(uid, id);
		
		return deletedItemId;
	}

	public Integer clearList(Integer uid) {
		return todoMapper.deleteAll(uid);
	}

	

}
