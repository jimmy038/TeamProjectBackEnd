package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.service.ifs.CommentService;
import com.example.PipiShrimp.vo.CommentRes;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	private CommentService service;

	@PostMapping(value = "/comment/create")
	public CommentRes create(@RequestBody Comment comment) {
		return service.create(comment);
	}
}
