package com.example.BookStoreApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreAppApplication.class, args);
	}

}

/*

url: jdbc:h2:mem:bookstoredb
http://localhost:8080/h2-console

curl -X GET http://localhost:8080/api/books

 */