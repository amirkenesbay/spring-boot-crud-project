package com.mycompany.repository;

import com.mycompany.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Long countById(Integer id);
}
