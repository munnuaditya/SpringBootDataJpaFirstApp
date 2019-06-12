package com.app.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Product;
import com.app.repo.ProductRepository;
@Component
public class ConsoleRunner implements CommandLineRunner {
	@Autowired
	private ProductRepository repo;

	@Override
	public void run(String... args) throws Exception {
		repo.save(new Product(10,"A",3.3));
		repo.save(new Product(11,"B",4.4));
		repo.save(new Product(12,"C",5.5));
		repo.save(new Product(12,"D",6.6));
		
		//----update all-------------------
		repo.save(new Product(13,"E",8.8));
		
		//---Bulk insert---------
		List<Product> prds = Arrays.asList(
										new Product(101,"RR",9.9),
										new Product(102,"EE",8.9),
										new Product(103,"TT",7.9),
										new Product(104,"GG",6.9)
											);
		repo.saveAll(prds);
		
		//---fetch one row
		Optional<Product> p = repo.findById(18);
		if(p.isPresent()) {
			Product prd = p.get();
			System.out.println(prd.getProdCode());
		}
		else {
			System.out.println("row not found");
		}
		
		//fetch all rows
		List<Product> list = repo.findAll();
		list.forEach(System.out::println);
		
		//delete operation
		repo.deleteById(10);
		
		//delete all rows
		repo.deleteAll();
		repo.deleteAllInBatch();

	}

}
