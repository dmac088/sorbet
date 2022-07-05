package io.nzbee.integration.entity.department;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.product.department.IDepartmentService;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.department.IDepartmentEntityBeanFactory;
import io.nzbee.entity.product.department.DepartmentDaoImpl;
import io.nzbee.entity.product.department.DepartmentServiceImpl;
import io.nzbee.entity.product.department.IDepartmentDao;
import io.nzbee.integration.entity.beans.department.DepartmentEntityBeanFactory;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigDepartmentEntityTests {
	
	@Bean
	public IDepartmentEntityBeanFactory DepartmentEntityBeanFactory() {
		return new DepartmentEntityBeanFactory();
	}
	
	@Bean 
	public IDepartmentService DepartmentServiceImpl() {
		return new DepartmentServiceImpl();
	}
	
	@Bean 
	public IDepartmentDao departmentDao() {
		return new DepartmentDaoImpl();
	}
	
}
