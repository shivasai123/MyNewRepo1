package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Employee;
import com.app.services.IEmployeeService;
@Controller
public class EmployeeController {
@Autowired
private IEmployeeService empService;
@RequestMapping("/home")
public ModelAndView homePage(){
return new ModelAndView("home");
}
@RequestMapping("/insert")
public String insert(@ModelAttribute("employee")Employee emp){
empService.createEmployee(emp);
System.out.println("hello");
return"redirect:/app/show";
}
@RequestMapping("/show")
public ModelAndView show(@ModelAttribute("employee")Employee emp){
List<Employee> employeeList=empService.showEmployeeDetails();
return new ModelAndView("home","employeeList",employeeList);
}
@RequestMapping("/delete/{empId}")
public String delete(@PathVariable("empId")int empId){
empService.deleteEmployeeById(empId);
return"redirect:/app/show";
}
@RequestMapping("/edit/{empId}")
public ModelAndView edit(@PathVariable("empId")int empId){
Employee emp=empService.loadEmployeeById(empId);
return new ModelAndView("Edit","emp",emp);
}
}