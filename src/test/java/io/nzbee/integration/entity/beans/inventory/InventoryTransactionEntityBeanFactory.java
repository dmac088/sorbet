package io.nzbee.integration.entity.beans.inventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.IInventoryTypeService;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.role.supplier.ISupplierService;
import io.nzbee.entity.role.supplier.SupplierEntity;


@Service

public class InventoryTransactionEntityBeanFactory implements IInventoryEntityBeanFactory {

	@Autowired
	private IInventoryLocationService inventoryLocationService;
	
	@Autowired
	private IInventoryTypeService inventoryTypeService;
	
	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Autowired
	private IProductService productService;
	
	@Override
	public final InventoryTransaction getBean() {
		final InventoryTransaction inventoryTransaction = new InventoryTransaction();
		
		Optional<InventoryLocation> iL 	= inventoryLocationService.findByCode("LCK01");
		Optional<InventoryType> iT 		= inventoryTypeService.findByCode("IN");
		Optional<Currency> iC 			= currencyService.findByCode(Constants.currencyHKD);
		Optional<SupplierEntity> iS 			= supplierService.findByCode("1000000002");
		Optional<ProductEntity> iP 		= productService.findByCode("30833030");
		
		inventoryTransaction.setInventoryLocation(iL.get());
		
		inventoryTransaction.setInventoryType(iT.get());
		
		inventoryTransaction.setCurrency(iC.get());
		
		inventoryTransaction.setSupplier(((Party) Hibernate.unproxy(iS.get().getSupplierRole().getRoleParty())).getPartyOrganisation());
		
		inventoryTransaction.setProduct(iP.get());
		
		inventoryTransaction.setQuantity(Long.valueOf(5));
		
		inventoryTransaction.setPrice(Double.valueOf(15.20));
		
		inventoryTransaction.setInventoryTransactionDate(LocalDateTime.parse("2020-10-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		return inventoryTransaction;
	}
	
}
