package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        List<Employee> list = employeeService.getAllEmployee();
        model.addAttribute("employees", list);
        return "list-employees";
    }

    @GetMapping("/showform")
    public String showformadd(Model themodel) {
        Employee theEmployee = new Employee();
        themodel.addAttribute("employee", theEmployee);
        return "employee-form";
    }

    @PostMapping("/save")
    public String savemployee(@ModelAttribute("employee") Employee theemp) {
        employeeService.createEmployee(theemp);
        return "redirect:/employees/list";
    }

    @GetMapping("/showupdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        Employee theEmployee = employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String DeleteData(@RequestParam("employeeId") int theId, Model theModel)
    {
        employeeService.deletebyid(theId);
        return "redirect:/employees/list";


    }
   @GetMapping("/search")
    public String searchbyid(@RequestParam("employeeId")int theId,Model theModel)
    {

        Employee theEmployee = employeeService.findById(theId);
        theModel.addAttribute("employee", theEmployee);
        return "redirect:/employees/list";


    }
}




