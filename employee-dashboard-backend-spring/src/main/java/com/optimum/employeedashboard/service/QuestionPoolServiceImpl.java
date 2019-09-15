package com.optimum.employeedashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.dao.QuestionDao;
import com.optimum.employeedashboard.dao.QuestionPoolDao;
import com.optimum.employeedashboard.model.Question;
import com.optimum.employeedashboard.model.QuestionPool;

@Service
public class QuestionPoolServiceImpl implements QuestionPoolService {

	@Autowired
	QuestionPoolDao questionPoolDao;

	// This method may be inefficient because I'm lazy to get a native query
	// working.
	// Currently it gets everything and filters by isHidden.
	@Override
	public List<QuestionPool> displayAllQuestionPool() {
		List<QuestionPool> questionPool = questionPoolDao.findAll();
		java.util.Iterator<QuestionPool> itr = questionPool.iterator();
		while (itr.hasNext()) {
			QuestionPool pool = itr.next();
			if (pool.isHidden())
				itr.remove();
		}
		return questionPool;
	}

	@Override
	public int createQuestionPool(QuestionPool pool) {
		questionPoolDao.save(pool);
		// getPoolId is only generated on save. Does this still work?
		try {
			return pool.getPoolId();
		} catch (Exception e) {
			return -1;
		}
	}

	// NOTE: method below does nothing for now. Can just use the createQuestion
	// method instead.
	@Override
	public boolean addQuestionToPool(QuestionPool pool) {
		return false;
	}

	@Override
	public boolean hideQuestionPool(int poolId) {
		boolean status = true;
		try {
			// Pool to be hidden
			QuestionPool pool = questionPoolDao.getOne(poolId);
			pool.setHidden(true);
			questionPoolDao.save(pool);
		} catch (Exception e) {
			System.out.println("Error in Hiding Question Pool!");
			status = false;
		}
		return status;
	}

}
