package com.optimum.employeedashboard.model;

import java.util.List;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.dao.EmployeeDao;


@Service
public class CacheHelper {
	
	// @Autowired
	// EmployeeDao employeeDao;
	
	// Cache is a hashmap of K-V pair

    private CacheManager cacheManager;
    private Cache<Integer, Employee> employeeCache;
    private Cache<Integer, Question> questionCache;
 
    public CacheHelper() {
    	
    	// initialized constructor to build caches
        cacheManager = CacheManagerBuilder
          .newCacheManagerBuilder().build();
        cacheManager.init();
 
        // creates cache for employee accounts
        // used when admin queries the same employee account multiple times
        employeeCache = cacheManager
          .createCache("employeeAccounts", CacheConfigurationBuilder
            .newCacheConfigurationBuilder(
              Integer.class, Employee.class,
              ResourcePoolsBuilder.heap(100)));
        
        /*
        //Queries for all employee accounts in the DB
        List <Employee> EmpList = employeeDao.findAll();
        // For-each loop that inserts each employee
        // inside the cache
        for (Employee emp : EmpList) {
        	employeeCache.put(emp.getEmployeeId(), emp);
        }
        */
        
        // creates cache for questions
        // used when generating test questions
        questionCache = cacheManager
          .createCache("QuestionList", CacheConfigurationBuilder
            .newCacheConfigurationBuilder(
              Integer.class, Question.class,
              ResourcePoolsBuilder.heap(100)));
        
    }

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public Cache<Integer, Employee> getEmployeeCache() {
		return employeeCache;
	}

	public void setEmployeeCache(Cache<Integer, Employee> employeeCache) {
		this.employeeCache = employeeCache;
	}

	public Cache<Integer, Question> getQuestionCache() {
		return questionCache;
	}

	public void setQuestionCache(Cache<Integer, Question> questionCache) {
		this.questionCache = questionCache;
	}
	
    
    
     
}
