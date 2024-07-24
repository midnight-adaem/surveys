package com.midnight.adaem.surveys.com.midnight.adaem.surveys.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Participation {
    @Id
    private long id;

    @NonNull
    private Long memberId;
    @NonNull
    private Long surveyId;
    @NonNull
    private Long statusId;
    @NonNull
    private Long length;

}
