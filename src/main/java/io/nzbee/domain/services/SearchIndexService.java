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
		try {
			fullTextEntityManager.createIndexer(ProductEntity.class).batchSizeToLoadObjects(25)
			.cacheMode(CacheMode.NORMAL).threadsToLoadObjects(12).idFetchSize(150).transactionTimeout(1800)
			.progressMonitor(new SimpleIndexingProgressMonitor()) // a MassIndexerProgressMonitor implementation
			.startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
