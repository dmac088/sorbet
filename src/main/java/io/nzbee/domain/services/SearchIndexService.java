package io.nzbee.domain.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;

import io.nzbee.entity.product.ProductEntity;

@Service
@Transactional
public class SearchIndexService {
	
	@PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

	
	public void createSearchIndex() {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer(ProductEntity.class)
		.cacheMode(CacheMode.NORMAL)
		.progressMonitor(new SimpleIndexingProgressMonitor()) // a MassIndexerProgressMonitor implementation
		.start();
	}
}
