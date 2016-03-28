package com.egen.userMgmt;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserBuilder {

	public User buildUser(DBObject result) {

		User userObj = new User();
		Address addObj = new Address();
		Company compObj = new Company();

		userObj.setId((String) result.get("id"));
		userObj.setFirstName((String) result.get("firtsname"));
		userObj.setLastName((String) result.get("lastname"));
		userObj.setEmail((String) result.get("email"));

		DBObject add = (BasicDBObject) result.get("address");
		addObj.setStreet((String) add.get("street"));
		addObj.setCity((String) add.get("city"));
		addObj.setZip((String) add.get("zip"));
		addObj.setState((String) add.get("state"));
		addObj.setCountry((String) add.get("country"));
		userObj.setAddress(addObj);

		userObj.setDateCreated((String) result.get("dateCreated"));

		DBObject comp = (BasicDBObject) result.get("company");
		compObj.setName((String) comp.get("name"));
		compObj.setWebsite((String) comp.get("website"));
		userObj.setCompany(compObj);
		userObj.setProfilePic((String) result.get("profilePic"));

		return userObj;
	}
}
