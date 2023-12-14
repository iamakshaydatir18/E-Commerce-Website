package com.example.demo.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DAO {

	private static StandardServiceRegistry standardserviceRegistry;
	private static SessionFactory sessionFactory;

	static {

		try {

			if (sessionFactory == null) {
				standardserviceRegistry = new StandardServiceRegistryBuilder().configure().build();
				MetadataSources metadataSources = new MetadataSources(standardserviceRegistry);
				Metadata metadata = metadataSources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (standardserviceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(standardserviceRegistry);
			}
		}
	}

	public static SessionFactory getsessionFactory() {
		return sessionFactory;
	}

}