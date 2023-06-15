package com.example.cruddto.controller;


import com.example.cruddto.dto.GroupDTO;
import com.example.cruddto.entity.Faculty;
import com.example.cruddto.entity.Groups;
import com.example.cruddto.repository.FacultyRepository;
import com.example.cruddto.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    FacultyRepository facultyRepository;

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public String addGroup(@RequestBody GroupDTO groupDTO){
        Groups groups = new Groups();
        Faculty faculty = new Faculty();
        if (groupRepository.existsByFaculty_Id(groupDTO.getFaculty_id())){
            groups.setName(groupDTO.getName());
            Optional<Faculty> byId = facultyRepository.findById(groupDTO.getFaculty_id());
            groups.setFaculty(byId.get());
            groupRepository.save(groups);
            return "Group added";
        }
        else return "Faculty not found!!!";
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public List<Groups> groupsList(){
        return groupRepository.findAll();
    }

    @RequestMapping(value = "/group/{faculty_id}", method = RequestMethod.GET)
    public List<Groups> groupsListId(@PathVariable Integer faculty_id){
        List<Groups> byFaculty_id = groupRepository.findByFaculty_id(faculty_id);
        return byFaculty_id;
    }


}
