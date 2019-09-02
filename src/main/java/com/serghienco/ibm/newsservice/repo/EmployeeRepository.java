package com.serghienco.ibm.newsservice.repo;

import com.serghienco.ibm.newsservice.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "employees", collectionResourceRel = "employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    List<Employee> findByLastName(String lastName);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
