package com.example.assignment1.controller;
import com.example.assignment1.dto.EmployeeDto;
import com.example.assignment1.exceptions.BadRequestException;
import com.example.assignment1.services.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    //    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
    private EmployeeServices employeeServices; //the use of interface rather than class is important for loose coupling

    // Constructor based  injection
    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok().body(employeeServices.getAllEmployee()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(employeeServices.getEmployeeById(id));
    }


    @PostMapping
    public ResponseEntity<EmployeeDto> createCategory(@Valid @RequestBody EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            log.error("Cannot have an ID {}", employeeDto);
            throw new BadRequestException(EmployeeController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(employeeServices.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeDto(@Valid @RequestBody EmployeeDto employeeDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(employeeServices.updateEmployee(employeeDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id) {
        employeeServices.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }


}