package com.optimum.employeedashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.QuestionPool;

@Repository
public interface QuestionPoolDao extends JpaRepository <QuestionPool, Integer>{ 
}
