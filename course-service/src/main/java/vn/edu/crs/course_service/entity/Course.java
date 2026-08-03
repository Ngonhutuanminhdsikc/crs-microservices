package vn.edu.crs.course_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "course")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "ten_mon_hoc", length = 255, nullable = false)
    String tenMonHoc;

    @Column(name = "so_tin_chi", nullable = false)
    String soTinChi;

    @Column(name = "so_cho_toi_da", nullable = false)
    String soChoToiDa;

    @Column(name = "so_cho_con_lai", nullable = false)
    String soChoConLai;
}
