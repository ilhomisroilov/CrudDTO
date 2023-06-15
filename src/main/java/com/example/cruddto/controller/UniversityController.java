package com.example.cruddto.controller;

import com.example.cruddto.dto.UniversityAddressDTO;
import com.example.cruddto.entity.Address;
import com.example.cruddto.entity.University;
import com.example.cruddto.repository.AddressRepository;
import com.example.cruddto.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    AddressRepository addressRepository;


    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> universityList(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityAddressDTO universityAddressDTO){
        University university =  new University();
        Address address = new Address();
        boolean byName = universityRepository.existsByName(universityAddressDTO.getName());
        if (!byName) {
            university.setName(universityAddressDTO.getName());
            address.setCity(universityAddressDTO.getCity());
            address.setDistrict(universityAddressDTO.getDistrict());
            address.setStreet(universityAddressDTO.getStreet());
            university.setAddress(address);
            universityRepository.save(university);
            addressRepository.save(address);
            return "University added";
        }
        else return "Bunday nomli universitet bo'lishi mumkin";
    }

    @RequestMapping(value = "/university{id}", method = RequestMethod.POST)
    public Optional<University> university(@PathVariable Integer id){
        Optional<University> byId = universityRepository.findById(id);
        return byId;
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "University deleted";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityAddressDTO universityAddressDTO){
        List<University> universityList = universityRepository.findAll();


        University university =  new University();
        Address address = new Address();
        for (University u: universityList){
            if(u.getId()==id){
                university.setName(universityAddressDTO.getName());
                address.setCity(universityAddressDTO.getCity());
                address.setDistrict(universityAddressDTO.getDistrict());
                address.setStreet(universityAddressDTO.getStreet());
                university.setAddress(address);
                universityRepository.save(university);
                addressRepository.save(address);
                return "University updated";
            }
        }

        return "University not found!!!";
    }



}
