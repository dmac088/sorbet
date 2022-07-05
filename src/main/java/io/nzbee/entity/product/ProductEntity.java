package io.nzbee.entity.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.lucene.analysis.cjk.CJKBigramFilterFactory;
import org.apache.lucene.analysis.cjk.CJKWidthFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Normalizer;
import org.hibernate.search.annotations.NormalizerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.physical.entity.PhysicalProductEntity;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.shipping.entity.ShippingProductEntity;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import io.nzbee.entity.tag.TagEntity;

@Entity
@Indexed
@Table(name = "product", schema = "mochi")
@AnalyzerDef(name = Constants.localeENGB,
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, 
  params = {
	      @Parameter(name = "language", value = "English")
  })
})
@AnalyzerDef(name = Constants.localeZHHK,
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = CJKWidthFilterFactory.class),
  @TokenFilterDef(factory = CJKBigramFilterFactory.class),
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = StopFilterFactory.class)
})

@AnalyzerDef(name = "autocompleteEdgeAnalyzerENGB",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
filters = {
// Normalize token text to lowercase, as the user is unlikely to
// care about casing when searching for matches
@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
@Parameter(name = "replacement", value = " "),
@Parameter(name = "replace", value = "all") }),
@TokenFilterDef(factory = LowerCaseFilterFactory.class),
@TokenFilterDef(factory = StopFilterFactory.class),
// Index partial words starting at the front, so we can provide
// Autocomplete functionality
@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "3"),
@Parameter(name = "maxGramSize", value = "50") }) })


@AnalyzerDef(name = "autocompleteNGramAnalyzerENGB",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
// Normalize token text to lowercase, as the user is unlikely to
// care about casing when searching for matches
@TokenFilterDef(factory = WordDelimiterFilterFactory.class),
@TokenFilterDef(factory = LowerCaseFilterFactory.class),
@TokenFilterDef(factory = NGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "3"),
@Parameter(name = "maxGramSize", value = "5") }),
@TokenFilterDef(factory = PatternReplaceFilterFactory.class, params = {
@Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
@Parameter(name = "replacement", value = " "),
@Parameter(name = "replace", value = "all") })})

@AnalyzerDef(name = "autocompleteEdgeAnalyzerZHHK",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
filters = {
//Normalize token text to lowercase, as the user is unlikely to
//care about casing when searching for matches
@TokenFilterDef(factory = CJKWidthFilterFactory.class),
@TokenFilterDef(factory = CJKBigramFilterFactory.class),
@TokenFilterDef(factory = StopFilterFactory.class),
//Index partial words starting at the front, so we can provide
//Autocomplete functionality
@TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "1"),
@Parameter(name = "maxGramSize", value = "50") }) })


@AnalyzerDef(name = "autocompleteNGramAnalyzerZHHK",
//Split input into tokens according to tokenizer
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
//Normalize token text to lowercase, as the user is unlikely to
//care about casing when searching for matches
@TokenFilterDef(factory = CJKWidthFilterFactory.class),
@TokenFilterDef(factory = CJKBigramFilterFactory.class),
@TokenFilterDef(factory = NGramFilterFactory.class, params = {
@Parameter(name = "minGramSize", value = "1"),
@Parameter(name = "maxGramSize", value = "5") })})
@NormalizerDef(
		name = "sortNormalizer",
		filters = {
			@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // To handle diacritics such as "é"
			@TokenFilterDef(factory = LowerCaseFilterFactory.class)
		}
) 
public class ProductEntity implements Serializable { 
	
