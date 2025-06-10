package com.stayen.casa.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableMongoAuditing
@EnableTransactionManagement
public class MongoConfig {
	
	/**
	 * <pre>
	 * MongoTransactionManager : 
	 * 	1. It is a Spring class used to manage MongoDB transactions.
	 * 
	 * 	2. It integrates MongoDB’s native transaction capabilities with Spring’s @Transactional.
	 * 
	 * 	3. MongoDatabaseFactory is auto-injected by Spring and provides the database connection.
	 * </pre>
	 * 
	 * @param dbFactory
	 * @return
	 */
	@Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
	    return new MongoTransactionManager(dbFactory);
	}

}
