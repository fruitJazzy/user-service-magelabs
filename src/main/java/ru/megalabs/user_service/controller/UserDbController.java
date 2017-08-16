package ru.megalabs.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.megalabs.user_service.entity.Response;
import ru.megalabs.user_service.entity.User;
import ru.megalabs.user_service.service.UserRepository;

import static ru.megalabs.user_service.util.Utils.isValidUserFields;

/**
 * Created by fruitjazzy on 16.08.17.
 */
@RestController
@RequestMapping("/db")
public class UserDbController {

	@Autowired
	@Qualifier("UserDbImpl")
	private UserRepository userCollection;

	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Response create(@RequestBody User user) {
		if (!isValidUserFields(user)) {
			return new Response("Not valid user fields");
		}

		try {
			userCollection.create(user);
		}
		catch (DuplicateKeyException e) {
		return new Response("duplicate key");
		}
		return new Response("done");
	}

	@RequestMapping(
			value = "/getAll",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Response getAll() {
		return new Response(userCollection.getAll());
	}

	@RequestMapping(
			value = "/find/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Response findById(@PathVariable("id") String id) {
		if (id == null) {
			return new Response("not id");
		}
		User byId;
		try {
			 byId = userCollection.findById(id);
		}
		catch (EmptyResultDataAccessException e) {
			return new Response("not found");
		}
		return new Response(byId);
	}
}
