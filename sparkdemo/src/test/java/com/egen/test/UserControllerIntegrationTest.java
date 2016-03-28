package com.egen.test;

import com.egen.userMgmt.Main;
import com.google.gson.Gson;

import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class UserControllerIntegrationTest {

	@BeforeClass
	public static void beforeClass() {
		Main.main(null);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void aNewUserShouldBeCreated() {

		TestResponse res = request(
				"POST",
				"/createUser?firstname=Cathy&lastname=Springs&email=Cathy@gmail.com&link=http://wkjdfbdskjdskf.com&street=Lee Land Street&city=San Jose&country=USA&state=CA&zip=98087&compName=ccc&compLink=ccclink");
		Map<String, String> json = res.json();
		assertEquals("Cathy", json.get("firtsname"));
		assertEquals("Springs", json.get("lastname"));
		assertEquals("Cathy@gmail.com", json.get("email"));
		assertEquals("http://wkjdfbdskjdskf.com", json.get("link"));
		assertEquals("Lee Land Street", json.get("street"));
		assertEquals("San Jose", json.get("city"));
		assertEquals("USA", json.get("state"));
		assertEquals("CA", json.get("country"));
		assertEquals("98087", json.get("zip"));
		assertNotNull(json.get("id"));
	}

	private TestResponse request(String method, String path) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String, String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
}
