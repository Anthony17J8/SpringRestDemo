package ru.icoltd.springdemo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.icoltd.springdemo.entity.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostConstruct to load the student data
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Lionel", "Messy"));
        theStudents.add(new Student("Kevin", "De Bruyne"));
        theStudents.add(new Student("Andrey", "Shevchenko"));
    }

    // define endpoint for '/students' - return list of all students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define endpoint for '/students/{studentId} - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return theStudents.get(studentId);
    }
}
