package com.assignment.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

public class AddressBookJSON {
	private static final String ADDRESSBOOK_JSON_FILE = "/Users/saideeksha/Desktop/eclipse-workspace/addressbook/src/AddressBook_JSON.json";

	public static void main(String[] args) throws Exception {
		AddressBookJSON addressBookJSON = new AddressBookJSON();
		addressBookJSON.writeDataJson();
		addressBookJSON.readDataJson();
	}

	// reading data
	private void readDataJson() throws IOException {
		List<Contact> personsList = new ArrayList<Contact>();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_JSON_FILE));
			personsList.addAll(Arrays.asList(new Gson().fromJson(reader, Contact[].class)));
			System.out.println(personsList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// writing data into file
	private void writeDataJson() throws IOException {
		Writer writer = Files.newBufferedWriter(Paths.get(ADDRESSBOOK_JSON_FILE));
		List<Contact> personsList = new ArrayList<>();
		personsList.add(
				new Contact("Monica", "Geller", "Manhattan", "NYC", "New York", 500001, 675341929, "monica@gmail.com"));
		personsList.add(new Contact("Joey", "Tribbiani", "Nationwide", "Dallas", "Texas", 200009, 678345216,
				"josephtribbiani@gmail.com"));
		new Gson().toJson(personsList, writer);
		writer.close();
	}

}
