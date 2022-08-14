package io.nzbee.resources.category;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import io.nzbee.controllers.CategoryController;
import io.nzbee.resources.ISimpleResourceAssembler;

@Component
public class CategoryModelAssembler implements ISimpleResourceAssembler<CategoryLinkResource> {


	@Override
	public CategoryLinkResource toModel() {
		CategoryLinkResource cm =  new CategoryLinkResource();
		Link l1 = linkTo(methodOn(CategoryController.class).getProductCategories(null, null)).withRel("categories");
		cm.add(l1);
		return cm;
	}


}