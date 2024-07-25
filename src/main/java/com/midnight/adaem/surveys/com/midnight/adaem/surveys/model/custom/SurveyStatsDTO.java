package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyStatsDTO {
    private Long surveyId;
    private String surveyName;
    private String statusName;
    private Long count;
    private Double avgLength;

}
