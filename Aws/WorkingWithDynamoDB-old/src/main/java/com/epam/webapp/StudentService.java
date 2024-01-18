package com.epam.webapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    // Declare the repository as final to ensure its immutability
    private final StudentRepository studentRepository;

    // Use constructor-based dependency injection
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void updateStudent(String id,Student student) {
        studentRepository.findById(id).map(s->{
            s.setEmail(student.getEmail());
            s.setMbl(student.getMbl());
            s.setName(student.getName());
            s.setNum(student.getNum());
            studentRepository.save(s);
            return s;
        }).orElseThrow(()->new RuntimeException("Not found"));


    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
