package com.sap.aish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.aish.model.Student;
import com.sap.aish.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping(path="/add",
			method=RequestMethod.POST,
			consumes=org.springframework.http.MediaType.APPLICATION_JSON_VALUE
			)
	public boolean addRecord(@RequestBody Student student){
		return studentService.addStudentRecord(student);
	}
	
	@RequestMapping(path="/delete/{id}",
			method=RequestMethod.GET
			)
	public boolean deleteRecord(@PathVariable int id){
		return studentService.deleteStudentRecord(id);
	}
	
	@RequestMapping(path="/all",
			method=RequestMethod.GET,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE
			)
	public List<Student> getAll(){
		return studentService.getAllStudents();
	}
	
}
