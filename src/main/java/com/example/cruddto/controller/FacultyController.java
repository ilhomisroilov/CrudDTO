package com.example.cruddto.controller;

import com.example.cruddto.dto.FacultyDTO;
import com.example.cruddto.entity.Faculty;
import com.example.cruddto.entity.University;
import com.example.cruddto.repository.FacultyRepository;
import com.example.cruddto.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    @RequestMapping(value = "/faculty/{university_id}", method = RequestMethod.GET)
    public List<Faculty> facultyList(@PathVariable Integer university_id){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(university_id);
        return allByUniversityId;
    }

    @RequestMapping(value = "/faculty")
    public List<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }


    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public String addFaculty(@RequestBody FacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        Optional<University> byId = universityRepository.findById(facultyDTO.getUniversity_id());
        if (byId.isPresent()){
            faculty.setUniversity(byId.get());
            facultyRepository.save(faculty);
            return "Faculty added";
        }
        else return "University not found!!!";

    }

    @RequestMapping(value = "/faculty{id}", method = RequestMethod.POST)
    public Optional<Faculty> faculty(@PathVariable Integer id){
        Optional<Faculty> byId = facultyRepository.findById(id);
        return byId;
    }

    @RequestMapping(value = "/faculty{id}", method = RequestMethod.DELETE)
    public String deleteFaculty(@PathVariable Integer id){
        facultyRepository.deleteById(id);
        return "Faculty deleted";
    }

    @RequestMapping(value = "/faculty{id}", method = RequestMethod.PUT)
    public String updateFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO){
        List<Faculty> facultyList = facultyRepository.findAll();
        Faculty faculty = new Faculty();
        for (Faculty f : facultyList){
            if(f.getId()==id){
                faculty.setName(facultyDTO.getName());
                faculty.setUniversity(faculty.getUniversity());
                facultyRepository.save(faculty);
                return "Faculty updated";
            }
        }
        return "Faculty not found!!!";
    }

}
