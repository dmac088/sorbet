package io.nzbee.integration.entity.tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import io.nzbee.entity.tag.ITagDao;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagDaoPostgresImpl;
import io.nzbee.entity.tag.view.facet.ITagFacetDTOService;
import io.nzbee.entity.tag.view.facet.ITagFacetDao;
import io.nzbee.entity.tag.view.facet.TagFacetDTOPostgresDaoImpl;
import io.nzbee.entity.tag.view.facet.TagFacetDTOServiceImpl;
import io.nzbee.integration.entity.ConfigEntityTests;
import io.nzbee.integration.entity.beans.tag.ITagEntityBeanFactory;
import io.nzbee.integration.entity.beans.tag.TagEntityBeanFactory;
import io.nzbee.util.tag.TagMasterService;

@Configuration
@Import(ConfigEntityTests.class)
public class ConfigTagEntityTests {
	
	@Bean TagMasterService tagMasterService() {
		return new TagMasterService();
	}
	
	@Bean 
	public ITagService TagServiceImpl() {
		return new io.nzbee.entity.tag.TagServiceImpl();
	}
	
	@Bean 
	public ITagDao TagDao() {
		return new TagDaoPostgresImpl();
	}
	
	@Bean
	public ITagEntityBeanFactory tagEntityBeanFactory() {
		return new TagEntityBeanFactory();
	}
	
	@Bean
	public ITagFacetDTOService tagFacetDTOService() {
		return new TagFacetDTOServiceImpl();
	}
	
	@Bean 
	public ITagFacetDao tagFacetDao() {
		return new TagFacetDTOPostgresDaoImpl();
	}
	
}
