package com.mihalsky.springdemo.rest;

import com.mihalsky.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> studentList;

    @PostConstruct
    public void loadData(){
        studentList = new ArrayList<>();

        studentList.add(new Student("Kate","Porotikova"));
        studentList.add(new Student("Roman","Mihalsky"));
        studentList.add(new Student("Kate","Zudina"));
    }

    //define endpoint for "/students" return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentList;
    }

    @GetMapping("/student/{studentId}")
    public Student getSingleStudent(@PathVariable int studentId){
        if((studentId>studentList.size())||studentId<0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return studentList.get(studentId);
    }

}
