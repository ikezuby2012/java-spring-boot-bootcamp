package com.example.demo;

import com.example.demo.utils.Sort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		int number = 10;
		int[] numbers = {1, 4, 5};
		Sort newSort = new Sort(number, numbers);
		System.out.println("this is the array -- "+ Arrays.toString(newSort.getSortItems()));
	}
}
