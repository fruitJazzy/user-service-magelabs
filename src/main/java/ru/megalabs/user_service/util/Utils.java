package ru.megalabs.user_service.util;

import ru.megalabs.user_service.entity.User;

/**
 * Created by fruitjazzy on 16.08.17.
 */
public class Utils {
	public static boolean isValidUserFields(User user) {
		if (user == null) {
			return false;
		}
		else if (user.getId() == null && user.getAge() == null && user.getName() == null && user.getSurname() == null) {
			return false;
		}
		return true;
	}

	public static int parseId(String id) {
		int num = 0;
		try {
			num = Integer.valueOf(id);
		}
		catch (NumberFormatException e) {
			System.err.println("not correct id");
		}
		return num;
	}
}
