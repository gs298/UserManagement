package com.egen.userMgmt;

import static spark.Spark.*;
import static com.egen.userMgmt.JsonUtil.*;

import java.util.Date;
import java.util.UUID;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import spark.ResponseTransformer;
import spark.Route;

public class UserController {
	static String regexName = "[A-Za-z]+";

	public UserController(final UserService userService) {

		get("/getAllUsers", (req, res) -> userService.getAllUsers(), json());

		put("/update",
				(req, res) -> {

					String str = userService.updateUser(req.queryParams("id"),
							req.queryParams("field"), req.queryParams("val"));

					return str;
				}, json());

		post("/createUser",
				(req, res) -> {

					User user = new User();
					user.setId(req.queryParams("id"));
					user.setFirstName(req.queryParams("firstname"));
					user.setLastName(req.queryParams("lastname"));
					user.setEmail(req.queryParams("email"));
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					System.out.println(dateFormat.format(date));
					user.setDateCreated(dateFormat.format(date));
					user.setProfilePic(req.queryParams("link"));

					Address add = new Address();
					add.setStreet(req.queryParams("street"));
					add.setCity(req.queryParams("city"));
					add.setCountry(req.queryParams("country"));
					add.setState(req.queryParams("state"));
					add.setZip(req.queryParams("zip"));
					user.setAddress(add);

					Company comp = new Company();
					comp.setName(req.queryParams("compName"));
					comp.setWebsite(req.queryParams("compLink"));
					user.setCompany(comp);

					return userService.createUser(user);
				}, json());

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(404);
			res.body(toJson(new ResponseError(e)));
		});
	}

}
