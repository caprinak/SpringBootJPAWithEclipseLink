package com.sap.aish.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sap.aish.model.Student;

@Repository
public interface StudentRepo extends  JpaRepository<Student, Integer>{

	List<Student> findAll();
}
