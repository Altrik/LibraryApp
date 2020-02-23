package pl.mysite.Library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.mysite.Library.repository.BookRepository;

@Controller
public class UserController {
	
	@Autowired
	BookRepository bookRepo;
	
}
