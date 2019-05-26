package tw.com.river.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tw.com.river.bean.Todo;
import tw.com.river.service.exception.DataNotFoundException;

public interface ITodoService {

	/**
	 * Insert a new todo item
	 * @param todo obj
	 * @return affected rows
	 */
	Integer add(Todo todo);
	
	/**
	 * Get all todo items
	 * @param uid user id
	 * @return todo list
	 */
	List<Todo> getAllItems(Integer uid);
	
	/**
	 * Get a single todo item
	 * @param uid user id
	 * @param id item id
	 * @return todo item obj
	 */
	Todo getSingleItem(Integer uid, Integer id);
	
	/**
	 * Service: delete an todo item
	 * @param uid user id
	 * @param id item id
	 * @return deleted item's id
	 * @throws DataNotFoundException
	 */
	Integer deleteItem(Integer uid, Integer id);
	
	/**
	 * Service: Clear todo list
	 * @param uid user id
	 * @return affected rows
	 */
	Integer clearList(Integer uid);
}