	private static final long serialVersionUID = 8330041694804054992L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "productIdGenerator")
	@SequenceGenerator(name="productIdGenerator", sequenceName = "product_prd_id_seq", schema="mochi",  allocationSize=1)
	@Column(name="prd_id")
	private Long productId;

	@NaturalId
	@Column(name="upc_cd", unique = true, updatable = false)
	@Field(store=Store.YES, analyze=Analyze.NO)
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	private LocalDateTime productCreateDt;

	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = {
						CascadeType.ALL
			    })
	@JoinTable(		name 				= "product_category", 
					schema				= "mochi", 
		   			joinColumns 		= @JoinColumn(name = "prd_id"), 
		   			inverseJoinColumns 	= @JoinColumn(name = "cat_id"))
	private Set<CategoryProductEntity> categories = new HashSet<CategoryProductEntity>();
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = {
						CascadeType.ALL
		        }) 
	@JoinTable(name = "product_tag", schema="mochi", 
	   joinColumns 			= @JoinColumn(name = "prd_id"), 
	   inverseJoinColumns 	= @JoinColumn(name = "tag_id"))
	@IndexedEmbedded(	prefix="product.tags.", 
						includeEmbeddedObjectId=true)
	private Set<TagEntity> tags = new HashSet<TagEntity>();

	@OneToMany(	mappedBy="product",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<ProductAttributeEntity> attributes = new HashSet<ProductAttributeEntity>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bnd_id")
	@IndexedEmbedded(prefix="product.brand.")
	private BrandEntity brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dept_id")
	@IndexedEmbedded(prefix="product.department.")
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prd_sts_id")
	@IndexedEmbedded(prefix="product.status.")
	private ProductStatusEntity productStatus;

	@OneToMany(	mappedBy="product",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	Set<ProductPriceEntity> prices = new HashSet<ProductPriceEntity>();

	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded(prefix="product.promotions.", 
					 includeEmbeddedObjectId=true)
    private Set<PromotionProductEntity> promotions = new HashSet<PromotionProductEntity>();
	
	@OneToOne(	mappedBy="physicalProduct",
				fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private PhysicalProductEntity productPhysical;
	
	@OneToOne(	mappedBy="shippingProduct",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private ShippingProductEntity productShipping;
	
	@Transient
	private CategoryProductEntity primaryCategory;
	
	@Transient 
	private boolean inStock;
	
	public ProductEntity() {
	}

	public CategoryProductEntity getPrimaryCategory() {
		return primaryCategory;
	}

    @Field(store = Store.YES, analyze 		= Analyze.NO, normalizer 	= @Normalizer(definition = "sortNormalizer"))	
	@SortableField
	@Transient
	public BigDecimal getCurrentRetailPriceHKD() {
		 Optional<ProductPriceEntity> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals(Constants.currencyHKD)
										 	&& p.getType().getCode().equals(Constants.retailPriceCode)
						 			).findFirst();
				 
		 return (priceVal.isPresent()) 
					? priceVal.get().getPriceValue()
					: BigDecimal.valueOf(0);     
	}
	
    @Field(store = Store.YES, analyze 		= Analyze.NO, normalizer 	= @Normalizer(definition = "sortNormalizer"))	
 	@SortableField
	@Transient
	public BigDecimal getCurrentRetailPriceUSD() {
		Optional<ProductPriceEntity> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals(Constants.currencyUSD)
										 	&& p.getType().getCode().equals(Constants.retailPriceCode)
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue()
				: BigDecimal.valueOf(0);   
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceHKD() {
		Optional<ProductPriceEntity> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals(Constants.currencyHKD) && 
										 	p.getType().getCode().equals(Constants.markdownPriceCode)
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue().doubleValue()
				: BigDecimal.valueOf(0).doubleValue();         
	}
	
	@Field(store=Store.YES)
	@SortableField
	@Transient
	public Double getCurrentMarkdownPriceUSD() {
		Optional<ProductPriceEntity> priceVal = 
				 this.prices.stream().filter(p ->
										 	p.getCurrency().getCode().equals(Constants.currencyUSD) && 
										 	p.getType().getCode().equals(Constants.markdownPriceCode)
						 			).findFirst();
				 
		return (priceVal.isPresent()) 
				? priceVal.get().getPriceValue().doubleValue()
				: BigDecimal.valueOf(0).doubleValue();           
	}
	
	@Facet
	@Field(analyze=Analyze.NO, store=Store.YES)
	@Transient
	public Double getCurrentMarkdownPriceHKDFacet() {
		return this.getCurrentMarkdownPriceHKD().doubleValue();
	}
	
	@Facet
	@Field(analyze=Analyze.NO, store=Store.YES)
	@Transient
	public Double getCurrentMarkdownPriceUSDFacet() {
		return this.getCurrentMarkdownPriceUSD().doubleValue();
	}
	
	@Transient
	@Fields({
			  @Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeENGB)),
			  @Field(name = "edgeNGramTitleENGB", index = Index.YES, store = Store.NO,
			  			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzerENGB")),
			  @Field(name = "nGramTitleENGB", index = Index.YES, store = Store.NO,
			  			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteNGramAnalyzerENGB"))
	})
	public String getProductDescENGB() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeENGB)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductDesc() : "Not Applicable"; 
	}
	
	@Transient
	@Fields({
		@Field(analyze = Analyze.YES, store=Store.NO, analyzer = @Analyzer(definition = Constants.localeZHHK)),
		@Field(name = "edgeNGramTitleZHHK", index = Index.YES, store = Store.NO,
			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteEdgeAnalyzerZHHK")),
		@Field(name = "nGramTitleZHHK", index = Index.YES, store = Store.NO,
			analyze = Analyze.YES, analyzer = @Analyzer(definition = "autocompleteNGramAnalyzerZHHK"))
	})
	public String getProductDescZHHK() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductDesc() : "Not Applicable"; 
	}
	
	@Transient
	@Fields({
		@Field(	analyze = Analyze.YES, 
				store=Store.NO, 
				analyzer = @Analyzer(definition = Constants.localeENGB))
	})
	public String getProductLongDescENGB() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeENGB)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductLongDesc() : "Not Applicable"; 
	}
	
	@Transient
	@Fields({
		@Field(	analyze = Analyze.YES, 
				store=Store.NO, 
				analyzer = @Analyzer(definition = Constants.localeZHHK))
	})
	public String getProductLongDescZHHK() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductLongDesc() : "Not Applicable"; 
	}
	
	@Field(store = Store.YES, analyze 		= Analyze.NO, normalizer 	= @Normalizer(definition = "sortNormalizer"))	
	@SortableField
	public String getProductDescSortENGB() {
		return this.getProductDescENGB();
	}
	
	@Field(store = Store.YES, analyze 		= Analyze.NO, normalizer 	= @Normalizer(definition = "sortNormalizer"))	
	@SortableField
	public String getProductDescSortZHHK() {
		return this.getProductDescZHHK();
	}
	
	public String getProductImageENGB() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeENGB)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductImage() : "unknown.jpg";
	}
	
	public String getProductImageZHHK() {
		Optional<ProductAttributeEntity> pa = this.getAttributes().stream().filter(a -> a.getLclCd().equals(Constants.localeZHHK)).findFirst();
		return (pa.isPresent()) ? pa.get().getProductImage() : "unknown.jpg";
	}
	

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}
	
	public Long getProductId() {
		return productId;
	}

	public Set<CategoryProductEntity> getCategories() {
		return this.categories;
	}
	
	@IndexedEmbedded(	prefix="product.categories.", 
						includeEmbeddedObjectId=true)
	public Set<CategoryEntity> getCategoriesForIndex() {
		return this.categories.stream().filter(c -> c.getCategory().getRootNode(c.getCategory()).getCategoryCode().equals(Constants.indexCategoryHierarchyRootNode)).collect(Collectors.toSet()).stream().map(c -> c.getCategory()).collect(Collectors.toSet());
	}
	
	public void setCategories(Set<CategoryProductEntity> categories) {
		this.categories = categories;
	}
	
	public Set<TagEntity> getTags() {
		return this.tags;
	}
	
	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}
	
	public Set<ProductAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUPC() {
		return productUPC;
	}

	public void setUPC(String productUPC) {
		this.productUPC = productUPC;
	}
	
	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public LocalDateTime getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(LocalDateTime productCreateDt) {
		this.productCreateDt = productCreateDt;
	}
	
	public Set<ProductPriceEntity> getPrices() {
		return this.prices;
	}

	public void setPrices(Set<ProductPriceEntity> productPrices) {
		this.prices = productPrices;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}
	
	public ProductStatusEntity getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatusEntity productStatus) {
		this.productStatus = productStatus;
	}
	
	public void addTag(TagEntity tag) {
		this.tags.add(tag);
	}
	
	public void removeTag(TagEntity tag) {
		this.getTags().remove(tag);
	}
	
	public void addProductCategory(CategoryProductEntity categoryProduct) {
		this.getCategories().add(categoryProduct);
	}
	
	public void removeProductCategory(CategoryProductEntity categoryProduct) {
		this.getCategories().remove(categoryProduct);
	}
	
	public void addProductAttribute(ProductAttributeEntity productAttribute) {
		this.getAttributes().add(productAttribute);
		productAttribute.setProduct(this);
	}
	
	public void removeProductAttribute(ProductAttributeEntity productAttribute) {
		this.getAttributes().remove(productAttribute);
		productAttribute.setProduct(null);
	}
	
	public void addProductPrice(ProductPriceEntity productPrice) {
		this.getPrices().add(productPrice);
		productPrice.setProduct(this);
	}
	
	public void removeProductPrice(ProductPriceEntity productPrice) {
		this.getPrices().remove(productPrice);
		productPrice.setProduct(null);
	}
	
	public void addCategory(CategoryProductEntity category) {
		this.categories.add(category);
		category.getProducts().add(this);
	}
	
	public void removeCategory(CategoryProductEntity category) {
		this.categories.remove(category);
		category.getProducts().remove(this);
	}
	
    public String getTypeDiscriminator() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	public Set<PromotionProductEntity> getPromotions() {
		return promotions;
	}

	public void setPromotions(Set<PromotionProductEntity> promotions) {
		this.promotions = promotions;
	}
	
	public void addPromotion(PromotionProductEntity promotion) {
		this.getPromotions().add(promotion);
		promotion.getProducts().add(this);
	}
	
	public void removePromotion(PromotionProductEntity promotion) {
		this.getPromotions().remove(promotion);
		promotion.removeProduct(this);
	}

	public PhysicalProductEntity getProductPhysical() {
		return productPhysical;
	}

	public void setProductPhysical(PhysicalProductEntity productPhysical) {
		this.productPhysical = productPhysical;
	}
	
	public ShippingProductEntity getProductShipping() {
		return productShipping;
	}

	public void setProductShipping(ShippingProductEntity productShipping) {
		this.productShipping = productShipping;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        return productUPC != null && productUPC.equals(((ProductEntity) o).getProductUPC());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getProductUPC());
    }
 
	
}
