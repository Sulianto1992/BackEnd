package com.optimum.employeedashboard.service;

import java.util.List;

import com.optimum.employeedashboard.model.QuestionPool;

public interface QuestionPoolService {

	// Use case: View Question Pool

	List<QuestionPool> displayAllQuestionPool();

	// Use case: Create Question Pool

	int createQuestionPool(QuestionPool pool);

	// Use case: Edit Question Pool

	boolean addQuestionToPool(QuestionPool pool);

	// Use case: Hide Question Pool

	boolean hideQuestionPool(int poolId);

}
