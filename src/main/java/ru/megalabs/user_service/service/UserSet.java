package ru.megalabs.user_service.service;

import org.springframework.stereotype.Component;
import ru.megalabs.user_service.entity.User;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by fruitjazzy on 16.08.17.
 */
@Component
public class UserSet {
	private Set<User> userSet = new ConcurrentSkipListSet<>(new UserComparator());

	public Set<User> getUserSet() {
		return userSet;
	}

	static class UserComparator implements Comparator<User> {
		@Override
		public int compare(User o1, User o2) {
			return o2.getId().compareTo(o1.getId());
		}
	}
}
