package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyStatsResultsDTO {
    private Long surveyId;
    private String surveyName;
    private Long numCompleted;
    private Long numFiltered;
    private Long numRejected;
    private Double avgLength;
}
