package com.serghienco.ibm.newsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recommended_news")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecommendedNews {

    public RecommendedNews(@NonNull News news, @NonNull Employee employee, @NonNull LocalDate publishDate) {
        this.news = news;
        this.employee = employee;
        this.publishDate = publishDate;
    }

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "news_id")
    @Getter
    @Setter
    private News news;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id")
    @Getter
    @Setter
    private Employee employee;

    @Column(nullable = false)
    private LocalDate publishDate;
}
