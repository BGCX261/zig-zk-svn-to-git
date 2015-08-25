package com.twinstorm.zkskeleton.service.user;

import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.domain.user.UserWrapper;
import com.twinstorm.zkskeleton.domain.exception.DomainException;
import com.twinstorm.zkskeleton.service.shared.user.UserService;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.acegisecurity.userdetails.jdbc.JdbcDaoImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.util.Date;

public class UserDetailsService extends JdbcDaoImpl {

	private static final Log log = LogFactory.getLog(UserDetailsService.class);
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		if (username.equals("admin") && userService.count() > 0) {
			throw new DomainException(
					"Cannot login with the default users unless there are no users in the database");
		}

		UserWrapper wrapper = null;
		try {
			UserDetails user = super.loadUserByUsername(username);
			CustomUser propertyUser = userService.findByUserName(username);

			propertyUser.setLastLogin(new Date());
			userService.save(propertyUser);

			wrapper = new UserWrapper(user.getUsername(), user
					.getPassword(), user.isEnabled(), user.getAuthorities(),
					propertyUser);
			
		} catch (UsernameNotFoundException e) {
			log.debug("User not found ...");
		} catch (DataAccessException e) {
			log.error("Data access failed ...", e);
		}
		return wrapper;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
