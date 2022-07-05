package io.nzbee.unit.domain.beans.tag;


import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Service

public class TagDoBeanFactory implements ITagDoBeanFactory {

	@Override
	public TagFacetView getBean() {
		TagFacetView tfv = new TagFacetView();
		tfv.setTagCode("TST01");
		tfv.setTagDesc("test tag");
		tfv.setProductCount(Long.valueOf(20).intValue());
		tfv.setLocale(Constants.localeENGB);
		return tfv;
	}
	
}
