package com.example.cruddto.controller;

import com.example.cruddto.dto.StudentDTO;
import com.example.cruddto.entity.Address;
import com.example.cruddto.entity.Groups;
import com.example.cruddto.entity.Student;
import com.example.cruddto.repository.GroupRepository;
import com.example.cruddto.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    GroupRepository groupRepository;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> studentList(){
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/student/{groups_id}", method = RequestMethod.GET)
    public List<Student> studentListId(@PathVariable Integer groups_id){
        List<Student> byGroups_id = studentRepository.findByGroups_Id(groups_id);
        return byGroups_id;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDTO studentDTO){
        Student student = new Student();
        Address address = new Address();
        if (studentRepository.existsByGroups_Id(studentDTO.getGroup_id())){
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            address.setStreet(studentDTO.getStreet());
            address.setCity(studentDTO.getCity());
            address.setDistrict(studentDTO.getDistrict());
            student.setAddress(address);
            Optional<Groups> byId = groupRepository.findById(studentDTO.getGroup_id());
            student.setGroups(byId.get());
            studentRepository.save(student);
            return "Student added";
        }
        else return "Group not found!!!";

    }

}
