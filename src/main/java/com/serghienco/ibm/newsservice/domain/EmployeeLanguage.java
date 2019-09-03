package com.serghienco.ibm.newsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "employee_languages",
        uniqueConstraints = @UniqueConstraint(columnNames = {"language", "employee_id"}))
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmployeeLanguage implements Identifiable<Long> {

    public EmployeeLanguage(Employee employee, String language) {
        this.employee = employee;
        this.language = language;
    }

    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Size(min = 2, max = 2)
    @Column(nullable = false, length = 2)
    private String language;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
