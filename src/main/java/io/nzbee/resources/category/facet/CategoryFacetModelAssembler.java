package io.nzbee.resources.category.facet;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import io.nzbee.controllers.CategoryController;
import io.nzbee.resources.controllers.BrandController;
import io.nzbee.resources.controllers.ProductResourceController;
import io.nzbee.resources.controllers.TagController;
import io.nzbee.search.facet.EntityFacetHierarchical;

@Component
public class CategoryFacetModelAssembler extends RepresentationModelAssemblerSupport<EntityFacetHierarchical, CategoryFacetModel> {

	public CategoryFacetModelAssembler() {
		super(CategoryController.class, CategoryFacetModel.class);
	}
	
	public CategoryFacetModel toModel(EntityFacetHierarchical category, String locale, String currency) {
		
		CategoryFacetModel cfm = new CategoryFacetModel(category);
		
		cfm.add(linkTo(methodOn(CategoryController.class).getChildCategoryFacets(locale, currency, category.getId(), null))
				.withRel("children"));
		
		cfm.add(linkTo(methodOn(ProductResourceController.class).getProductNavigationURI())
				.withRel("products"));
		
		cfm.add(linkTo(methodOn(BrandController.class).getBrands(locale, currency, category.getId(), null))
				.withRel("brands"));
		
		cfm.add(linkTo(methodOn(TagController.class).getTags(locale, currency, category.getId(), null))
				.withRel("tags"));
		
		cfm.add(linkTo(methodOn(CategoryController.class).getMaxPriceFacet(locale, currency, category.getId(), null))
				.withRel("maxprice"));
		
		return cfm;
	}

	@Override
	public CategoryFacetModel toModel(EntityFacetHierarchical category) {
		return new CategoryFacetModel(category);
	}

	public CollectionModel<CategoryFacetModel> toCollectionModel(List<EntityFacetHierarchical> collection, String locale, String currency) {
		return CollectionModel.of(collection.stream().map(c -> this.toModel(c, locale, currency)).collect(Collectors.toList()));
	}


}