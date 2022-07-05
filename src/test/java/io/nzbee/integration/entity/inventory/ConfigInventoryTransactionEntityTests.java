package io.nzbee.integration.entity.inventory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransactionServiceImpl;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryTypeServiceImpl;
import io.nzbee.entity.role.supplier.ISupplierService;
import io.nzbee.entity.role.supplier.SupplierServiceImpl;
import io.nzbee.entity.stock.IStockOnHandService;
import io.nzbee.entity.stock.StockOnHandServiceImpl;
import io.nzbee.integration.entity.beans.inventory.InventoryTransactionEntityBeanFactory;
import io.nzbee.integration.entity.inventory.location.ConfigInventoryLocationEntityTests;
import io.nzbee.integration.entity.product.ConfigProductEntityTests;
import io.nzbee.util.inventory.InventoryMasterService;

@Configuration
@Import({ConfigInventoryLocationEntityTests.class,
		 ConfigProductEntityTests.class
		})
public class ConfigInventoryTransactionEntityTests {
	
	@Bean
	public IInventoryTransactionService inventoryTransactionService() {
		return new InventoryTransactionServiceImpl();
	}
	
	@Bean
	public InventoryTransactionEntityBeanFactory inventoryTransactionEntityBeanFactory() {
		return new InventoryTransactionEntityBeanFactory();
	}
	
	@Bean
	public IInventoryTypeService inventoryTypeService() {
		return new InventoryTypeServiceImpl();
	}
	
	@Bean
	public ISupplierService supplierService() {
		return new SupplierServiceImpl();
	}
	
	@Bean
	public InventoryMasterService inventoryMasterService() {
		return new InventoryMasterService();
	}
	
	@Bean
	public IStockOnHandService stockOnHandService() {
		return new StockOnHandServiceImpl();
	}
}
