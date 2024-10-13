package com.virtusa.banking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

public abstract class BaseRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	protected BaseRepository() {
		super();
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}
