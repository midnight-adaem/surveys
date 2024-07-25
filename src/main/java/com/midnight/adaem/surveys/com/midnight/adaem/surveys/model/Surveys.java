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
@JsonPropertyOrder({ "id", "name", "expcCompletes", "compPts", "filteredPts" })
public class Surveys {
    @Id
    @JsonProperty("Survey Id")
    private long id;

    @NonNull
    @JsonProperty("Name")
    private String name;

    //Expected completes
    @NonNull
    @JsonProperty("Expected completes")
    private Long expcCompletes;

    //Completion points
    @NonNull
    @JsonProperty("Completion points")
    private Long compPts;

    // Filtered points
    @NonNull
    @JsonProperty("Filtered points")
    private Long filteredPts;

}
