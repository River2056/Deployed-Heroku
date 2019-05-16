package tw.com.river.service;

import tw.com.river.bean.User;
import tw.com.river.service.exception.UsernameAlreadyExistsException;

public interface IUserService {

	/**
	 * service register method
	 * @param user object
	 * @return new user id
	 * @throws UsernameAlreadyExistsException
	 */
	Integer register(User user);
	
	/**
	 * UserService login method
	 * @param username
	 * @param password
	 * @throws UserNotFoundException user not found
	 * @throws PasswordNotMatchException password not match
	 * @return user object
	 */
	User login(String username, String password);
	
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
