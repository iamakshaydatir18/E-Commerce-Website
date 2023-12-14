package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QueriesDao;
import com.example.demo.model.Queries;



@Service
public class QueriesServiceImpl implements QueriesService {

	@Autowired
	private QueriesDao queryDao;

	public void addQuery(Queries queries) {

		queryDao.addQuery(queries);
	}

}
