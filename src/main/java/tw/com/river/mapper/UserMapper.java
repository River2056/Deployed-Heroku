package tw.com.river.mapper;

import tw.com.river.bean.User;

public interface UserMapper {
	
	/**
	 * dao insert new user
	 * @param user object
	 * @return affected rows
	 */
	Integer insert(User user);
	
	/**
	 * Find user by id
	 * @param id 
	 * @return return a User object
	 */
	User findUserById(Integer id);
	
	/**
	 * Find user by name
	 * @param username
	 * @return return User object
	 */
	User findUserByUsername(String username);
}
