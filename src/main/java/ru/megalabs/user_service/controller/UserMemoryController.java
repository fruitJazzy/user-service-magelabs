package ru.megalabs.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/memory")
public class UserMemoryController {

	@Autowired
	@Qualifier("UserMemoryImpl")
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

		userCollection.create(user);
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
		User byId = userCollection.findById(id);
		if (byId == null) {
			return new Response("not found");
		}
		return new Response(byId);
	}
}