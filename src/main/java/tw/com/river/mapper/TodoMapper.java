package tw.com.river.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tw.com.river.bean.Todo;

public interface TodoMapper {

	/**
	 * Insert a new todo item
	 * @param todo obj
	 * @return affected rows
	 */
	Integer insert(Todo todo);
	
	/**
	 * Get user's todo list
	 * @param uid user id
	 * @return return list of todo items
	 */
	List<Todo> getAllItems(Integer uid);
	
	/**
	 * Get a single todo item
	 * @param uid user id
	 * @param id item id
	 * @return todo item obj
	 */
	Todo getSingleItem(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * Delete a todo item
	 * @param uid user id
	 * @param id item id
	 * @return affected rows
	 */
	Integer delete(
			@Param("uid") Integer uid, 
			@Param("id") Integer id);
	
	/**
	 * Clear todo list
	 * @param uid user id
	 * @return affected rows
	 */
	Integer deleteAll(Integer uid);
}
