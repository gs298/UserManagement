package com.egen.userMgmt;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class UserJsonWriter {

	public JsonObject ConvertToJson(User obj) {
		JsonObjectBuilder userBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonObjectBuilder companyBuilder = Json.createObjectBuilder();

		addressBuilder.add("street", obj.getAddress().getStreet())
				.add("city", obj.getAddress().getCity())
				.add("zip", obj.getAddress().getZip())
				.add("state", obj.getAddress().getState())
				.add("cuntry", obj.getAddress().getCountry());

		companyBuilder.add("name", obj.getCompany().getName()).add("website",
				obj.getCompany().getWebsite());

		userBuilder.add("id", obj.getId()).add("firtsname", obj.getFirstName())
				.add("lastname", obj.getLastName())
				.add("email", obj.getEmail()).add("address", addressBuilder)
				.add("dateCreated", obj.getDateCreated())
				.add("company", companyBuilder)
				.add("profilePic", obj.getProfilePic());

		JsonObject empJsonObject = userBuilder.build();
		return empJsonObject;
	}

}
