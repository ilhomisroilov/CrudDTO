package com.example.cruddto.repository;

import com.example.cruddto.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {

    boolean existsByFaculty_Id(Integer faculty_id);


    List<Groups> findByFaculty_id(Integer faculty_id);
}
