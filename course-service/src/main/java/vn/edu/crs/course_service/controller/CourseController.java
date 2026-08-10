package vn.edu.crs.course_service.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CourseController {
    CourseService courseService;

    @GetMapping
    public Page<CourseDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return courseService.search(keyword, pageable);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@Valid @RequestBody CourseDTO courseDTO) { // Bổ sung @Valid
        return courseService.create(courseDTO);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@Valid @RequestBody CourseDTO courseDTO, @PathVariable Long id) {
        return courseService.update(courseDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { // Bổ sung @PathVariable
        courseService.deleteById(id);
    }
}