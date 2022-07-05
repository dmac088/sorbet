package io.nzbee.entity.bag.item.domain.regular;

import java.math.BigDecimal;

public class RegularBagItemDomainDTO {

	//physical dimensions
	private final String productUPC;
	
	private final String bagItemStatus;
	
	private final BigDecimal markdownPrice;
	
	private final BigDecimal weight;
	
	private final boolean inStock;

	public RegularBagItemDomainDTO(String productUPC,  
							String bagItemStatus,
							BigDecimal markdownPrice, 
							BigDecimal weight,
							boolean inStock) {
		super();
		this.productUPC 	= productUPC;
		this.bagItemStatus 	= bagItemStatus;
		this.markdownPrice 	= markdownPrice;
		this.weight 		= weight;
		this.inStock 		= inStock;
	}
	
	public RegularBagItemDomainDTO(String productUPC,  
			String bagItemStatus,
			BigDecimal markdownPrice) {
		super();
		this.productUPC 	= productUPC;
		this.bagItemStatus 	= bagItemStatus;
		this.markdownPrice 	= markdownPrice;
		this.weight 		= new BigDecimal(0);
		this.inStock 		= true;
	}

	public String getProductUPC() {
		return productUPC;
	}

	public String getBagItemStatus() {
		return bagItemStatus;
	}

	public BigDecimal getMarkdownPrice() {
		return markdownPrice;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public boolean isInStock() {
		return inStock;
	}

}
