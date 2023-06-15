package com.example.cruddto.repository;

import com.example.cruddto.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    boolean existsByGroups_Id(Integer groups_id);

    List<Student> findByGroups_Id(Integer id);
}
