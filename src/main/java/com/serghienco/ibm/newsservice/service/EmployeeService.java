package com.serghienco.ibm.newsservice.service;

import com.serghienco.ibm.newsservice.domain.Employee;
import com.serghienco.ibm.newsservice.domain.EmployeeLanguage;
import com.serghienco.ibm.newsservice.repo.EmployeeLanguageRepository;
import com.serghienco.ibm.newsservice.repo.EmployeeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeLanguageRepository employeeLanguageRepository;
    private final EmployeeRepository employeeRepository;
    private final NewsSourceService newsSourceService;

    @Autowired
    public EmployeeService(
            @NonNull EmployeeRepository employeeRepository,
            @NonNull EmployeeLanguageRepository employeeLanguageRepository,
            @NonNull NewsSourceService newsSourceService
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeLanguageRepository = employeeLanguageRepository;
        this.newsSourceService = newsSourceService;
    }

    public EmployeeLanguage save(long id, String language) {
        if (language.length() != 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "language length is not 2");
        }
        return employeeLanguageRepository.save(new EmployeeLanguage(getEmployeeOrThrowNotFound(id), language));
    }

    public List<EmployeeLanguage> retrieveLanguages(long id) {
        return getEmployeeOrThrowNotFound(id).getLanguages();
    }

    public List<Employee> findEmployeesByLanguage(String language) {
        return employeeLanguageRepository.findEmployeesByLanguage(language);
    }

    private Employee getEmployeeOrThrowNotFound(long id) {
        return employeeRepository.findById(id).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        });
    }
}
