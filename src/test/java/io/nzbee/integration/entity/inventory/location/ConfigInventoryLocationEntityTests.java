package io.nzbee.integration.entity.inventory.location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocationServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.inventory.location.IInventoryLocationEntityBeanFactory;
import io.nzbee.integration.entity.beans.inventory.location.InventoryLocationEntityBeanFactory;
import io.nzbee.util.inventory.InventoryLocationMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigInventoryLocationEntityTests {
	
	@Bean
	public IInventoryLocationEntityBeanFactory inventoryLocationEntityBeanFactory() {
		return new InventoryLocationEntityBeanFactory();
	}
	
	@Bean
	public IInventoryLocationService inventoryLocationService() {
		return new InventoryLocationServiceImpl();
	}
	
	@Bean
	public InventoryLocationMasterService inventoryLocationMasterService() {
		return new InventoryLocationMasterService();
	}
}
