package com.egen.userMgmt;

import java.util.*;

import javax.json.Json;
import javax.json.JsonObject;

import jdk.nashorn.api.scripting.JSObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class UserService {
	private static DBCollection collection;

	public UserService() {

		try {

			MongoClient mongoClient = new MongoClient("localhost");

			DB db = mongoClient.getDB("management");
			collection = db.getCollection("Users");

			System.out.println("Connecting to MongoDB@"
					+ mongoClient.getAllAddress());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public List<User> getAllUsers() {
		DBCursor cursor = collection.find();
		List<User> userList = new ArrayList<User>();

		for (DBObject result : cursor) {

			UserBuilder obj = new UserBuilder();
			User usrObj = obj.buildUser(result);

			userList.add(usrObj);
		}

		return userList;
	}

	public User createUser(User obj) {
		if (getUserById(obj.getId())) {

			// return null;
			throw new IllegalArgumentException("User with id '" + obj.getId()
					+ "' already exists");

		} else {
			UserJsonWriter jsonObj = new UserJsonWriter();
			JsonObject empJsonObject = jsonObj.ConvertToJson(obj);
			System.out.println(empJsonObject.toString());
			DBObject dbObject = (DBObject) JSON.parse(empJsonObject.toString());

			collection.insert(dbObject);
			return obj;
		}
	}

	public static Boolean getUserById(String id) {
		BasicDBObject query = new BasicDBObject();

		query.put("id", id);
		DBCursor cursor = collection.find(query);
		System.out.println(cursor.size());
		if (cursor.size() != 0)
			return true;
		else
			return false;
	}

	public String updateUser(String id, String field, String val) {
		Boolean user = getUserById(id);
		if (user == false) {
			throw new IllegalArgumentException("No user with id '" + id
					+ "' found");
		}

		else {
			DBObject query = new BasicDBObject("id", id);
			DBObject update = new BasicDBObject();
			update.put("$set", new BasicDBObject(field, val));
			collection.update(query, update);
			return val;
		}

	}

}
