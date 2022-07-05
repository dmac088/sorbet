package io.nzbee.integration.entity.beans.inventory.location;


import org.springframework.stereotype.Service;
import io.nzbee.entity.inventory.location.InventoryLocation;

@Service

public class InventoryLocationEntityBeanFactory implements IInventoryLocationEntityBeanFactory {

	@Override
	public final InventoryLocation getBean() {
		InventoryLocation inventoryLocation = new InventoryLocation();
		
		inventoryLocation.setLocationCode("TST01");
		inventoryLocation.setLocationDesc("test location");
		inventoryLocation.setLocationIsActive(true);
		
		return inventoryLocation;
	}
	
}
