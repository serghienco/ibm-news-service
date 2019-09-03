package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.RecommendedNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@RepositoryRestResource(path = "recommended-news", collectionResourceRel = "recommended-news")
public interface RecommendedNewsRepository extends JpaRepository<RecommendedNews, Long> {

    @RestResource(path = "/published", rel = "published-date")
    Page<RecommendedNews> findByPublishDate(
            @Param("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate publishedDate, Pageable pageable);
}
