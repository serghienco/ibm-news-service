package com.serghienco.ibm.newsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "news")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class News implements Identifiable<Long> {

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    @Getter
    @Setter
    private String source;

    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    @Getter
    @Setter
    private String author;

    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    @Getter
    @Setter
    private String title;

    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    @Getter
    @Setter
    private String description;

    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    @Getter
    @Setter
    private String url;

    @Size(min = 1, max = 500)
    @Column(length = 500, nullable = false)
    @Getter
    @Setter
    private String urlToImage;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDate publishedAt;

    @Size(min = 1, max = 3000)
    @Column(length = 3000, nullable = false)
    @Getter
    @Setter
    private String content;

    @JsonIgnore
    @Getter
    @OneToMany(mappedBy = "news", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RecommendedNews> recommendedNews;
}
