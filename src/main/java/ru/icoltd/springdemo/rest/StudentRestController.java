package ru.icoltd.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

        // check the student against the list size
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    // add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> exceptionHandler(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<StudentErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // add another exception handler to catch any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> anyExceptionHandler(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<StudentErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
