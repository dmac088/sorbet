package io.nzbee;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.globals")
public class Globals {

	private String baseURL;
	private String imagePath;
	private String indexPath;
	private String retailPriceCode;
	private String markdownPriceCode;
	private String unknownProductDesc;
	private String unknownProductImage;
	private String activeSKUCode;
	private String defaultProductRootCategoryCode;
	private int defaultPage;
	private int defaultPageSize;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getRetailPriceCode() {
		return retailPriceCode;
	}

	public void setRetailPriceCode(String retailPriceCode) {
		this.retailPriceCode = retailPriceCode;
	}

	public String getMarkdownPriceCode() {
		return markdownPriceCode;
	}

	public void setMarkdownPriceCode(String markdownPriceCode) {
		this.markdownPriceCode = markdownPriceCode;
	}

	public String getUnknownProductDesc() {
		return unknownProductDesc;
	}

	public void setUnknownProductDesc(String unknownProductDesc) {
		this.unknownProductDesc = unknownProductDesc;
	}

	public String getUnknownProductImage() {
		return unknownProductImage;
	}

	public void setUnknownProductImage(String unknownProductImage) {
		this.unknownProductImage = unknownProductImage;
	}

	public String getActiveSKUCode() {
		return activeSKUCode;
	}

	public void setActiveSKUCode(String activeSKUCode) {
		this.activeSKUCode = activeSKUCode;
	}

	public int getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(int defaultPage) {
		this.defaultPage = defaultPage;
	}

	public int getDefaultPageSize() {
		return defaultPageSize;
	}

	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public String getDefaultProductRootCategoryCode() {
		return defaultProductRootCategoryCode;
	}

	public void setDefaultProductRootCategoryCode(String defaultProductRootCategoryCode) {
		this.defaultProductRootCategoryCode = defaultProductRootCategoryCode;
	}

}
