package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.Employee;
import com.serghienco.ibm.newsservice.domain.EmployeeLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface EmployeeLanguageRepository extends JpaRepository<EmployeeLanguage, Long> {

    @Query("select el.employee from EmployeeLanguage el WHERE el.language = ?1")
    List<Employee> findEmployeesByLanguage(String language);
}
