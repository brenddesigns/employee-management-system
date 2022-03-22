package com.brendzn.employeemanagementsystem.controller;

import com.brendzn.employeemanagementsystem.entity.Employee;
import com.brendzn.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    // Get all employees from database
    @GetMapping({"/showEmployees", "/", "/list"})
    public ModelAndView showEmployees() {
        ModelAndView mav = new ModelAndView("list-employees");
        mav.addObject("employees", employeeRepo.findAll());
        return mav;
    }

    // Show form to add an employee
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("employee-form");
        Employee employee = new Employee();
        mav.addObject("employee", employee);
        return mav;
    }

    // Save an employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepo.save(employee);
        return "redirect:/list";
    }

    // Show form to update employee
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
        ModelAndView mav = new ModelAndView("employee-form");
        Employee employee = employeeRepo.findById(employeeId).get();
        mav.addObject("employee", employee);
        return mav;
    }

    // Delete employee by id
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId) {
        employeeRepo.deleteById(employeeId);
        return "redirect:/list";
    }
}
