package io.nzbee.view.product.physical.full;

import java.math.BigDecimal;

public class PhysicalProductFullView {
	
	private String productUPC;

	private String productDesc;
	
	private BigDecimal productRetail;
	
	private BigDecimal productMarkdown;
	
	private String productType;
	
	private String brandDesc;
	
	private boolean inStock;
		
	private BigDecimal weight;
	
	private String productImage;
	
	private String productLongDesc;

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public BigDecimal getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(BigDecimal productRetail) {
		this.productRetail = productRetail;
	}

	public BigDecimal getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(BigDecimal productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal bigDecimal) {
		this.weight = bigDecimal;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		this.productLongDesc = productLongDesc;
	}

}

