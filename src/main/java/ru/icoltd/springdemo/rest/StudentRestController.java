package ru.icoltd.springdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.icoltd.springdemo.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for '/students' - return list of all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> theStudents = new ArrayList<>();
        theStudents.add(new Student("Lionel", "Messy"));
        theStudents.add(new Student("Kevin", "De Bruyne"));
        theStudents.add(new Student("Andrey", "Shevchenko"));
        return theStudents;
    }
}
