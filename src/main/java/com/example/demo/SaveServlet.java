package com.example.demo;

import lombok.Cleanup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

       @Cleanup
       PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String country = request.getParameter("country");

        Dog dog = new Dog();

        dog.setName(name);
        dog.setOwner(owner);
        dog.setCountry(country);

        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        int status = DogRepository.save(dog);
        //out.println(status);

        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        //out.close();
    }
}
