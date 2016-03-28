package com.egen.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

import com.egen.userMgmt.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestCases extends TestCase {
	public static boolean setupOnce = false;

	@SuppressWarnings("unchecked")
	public void testUser() throws Exception {
		setUpBeforeClass();
		/*
		 * Unit Testing for creating Users:
		 */
		// http://localhost:4567/createUser?
		// firstname=Shirin&secondname=Richardo&email=shirin@gmail.com&link=http://wkjdfbdskjdskf.com&
		// street=Lee Land Street&city=San
		// Jose&country=USA&state=CA&zip=98087&compName=ccc&compLink=ccclink
		TestHttpResponse res = SendHttpRequest(
				"POST",
				"/createUser?firstname=Veena&lastname=Shah&email=shiah@gmail.com&link=http://wkjdfbdskjdskf.com&street=Lee Land Street&city=San Jose&country=USA&state=CA&zip=98087&compName=ccc&compLink=ccclink");
		User user = new Gson().fromJson(res.body, User.class);
		assertEquals(201, res.status);
		assertEquals("Cathy", user.getFirstName());
		assertEquals("Springs", user.getLastName());
		assertEquals("Cathy@gmail.com", user.getEmail());
		assertEquals("http://wkjdfbdskjdskf.com", user.getProfilePic());
		assertEquals("Lee Land Street", user.getAddress().getStreet());
		assertEquals("San Jose", user.getAddress().getCity());
		assertEquals("USA", user.getAddress().getCountry());
		assertEquals("CA", user.getAddress().getState());
		assertEquals("98087", user.getAddress().getZip());
		assertNotNull(user.getId());

		// res = SendHttpRequest("POST",
		// "/createUser?firstName=Rahey&lastName=Krisha&age=36&gender=M&phone=1234567891&zip=123&middleName=M");
		// user = new Gson().fromJson(res.body, User.class);
		// assertEquals(201, res.status);
		// assertEquals("Rahey", user.firstName);
		// assertEquals("Krisha", user.lastName);
		// assertEquals("36", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);
		// assertNotNull(user.id);

		// @SuppressWarnings("unused")
		// String id = user.id;
		// res = SendHttpRequest("GET", "/getAllUsers");
		// /*
		// Unit testing to get all the users:
		// */
		// java.lang.reflect.Type typeName = new
		// TypeToken<ArrayList<User>>(){}.getType();
		// ArrayList<User> users = (ArrayList<User>)new
		// Gson().fromJson(res.body, typeName);
		// assertEquals(200, res.status);
		// assertEquals(2, users.size());
		// user = (User) users.get(0);
		// assertEquals("john", user.firstName);
		// assertEquals("JOse", user.lastName);
		// assertEquals("26", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);
		//
		// user = (User) users.get(1);
		// assertEquals("Rahey", user.firstName);
		// assertEquals("Krisha", user.lastName);
		// assertEquals("36", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);
		//
		/*
		 * Unit testing to update user:
		 */
		// res = SendHttpRequest("PUT", "/updateUser/0?firstName=Ditz");
		// user = new Gson().fromJson(res.body, User.class);
		// assertEquals(200, res.status);
		// assertEquals("Ditz", user.firstName);
		// assertEquals("JOse", user.lastName);
		// assertEquals("26", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);
		// /*
		// Unit testing to get all the Users:
		// */
		// res = SendHttpRequest("GET", "/getAllUsers");
		// typeName = new TypeToken<ArrayList<User>>(){}.getType();
		// users = (ArrayList<User>)new Gson().fromJson(res.body, typeName);
		// assertEquals(200, res.status);
		// assertEquals(2, users.size());
		// user = (User) users.get(0);
		// assertEquals("Ditz", user.firstName);
		// assertEquals("JOse", user.lastName);
		// assertEquals("26", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);
		//
		// user = (User) users.get(1);
		// assertEquals("Rahey", user.firstName);
		// assertEquals("Krisha", user.lastName);
		// assertEquals("36", user.age);
		// assertEquals("M", user.gender);
		// assertEquals("1234567891", user.phone);
		// assertEquals("M", user.middleName);

		System.out.println("Test User create Succeeded");

	}

	private TestHttpResponse SendHttpRequest(String httpMethod,
			String urlSegment) throws IOException {
		try {
			URL url = new URL("http://localhost:4567" + urlSegment);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(httpMethod);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestHttpResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			// e.printStackTrace();
			throw e;
		}
	}

	public static void setUpBeforeClass() throws Exception {
		if (setupOnce == false) {
			setupOnce = true;
			Spark.stop();
			// App.main(null);
			Thread.sleep(500);
		}
	}
}

class TestHttpResponse {

	public final String body;
	public final int status;

	public TestHttpResponse(int status, String body) {
		this.status = status;
		this.body = body;
	}
}
