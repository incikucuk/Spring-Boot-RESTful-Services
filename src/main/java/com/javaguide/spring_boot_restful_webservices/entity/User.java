package com.javaguide.spring_boot_restful_webservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity  //bu class bir sınıfı bir jpa varlığı olarak belirtir.
@Table(name = "users")   //bu annt sayesinde db'de otomatik olarak users adında bir tablo oluşturulacaktır
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //db de otomatik artırım sağlamak için
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
}
