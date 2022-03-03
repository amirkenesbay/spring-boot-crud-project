package com.mycompany.controller;

import com.mycompany.entity.Student;
import com.mycompany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/students/new")
    public String showNewForm(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add new Student");
        return "student_form";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes ra){
        service.save(student);
        ra.addFlashAttribute("message", "The student has been saved successfully");
        return "redirect:/students";
    }
}
