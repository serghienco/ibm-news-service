package com.serghienco.ibm.newsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Identifiable<Long> {

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Size(min = 2, max = 200)
    @Column(length = 200, nullable = false)
    private String firstName, lastName;

    @Getter
    @Setter
    @Size(min = 2, max = 200)
    @Column(length = 200, nullable = false)
    private String department;

    @Getter
    @Setter
    @Size(min = 2, max = 200)
    @Column(length = 200, nullable = false)
    private String jobRole;

    @Getter
    @Setter
    @Size(min = 1, max = 500)
    @Column(nullable = false, length = 500)
    private String address;

    @Getter
    @Setter
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String city;

    @Getter
    @Setter
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2)
    private String state;

    @Getter
    @Setter
    @Positive
    private long zip;

    @Getter
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "employee")
    private List<EmployeeLanguage> languages;
}
