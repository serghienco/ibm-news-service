package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.RecommendedNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(path = "recommended-news", collectionResourceRel = "recommended-news")
public interface RecommendedNewsRepository extends JpaRepository<RecommendedNews, Long> {

    List<RecommendedNews> findByPublishDate(
            @Param("publishedDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate publishedDate);
}
