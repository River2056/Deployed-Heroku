package tw.com.river.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tw.com.river.bean.User;
import tw.com.river.mapper.UserMapper;
import tw.com.river.service.exception.PasswordNotMatchException;
import tw.com.river.service.exception.UserNotFoundException;
import tw.com.river.service.exception.UsernameAlreadyExistsException;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	public Integer register(User user) {
		User u = findUserByUsername(user.getUsername());
		if(u != null) {
			throw new UsernameAlreadyExistsException("用戶名重複, 請改用重試!");
			
		}
		
		insert(user);
		Integer userId = findUserByUsername(user.getUsername()).getId();
		return userId;
		
	}
	
	public User login(String username, String password) {
		User user = findUserByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("查無此用戶, 請先註冊!");
			
		} else {
			if(user.getPassword().equals(password)) {
				return user;
			} else {
				throw new PasswordNotMatchException("密碼不正確, 請重新檢查!");
				
			}
			
		}
		
	}
	
	public Integer insert(User user) {
		return userMapper.insert(user);
	}

	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

	
	
	
	
}
