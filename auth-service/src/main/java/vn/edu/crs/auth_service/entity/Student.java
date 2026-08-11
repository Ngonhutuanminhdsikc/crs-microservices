package vn.edu.crs.auth_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    @Column(name = "ho_ten", nullable = false, length = 255)
     String hoTen;
    @Column(unique = true, length = 100)
     String mssv;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
     User user;
}