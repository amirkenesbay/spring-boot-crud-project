package com.mycompany;

import com.mycompany.entity.Student;
import com.mycompany.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testAddNew(){
        Student student = new Student();
        student.setFirstName("Amir");
        student.setLastName("Kenesbay");
        student.setPassportNumber(9907546);
        student.setExpelled(true);

        Student savedStudent = studentRepository.save(student);
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void listAll(){
        Iterable<Student> students = studentRepository.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdate(){
        Integer studentId = 1;
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.get();
        student.setPassportNumber(978546);
        studentRepository.save(student);

        Student updateStudent = studentRepository.findById(studentId).get();
        Assertions.assertThat(updateStudent.getPassportNumber()).isEqualTo(978546);
    }

    @Test
    public void testGet(){
        Integer studentId = 1;
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Assertions.assertThat(optionalStudent).isPresent();
        System.out.println(optionalStudent.get());
    }

    @Test
    public void testDelete(){
        Integer studentId = 1;
        studentRepository.deleteById(studentId);
        Optional<Student> optionalStudent = studentRepository.findById(1);
        Assertions.assertThat(optionalStudent).isNotPresent();
    }
}
