package com.example.Practical_CSW_Client.controller;

import com.example.Practical_CSW_Client.entity.Employee;
import com.example.Practical_CSW_Client.retofit.RetrofitGenerator;
import com.example.Practical_CSW_Client.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetEmployeeController extends HttpServlet {
    private final EmployeeService employeeService;

    public GetEmployeeController() {
        employeeService = RetrofitGenerator.createService(EmployeeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeService.getEmployees().execute().body();
        req.setAttribute("listEmployees", employees);
        req.getRequestDispatcher("/employee/list.jsp").forward(req, resp);
    }
}
