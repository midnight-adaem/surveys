package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@JsonPropertyOrder({ "id", "name" })
public class Statuses {
    @Id
    @JsonProperty("Status Id")
    private long id;

    @NonNull
    @JsonProperty("Name")
    private String name;

}
