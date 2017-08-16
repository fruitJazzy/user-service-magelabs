package ru.megalabs.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.megalabs.user_service.entity.User;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by fruitjazzy on 16.08.17.
 */
@Component
@Qualifier("UserMemoryImpl")
public class UserMemoryImpl implements UserRepository {

	private UserSet userSet;

	@Autowired
	public UserMemoryImpl(UserSet userSet) {
		this.userSet = userSet;
	}

	@Override
	public void create(User user) {
		userSet.getUserSet().add(user);
	}

	@Override
	public Map<String, User> getAll() {
		return userSet.getUserSet().stream()
				.collect(Collectors.toMap(User::getId, i -> i));
	}

	@Override
	public User findById(String id) {
		return userSet.getUserSet().stream()
				.filter(i -> i.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}
