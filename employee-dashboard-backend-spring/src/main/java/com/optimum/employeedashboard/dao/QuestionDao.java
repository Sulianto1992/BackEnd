package com.optimum.employeedashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{

	//moved these functions to the service layer.
	
}
