package vn.edu.crs.course_service.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.repository.CourseRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CourseService {
    CourseRepository courseRepository;

    public List<CourseDTO> getAll() {
        return courseRepository
                .findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Khong tim thay mon hoc co id=" + id));
        return toDTO(course);
    }

    public CourseDTO create(CourseDTO courseDTO) {
        if (courseRepository.existsByTenMonHocIgnoreCase(courseDTO.getTenMonHoc())) {
            throw new IllegalArgumentException("Ten mon hoc nay da ton tai");
        }

        Course course = new Course();
        course.setTenMonHoc(courseDTO.getTenMonHoc());
        course.setSoTinChi(courseDTO.getSoTinChi());
        course.setSoChoToiDa(courseDTO.getSoChoToiDa());

        if (courseDTO.getSoChoConLai() != null) {
            course.setSoChoConLai(courseDTO.getSoChoConLai());
        } else {
            course.setSoChoConLai(courseDTO.getSoChoToiDa());
        }

        Course savedCourse = courseRepository.save(course);
        return toDTO(savedCourse);
    }

    public CourseDTO update(CourseDTO courseDTO, Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Khong tim thay mon hoc co id=" + id));

        course.setTenMonHoc(courseDTO.getTenMonHoc());
        course.setSoTinChi(courseDTO.getSoTinChi());
        course.setSoChoToiDa(courseDTO.getSoChoToiDa());

        if (courseDTO.getSoChoConLai() != null) {
            course.setSoChoConLai(courseDTO.getSoChoConLai());
        } else {
            course.setSoChoConLai(courseDTO.getSoChoToiDa());
        }

        Course savedCourse = courseRepository.save(course);
        return toDTO(savedCourse);
    }

    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException("Khong tim thay mon hoc co id=" + id);
        }
        courseRepository.deleteById(id);
    }

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTenMonHoc(),
                course.getSoTinChi(),
                course.getSoChoToiDa(),
                course.getSoChoConLai()
        );
    }
}