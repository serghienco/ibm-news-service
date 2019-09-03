package com.serghienco.ibm.newsservice.service;

import com.serghienco.ibm.newsservice.domain.Employee;
import com.serghienco.ibm.newsservice.domain.News;
import com.serghienco.ibm.newsservice.domain.RecommendedNews;
import com.serghienco.ibm.newsservice.repo.EmployeeLanguageRepository;
import com.serghienco.ibm.newsservice.repo.NewsRepository;
import com.serghienco.ibm.newsservice.repo.RecommendedNewsRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class NewsService {

    private final NewsRepository newsRepository;
    private final RecommendedNewsRepository recommendedNewsRepository;
    private final EmployeeLanguageRepository employeeLanguageRepository;
    private final NewsSourceService newsSourceService;

    @Autowired
    public NewsService(
            @NonNull NewsRepository newsRepository,
            @NonNull RecommendedNewsRepository recommendedNewsRepository,
            @NonNull EmployeeLanguageRepository employeeLanguageRepository,
            @NonNull NewsSourceService newsSourceService) {
        this.newsRepository = newsRepository;
        this.recommendedNewsRepository = recommendedNewsRepository;
        this.employeeLanguageRepository = employeeLanguageRepository;
        this.newsSourceService = newsSourceService;
    }

    public News save(News news) {
        LocalDate recommendedDate = setPublishedAtNowIfNullAndGet(news);
        news = newsRepository.save(news);
        String language = newsSourceService.getNewsLanguage(news);
        List<Employee> employees = employeeLanguageRepository.findEmployeesByLanguage(language);
        for (Employee employee : employees) {
            recommendedNewsRepository.save(new RecommendedNews(news, employee, recommendedDate));
        }
        return news;
    }

    private LocalDate setPublishedAtNowIfNullAndGet(News news) {
        if (news.getPublishedAt() == null) {
            news.setPublishedAt(LocalDate.now());
        }
        return news.getPublishedAt();
    }
}
