package io.nzbee.integration.entity;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.nzbee.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes= ConfigEntityTests.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_RefreshSchema {

	 private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	 
	private final String dbScriptPath = Constants.testDbScriptPath + "";
	
    @Test
    @Rollback(false)
    public void refreshSchema() {
    	Connection con = null;
		try {
			con = database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ScriptUtils.executeSqlScript(con, new FileSystemResource(dbScriptPath + "/mochi_schema.sql"));
    	ScriptUtils.executeSqlScript(con, new FileSystemResource(dbScriptPath + "/security_schema.sql"));
  
    	LOGGER.debug((new FileSystemResource(Constants.testDbScriptPath + "/mochi_schema.sql")).getFile().getAbsolutePath());
		LOGGER.debug("Schema Refreshed!");
    }
    
    @Test
    @Rollback(false)
    public void refreshData() {
    	Connection con = null;
		try {
			con = database.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	ScriptUtils.executeSqlScript(con, new FileSystemResource(dbScriptPath + "/mochi_data.sql"));
    	ScriptUtils.executeSqlScript(con, new FileSystemResource(dbScriptPath + "/security_data.sql"));

    	LOGGER.debug("Data Refreshed!");
    }
}