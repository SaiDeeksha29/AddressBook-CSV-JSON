package com.assignment.addressbook;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCSV {
	private static final String ADDRESSBOOK_CSV_FILE = "/Users/saideeksha/Desktop/eclipse-workspace/addressbook/src/AddressBook_CSV.csv";

	public static void main(String[] args) throws Exception {
		AddressBookCSV addressBookCSV = new AddressBookCSV();
		try {
			addressBookCSV.writeCSVData();
			addressBookCSV.readCSVData();
		} catch (IOException e) {
			System.out.println("Exception is - " + e);
		}
	}

	public void writeCSVData() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(ADDRESSBOOK_CSV_FILE));) {
			StatefulBeanToCsvBuilder<Contact> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<Contact> beanWriter = builder.build();
			List<Contact> personsList = new ArrayList<>();
			personsList.add(new Contact("Joey", "Tribbiani", "Nationwide", "Dallas", "Texas", 200009, 678345216,
					"josephtribbiani@gmail.com"));
			personsList.add(new Contact("Monica", "Geller", "Manhattan", "NYC", "New York", 500001, 675341929,
					"monica@gmail.com"));
			beanWriter.write(personsList);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCSVData() throws Exception {
		try (Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_CSV_FILE));
				CSVReader csvReader = new CSVReader(reader);) {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("Address - " + nextRecord[0]);
				System.out.println("City - " + nextRecord[1]);
				System.out.println("Email - " + nextRecord[2]);
				System.out.println("First Name - " + nextRecord[3]);
				System.out.println("Last Name - " + nextRecord[4]);
				System.out.println("Phone - " + nextRecord[5]);
				System.out.println("State - " + nextRecord[6]);
				System.out.println("Zip - " + nextRecord[7]);
			}
		}
	}
}
