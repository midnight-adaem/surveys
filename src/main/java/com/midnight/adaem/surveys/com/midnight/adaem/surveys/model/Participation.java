package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@JsonPropertyOrder({ "memberId", "surveyId", "statusId", "length" })
//Member Id,Survey Id,Status,Length
public class Participation {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @JsonProperty("Member Id")
    private Long memberId;

    @NonNull
    @JsonProperty("Survey Id")
    private Long surveyId;

    @NonNull
    @JsonProperty("Status")
    private Long statusId;

    @JsonProperty("Length")
    private Long length;

}
