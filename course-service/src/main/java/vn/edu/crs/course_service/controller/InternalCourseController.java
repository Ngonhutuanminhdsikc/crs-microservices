package vn.edu.crs.course_service.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.service.CourseService;

@RestController
@RequestMapping("/internal/courses")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InternalCourseController {
    CourseService courseService;

    @PatchMapping("/{id}/reserve-seat")
    public CourseDTO reserveSeat(@PathVariable Long id){
        return courseService.reserveSeat(id);
    }

    @PatchMapping("/{id}/release-seat")
    public CourseDTO releaseSeat(@PathVariable Long id){
        return courseService.releaseSeat(id);
    }
}
