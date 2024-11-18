package com.sap.aish.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.aish.model.Student;
import com.sap.aish.repository.StudentRepo;

@Service
public class StudentService {

	
	@Autowired
	StudentRepo studentRepo;
	
	public boolean addStudentRecord(Student student){
		
		Student s=studentRepo.findOne(student.getStdId());
		if(s==null){
			studentRepo.save(student);
			return true;
		}
		return false;
	}
	
	public boolean deleteStudentRecord(int id){
		
		Student s=studentRepo.findOne(id);
		if(s==null){
			studentRepo.delete(id);
			return true;
		}
		return false;
	}

	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
				
				
	}
	
}
