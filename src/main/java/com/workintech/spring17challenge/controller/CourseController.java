package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import com.workintech.spring17challenge.model.HighCourseGpa;
import com.workintech.spring17challenge.model.LowCourseGpa;
import com.workintech.spring17challenge.model.MediumCourseGpa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private LowCourseGpa lowCourseGpa;
    private MediumCourseGpa mediumCourseGpa;
    private HighCourseGpa highCourseGpa;
    private List<Course> courses;


    public CourseController(LowCourseGpa lowCourseGpa, MediumCourseGpa mediumCourseGpa, HighCourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
        this.courses = new ArrayList<>();
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courses;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        if (course == null ||
                course.getId() == null ||
                course.getName() == null ||
                course.getCredit() == null ||
                course.getGrade() == null) {
            throw new ApiException("Course fields cannot be null", HttpStatus.BAD_REQUEST);
        }

        courses.add(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            if (c.getId() == id) {
                courses.set(i, updatedCourse);
                return ResponseEntity.ok(updatedCourse);
            }
        }
        throw new ApiException("Course not found for id: " + id, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public Course deleteCourse(@PathVariable int id) {
       Course course = courses.get(id);
        courses.remove(course);
        return  course;
    }
    @GetMapping("/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name) {
        Course course = courses.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ApiException("Course not found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(course);
    }












}
