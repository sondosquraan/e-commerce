package com.example.assignment1.services.imp;

import com.example.assignment1.dto.EmployeeDto;
import com.example.assignment1.entity.Employee;
import com.example.assignment1.exceptions.ResourceNotFoundException;
import com.example.assignment1.repository.EmployeeRepository;
import com.example.assignment1.services.EmployeeServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
    public class EmployeeServiceImp implements EmployeeServices {

        private EmployeeRepository employeeRepository;

        public EmployeeServiceImp(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }


        // convert Entity into DTO
        private EmployeeDto mapToDTO(Employee employee) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setPassword(employee.getPassword());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setDate(employee.getDate());
            return employeeDto;
        }

        // convert DTO to entity
        private Employee mapToEntity(EmployeeDto employeeDto) {
            Employee employee= new Employee();
            employee.setId(employeeDto.getId());
            employee.setName(employeeDto.getName());
            employee.setPassword(employeeDto.getPassword());
            employee.setEmail(employeeDto.getEmail());
            employee.setDate(employeeDto.getDate());

            return employee;
        }

        @Override
        public EmployeeDto createEmployee(EmployeeDto employeeDto) {
            Employee employee = mapToEntity(employeeDto);
            Employee newEmployee = employeeRepository.save(employee);

            // convert entity to DTO
            EmployeeDto employeeResponse = mapToDTO(newEmployee);
            return employeeResponse;
        }

        @Override
        public List<EmployeeDto> getAllEmployee() {
            List<Employee> employees = employeeRepository.findAll();
            return employees.stream().map(employee -> mapToDTO(employee)).collect(Collectors.toList());
        }

        @Override
        public EmployeeDto getEmployeeById(long id) {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            return mapToDTO(employee);
        }

        @Override
        public EmployeeDto updateEmployee(EmployeeDto employeeDto, long id) {
            Employee employee =employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            //employee.setId(employeeDto.getId());
            employee.setName(employeeDto.getName());
            employee.setPassword(employeeDto.getPassword());
            employee.setEmail(employeeDto.getEmail());
            employee.setDate(employeeDto.getDate());
            Employee updatedEmployee= employeeRepository.save(employee);
            return mapToDTO(updatedEmployee);
        }

        @Override
        public void deleteEmployeeById(long id) {
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
            employeeRepository.delete(employee);

        }
    }
