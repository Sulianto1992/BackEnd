package com.optimum.employeedashboard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.dao.QuestionDao;
import com.optimum.employeedashboard.dao.QuestionPoolDao;
import com.optimum.employeedashboard.model.Question;
import com.optimum.employeedashboard.model.QuestionPool;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionPoolDao questionPoolDao;

	@Autowired
	QuestionDao questionDao;

	// note: QuestionPools should be initialized with 0 questions. Creating a
	// question
	// and tagging it to a questionPool will increment the noOfQn counter in the
	// questionPool.
	@Override
	public boolean createQuestion(Question question, int poolId) {

		boolean status = true;
		Optional<QuestionPool> pool = questionPoolDao.findById(poolId);
		try {
			// so that an exception is thrown if there is no such QuestionPool with poolId.
			if (pool.isPresent()) {
				QuestionPool updatedQuestionPool = pool.get();

				if (updatedQuestionPool.isHidden()) {
					System.out.println("Inactive QuestionPool!");
					return false;
				} else {
					question.setPoolId(poolId);
					questionDao.save(question);

					// increment the noOfQns counter in the corresponding QuestionPool.
					int newNumberOfQuestionsInPool = updatedQuestionPool.getNoOfQnsInPool() + 1;
					updatedQuestionPool.setNoOfQnsInPool(newNumberOfQuestionsInPool);
					questionPoolDao.save(updatedQuestionPool);
				}
			} else
				status = false;
		} catch (Exception e) {
			System.out.println("Error in Creating Question!");
			status = false;
		}
		return status;
	}

}
