package com.mycompany.controller;

import com.mycompany.entity.Student;
import com.mycompany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public String showStudentList(Model model){
        List<Student> studentList = service.listAll();
        model.addAttribute("studentList", studentList);
        return "students";
    }

//    @GetMapping("/users/ne")
//    public String showNewForm(){
//
//    }
}
