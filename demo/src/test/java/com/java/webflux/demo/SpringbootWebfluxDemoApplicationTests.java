package com.java.webflux.demo;

import com.java.webflux.crud.api.controller.EmployeeController;
import com.java.webflux.crud.api.dto.EmployeeInputModel;
import com.java.webflux.crud.api.dto.EmployeeOutputModel;
import com.java.webflux.crud.api.facade.EmployeeFacade;
import com.java.webflux.crud.dal.model.Employee;
import com.java.webflux.crud.dal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
properties = "spring.main.web-application-type=reactive")*/
@WebFluxTest(EmployeeController.class)
//@ContextConfiguration(classes = {EmployeeFacade.class,EmployeeRepository.class})
public class SpringbootWebfluxDemoApplicationTests {


    @Autowired
    private EmployeeFacade facade;

    @Autowired
    private  EmployeeRepository repository;

    @Autowired
    private  WebTestClient webTestClient;


    @Test
    void contextLoads() {
        facade.getEmployees().subscribe(System.out::println);
    }

    @Test
    void testMono() {
        Mono<String> monoString = Mono.just("Java").log();
        monoString.subscribe(System.out::println);
    }

    @BeforeEach
    public void before() {
        System.out.println("before each test");
        repository.deleteAll().subscribe();
    }

    @Test
    public void testSaveEmployee() {
        EmployeeInputModel employeeInputModel = new EmployeeInputModel();
        employeeInputModel.setFirstName("John");
        employeeInputModel.setLastName("Doe");
        employeeInputModel.setEmail("<EMAIL>");
        webTestClient.post().uri("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(employeeInputModel), EmployeeOutputModel.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employeeInputModel.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employeeInputModel.getLastName())
                .jsonPath("$.email").isEqualTo(employeeInputModel.getEmail());
    }

    @Test
    public void testGettingSimpleEmployee() {
        Employee employeeInputModel = new Employee();
        employeeInputModel.setFirstName("Reza");
        employeeInputModel.setLastName("Rahmani");
        employeeInputModel.setEmail("reza@example.com");

        Employee employee = repository.save(employeeInputModel).block();

        webTestClient.get().uri("/api/v1/employees/{id}", Collections.singletonMap("id", employee.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(employee.getFirstName())
                .jsonPath("$.lastName").isEqualTo(employee.getLastName())
                .jsonPath("$.email").isEqualTo(employee.getEmail());

    }

    @Test
    public void testGetAllEmployees() {
        // create list employee
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setFirstName("Reza" + i);
            employee.setLastName("Rahmani" + i);
            employee.setEmail("reza" + i + "@example.com");
            employees.add(employee);
        }
        repository.saveAll(employees).subscribe();

        webTestClient.get().uri("/api/v1/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeOutputModel.class)
                .consumeWith(System.out::println);
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Yousef");
        employee.setLastName("Razavai");
        employee.setEmail("yousef@example.com");

        Employee savedEmployee = repository.save(employee).block();

        EmployeeInputModel updatedEmployee = new EmployeeInputModel();
        updatedEmployee.setFirstName("Youseffffffi");
        updatedEmployee.setLastName("Koskholiii");
        updatedEmployee.setEmail("youseffffffi@example.com");

        webTestClient.put().uri("/api/v1/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updatedEmployee), EmployeeOutputModel.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.firstName").isEqualTo(updatedEmployee.getFirstName())
                .jsonPath("$.lastName").isEqualTo(updatedEmployee.getLastName())
                .jsonPath("$.email").isEqualTo(updatedEmployee.getEmail());
    }


    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Yousfsfdsef");
        employee.setLastName("Razssfsavai");
        employee.setEmail("youserwwrwwwf@example.com");

        Employee savedEmployee = repository.save(employee).block();

        webTestClient.delete().uri("/api/v1/employees/{id}", Collections.singletonMap("id", savedEmployee.getId()))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .consumeWith(System.out::println);
    }


}
