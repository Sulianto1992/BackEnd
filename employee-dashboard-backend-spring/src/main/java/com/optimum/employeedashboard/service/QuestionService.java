package com.optimum.employeedashboard.service;

import com.optimum.employeedashboard.model.Question;

public interface QuestionService {

	boolean createQuestion(Question question, int poolId);
	
}
