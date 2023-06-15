package com.example.cruddto.repository;

import com.example.cruddto.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {



    List<Faculty> findAllByUniversityId(Integer university_id);
}
