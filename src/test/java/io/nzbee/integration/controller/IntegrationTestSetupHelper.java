package io.nzbee.integration.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import io.nzbee.entity.product.ProductEntity;
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import io.nzbee.Constants;

public class IntegrationTestSetupHelper {

	
	private static boolean setUpDBIsDone = false;
	private static boolean setUpIndexIsDone = false;

	public static void dbInit(DataSource database) {
		if (setUpDBIsDone) {
			return;
		}
		System.out.println("setting up");
		try (Connection con = database.getConnection()) {	
		 	ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_schema.sql"));
	    	ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/security_schema.sql"));
	    	ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/mochi_data.sql"));
	    	ScriptUtils.executeSqlScript(con, new FileSystemResource(Constants.testDbScriptPath + "/security_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUpDBIsDone = true;
	}
	
	public static void indexInit(EntityManager entityManager) {
		if (setUpIndexIsDone) {
			return;
		}
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		try {
			fullTextEntityManager.createIndexer(ProductEntity.class).batchSizeToLoadObjects(25)
					.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(12).idFetchSize(150).transactionTimeout(1800)
					.progressMonitor(new SimpleIndexingProgressMonitor()) // a MassIndexerProgressMonitor implementation
					.startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setUpIndexIsDone = true;
	}
}