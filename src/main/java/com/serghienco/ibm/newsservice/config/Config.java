package com.serghienco.ibm.newsservice.config;

import com.serghienco.ibm.newsservice.controller.EmployeeController;
import com.serghienco.ibm.newsservice.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Configuration
public class Config {

    @Bean
    public ResourceProcessor<PagedResources<Resource<Employee>>> employeeProcessorPagesExportLinks() {
        return new ResourceProcessor<PagedResources<Resource<Employee>>>() {
            @Override
            public PagedResources<Resource<Employee>> process(PagedResources<Resource<Employee>> resource) {
                return resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resources<Resource<Employee>>> employeeProcessorListAddExportLinks() {
        return new ResourceProcessor<Resources<Resource<Employee>>>() {
            @Override
            public Resources<Resource<Employee>> process(Resources<Resource<Employee>> resources) {
                return resources;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Employee>> employeeProcessorAddExportLinks() {
        return new ResourceProcessor<Resource<Employee>>() {
            @Override
            public Resource<Employee> process(Resource<Employee> resource) {
                resource.add(linkTo(methodOn(EmployeeController.class)
                        .getLanguages(resource.getContent().getId())).withRel("languages"));
                resource.add(linkTo(methodOn(EmployeeController.class)
                        .addLanguage(resource.getContent().getId(), null, null)).withRel("languages"));
                return resource;
            }
        };
    }
}
