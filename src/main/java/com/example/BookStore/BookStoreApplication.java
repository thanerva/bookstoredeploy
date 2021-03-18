package com.example.BookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.BookStore.domain.Book;
import com.example.BookStore.domain.BookRepository;
import com.example.BookStore.domain.Category;
import com.example.BookStore.domain.CategoryRepository;
import com.example.BookStore.domain.User;
import com.example.BookStore.domain.UserRepository;



@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository categories, UserRepository users) {
		return (args) -> {
			
			log.info("insert a few example books");
			
			
			Category tietokirja = new Category("tietokirja");				
			Category taitokirja = new Category("taitokirja");	

			categories.save(tietokirja);
			categories.save(taitokirja);
			
			categories.save(new Category("koirakirja"));
			categories.save(new Category("kissakirja"));
			categories.save(new Category("kalakirja"));
			categories.save(new Category("lintukirja"));
			
			users.deleteAll(); //Clearing previous users so heroku doesn't cause clutter
			repository.save(new Book("Testi titteli", "testi author", 1992, "100", 2, tietokirja ));
			repository.save(new Book("Testi titteli2", "testi author2", 1993, "1001", 3, taitokirja));
			
			users.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"));
			users.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));
			
		};
		
	}

}