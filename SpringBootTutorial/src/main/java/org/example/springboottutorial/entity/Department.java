package org.example.springboottutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
//    @NotBlank(message = "Department name is required")
//    @Length(max = 5, min = 3)
//    @Size(max = 5, min = 3)
//    @Email
//    @Positive
//    @Negative
//    @Future
//    @FutureOrPresent
//    @Past
//    @PastOrPresent
    private String departmentName;
    private String departmentCode;
    private String departmentAddress;
}
