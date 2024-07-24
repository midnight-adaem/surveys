package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Statuses {
    @Id
    private long id;
    @NonNull
    private String name;

}
