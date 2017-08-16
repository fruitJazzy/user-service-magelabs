package ru.megalabs.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.megalabs.user_service.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fruitjazzy on 16.08.17.
 */
@Repository
@Qualifier("UserDbImpl")
public class UserDbImpl implements UserRepository {

	private JdbcTemplate template;

	@Autowired
	public UserDbImpl(JdbcTemplate template) {
		this.template = template;
	}
	@Override
	public void create(User user) {
		String sql = "INSERT INTO users "+
				"(id, first_name, surname, age) VALUES (?, ?, ?, ?)";

		template.update(sql,
				new Object[]{
					user.getId(),
					user.getName(),
					user.getSurname(),
					user.getAge()
				});
	}

	@Override
	public Map<String, User> getAll() {
		String sql = "SELECT * FROM users";

		Map<String, User> resultMap = new HashMap<>();

		List<Map<String, Object>> maps = template.queryForList(sql);
		for (Map<String, Object> map : maps) {
			User user = new User();
			user.setId((String) map.get("id"));
			user.setName((String) map.get("first_name"));
			user.setSurname((String) map.get("surname"));
			user.setAge((Integer) map.get("age"));
			resultMap.put(user.getId(), user);
		}
		return resultMap;
	}


	@Override
	public User findById(String id) {
		String sql = "SELECT * FROM users WHERE id=?";

		User user = template.queryForObject(sql, new Object[]{id},
				(resultSet, i) -> {
					User user1 = new User();
					user1.setId(resultSet.getString("id"));
					user1.setName(resultSet.getString("first_name"));
					user1.setSurname(resultSet.getString("surname"));
					user1.setAge(resultSet.getInt("age"));
					return user1;
				});
		return user;
	}
}
