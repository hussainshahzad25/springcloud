package com.mongodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectToDB {

	public static void main(String args[]) {

		MongoClient mongo = new MongoClient("localhost", 27017);

		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "myDb",
				"password".toCharArray());
		
		System.out.println("Connected to the database successfully");

		MongoDatabase database = mongo.getDatabase("myDb");
		System.out.println("Credentials ::" + credential);
		// createCollection(database);
		// retrieveCollection(database);
		 insertRecord(database);
		retrieveRecords(database);

	}

	private static void retrieveRecords(MongoDatabase database) {
		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("test");
		System.out.println("Collection sampleCollection selected successfully");

		// Getting the iterable object
		FindIterable<Document> iterDoc = collection.find();
		int i = 1;

		// Getting the iterator
		// MongoCursor<Document> it = iterDoc.iterator();
		Iterator it = iterDoc.iterator();

		while (it.hasNext()) {
			Object next = it.next();
			Gson gson = new Gson();
			String jsonInString = gson.toJson(next);
			System.out.println(jsonInString);
			Employee fromJson = gson.fromJson(jsonInString, Employee.class);
			System.out.println(fromJson);
			i++;
		}

	}

	private static void insertRecord(MongoDatabase database) {
		// Retrieving a collection
		MongoCollection<Document> collection = database.getCollection("test");
		System.out.println("Collection sampleCollection selected successfully");
		Employee e1 = new Employee("Shahzad", "Hussain", 25);
		Employee e2 = new Employee("Gaurav", "bhoparia", 25);
		Employee e3 = new Employee("ram", "nath", 25);

		Document document = new Document("firstName", e1.getFirstName())
				.append("lastName", e1.getLastName())
				.append("age", e1.getAge());

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Shahzad", "Hussain", 25));
		employees.add(new Employee("Gaurav", "bhoparia", 25));
		employees.add(new Employee("ram", "nath", 25));

		collection.insertOne(document);
		System.out.println("Document inserted successfully");

		// MongoCollection<Document> collection = database
		// .getCollection("sampleCollection");
		// System.out.println("Collection sampleCollection selected successfully");
		// Document document = new Document("title", "MongoDB").append("id", 1)
		// .append("description", "database").append("likes", 100)
		// .append("url", "http://www.tutorialspoint.com/mongodb/")
		// .append("by", "tutorials point");
		// collection.insertOne(document);
		// System.out.println("Document inserted successfully");

	}

	private static void createCollection(MongoDatabase database) {
		// Creating a collection
		database.createCollection("sampleCollection");
		System.out.println("Collection created successfully");
	}

	private static void retrieveCollection(MongoDatabase database) {
		// Retieving a collection
		MongoCollection<Document> collection = database
				.getCollection("sampleCollection");
		// Gson gson = new Gson();
		// System.out.println(gson.toJson(collection));
		System.out.println("Collection myCollection selected successfully");

	}
}
