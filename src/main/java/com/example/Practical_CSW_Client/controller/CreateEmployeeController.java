package com.example.Practical_CSW_Client.controller ;

import com.example.Practical_CSW_Client.entity.Employee;
import com.example.Practical_CSW_Client.retofit.RetrofitGenerator;
import com.example.Practical_CSW_Client.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateEmployeeController extends HttpServlet {
    private static Employee obj;

    private final EmployeeService employeeService;

    public CreateEmployeeController() {
        employeeService = RetrofitGenerator.createService(EmployeeService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj = new Employee("Employee new",1000);
        req.setAttribute("employee", obj);
        req.getRequestDispatcher("/employee/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        obj.setName(req.getParameter("name"));
        obj.setSalary(Double.parseDouble(req.getParameter("salary")));

        employeeService.addEmployees(obj).execute();
        resp.sendRedirect("/employees");
    }
}
