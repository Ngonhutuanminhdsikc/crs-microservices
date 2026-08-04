package vn.edu.crs.course_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {

    Long id; // Đổi sang Long (wrapper class) thay vì primitive long

    @NotBlank(message = "Ten mon hoc khong duoc de trong")
    String tenMonHoc;

    @NotNull(message = "So tin chi khong duoc de trong")
    @Min(value = 1, message = "So tin chi phai lon hon 0")
    Integer soTinChi;

    @NotNull(message = "So cho toi da khong duoc de trong")
    @Min(value = 1, message = "So cho toi da phai lon hon 0")
    Integer soChoToiDa;

    Integer soChoConLai;
}