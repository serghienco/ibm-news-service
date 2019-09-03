package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.News;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "news", collectionResourceRel = "news")
public interface NewsRepository extends PagingAndSortingRepository<News, Long> {

}
