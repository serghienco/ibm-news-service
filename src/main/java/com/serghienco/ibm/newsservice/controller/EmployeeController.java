package com.serghienco.ibm.newsservice.controller;

import com.serghienco.ibm.newsservice.domain.Employee;
import com.serghienco.ibm.newsservice.service.EmployeeService;
import lombok.NonNull;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RepositoryRestController
@ExposesResourceFor(Employee.class)
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(@NonNull EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(path = "/employees/{id}/languages",
            method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity getLanguages(@PathVariable long id) {
        return ResponseEntity.ok(Resources.wrap(service.retrieveLanguages(id)));
    }

    @RequestMapping(path = "/employees/{id}/languages/{language}",
            method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity addLanguage(@PathVariable Long id, @PathVariable String language,
                                      PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toResource(service.save(id, language)));
    }
}
