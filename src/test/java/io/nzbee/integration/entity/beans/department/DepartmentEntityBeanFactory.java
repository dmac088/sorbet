package io.nzbee.integration.entity.beans.department;


import org.springframework.stereotype.Service;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;

@Service(value = "productTypeEntityBeanFactory")

public class DepartmentEntityBeanFactory implements IDepartmentEntityBeanFactory {


	@Override
	public DepartmentEntity getBean() {
		DepartmentEntity dept = new DepartmentEntity();
		DepartmentAttribute da = new DepartmentAttribute();
		
		da.setDesc("test department");
		da.setLclCd("en-GB");
	
		dept.setDepartmentCode("TST01");
		dept.getAttributes().add(da);
		da.setDepartment(dept);
		
		return dept;
	}
	
}
