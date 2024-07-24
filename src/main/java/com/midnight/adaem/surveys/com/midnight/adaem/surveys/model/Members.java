package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Members {
    @Id
    private long id;

    private String fullName;
    private String email;
    private Boolean isActive;

}
