package com.projetocoop.controller;

import com.projetocoop.entities.Course;
import com.projetocoop.entities.Student;
import com.projetocoop.repositories.CourseRepository;
import com.projetocoop.repositories.StudentRepository;
import com.projetocoop.service.CourseService;
import com.projetocoop.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (value = "/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    /**
     * Encontra o curso pelo id
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseService.findById(id).map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Altera os dados do curso desejado
     * @param id
     * @param updatedCourse
     * @return
     */
    @PutMapping (value = "/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course updatedCourse){
        return courseService.updateCourse(id, updatedCourse).map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo curso
     * @param newCourse
     * @return
     */
    @PostMapping
    public ResponseEntity<Course> insert(@RequestBody Course newCourse){
        Course course = courseService.insertCourse(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    /**
     * Deleta o curso
     * @param id
     * @return
     */
    @DeleteMapping (value ="/{id}")
    public ResponseEntity<Course> delete(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
