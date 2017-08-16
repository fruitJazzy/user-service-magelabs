package ru.megalabs.user_service.service;

import ru.megalabs.user_service.entity.User;

import java.util.Map;

/**
 * Created by fruitjazzy on 16.08.17.
 */
public interface UserRepository {
	public void create(User user);
	public Map<String, User> getAll();
	public User findById(String id);
}
