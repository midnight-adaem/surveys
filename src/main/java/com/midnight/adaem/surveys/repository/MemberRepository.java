package com.midnight.adaem.surveys.repository;

import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Members;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends CrudRepository<Members, Long> {
        //Fetch all respondents who completed the questionnaire for a given survey ID.
        @Query("SELECT m from Members m WHERE m.id in (select memberId from Participation p where p.surveyId = :surveyId AND p.statusId = 4)")
        List<Members> findAllBySurvey(@Param("surveyId") Long surveyId);


        // Fetch the list of members who can be invited to a given survey (not participated in that survey yet and are active).
        @Query("SELECT m from Members m WHERE m.isActive = true AND m.id not in (select memberId from Participation p where p.surveyId = :surveyId)")
        List<Members> findAllInvitableForSurvey(@Param("surveyId") Long surveyId);

}
