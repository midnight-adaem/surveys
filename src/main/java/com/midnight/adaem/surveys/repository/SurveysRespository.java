package com.midnight.adaem.surveys.repository;

import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.PointsDTO;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Surveys;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.SurveyStatsDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveysRespository extends CrudRepository<Surveys, Long> {

        //b. Fetch all surveys that were completed by a given member ID. (participation)
        // TODO: move status codes to enum or constant
        @Query("SELECT s from Surveys s WHERE s.id in (select surveyId from Participation p WHERE p.statusId = 4 AND p.memberId = :memberId )")
        List<Surveys> findAllCompletedSurveysByMemberId(@Param("memberId") Long memberId);


        //c. Fetch the list of points (with the related survey ID) that a member has collected so far (input is the member ID). (participation, surveys)
        @Query("SELECT new com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.PointsDTO (surveyId, completedSurveys.name, ST.name, compPts as points) from Participation P join Surveys as completedSurveys on (P.surveyId = completedSurveys.id AND P.statusId=4) join Statuses ST on (ST.id = P.statusId) where memberId = :memberId UNION SELECT new com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.PointsDTO (surveyId, filteredSurveys.name, ST.name, filteredPts as points) from Participation P join Surveys as filteredSurveys on (P.surveyId = filteredSurveys.id AND P.statusId=3) join Statuses ST on (ST.id = P.statusId) where memberId = :memberId")
        List<PointsDTO> findAllPointsByMember(@Param("memberId") Long memberId);





//        e. Fetch the list of surveys with statistics, including:
//        - Survey ID (surveys)
//        - Survey name (surveys)
//        - Number of completed questionnaires (participation)
//        - Number of filtered participants (participation)
//        - Number of rejected participants (participation)
//        - Average length of time spent on the survey (Participation.length)
        @Query("SELECT new com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.SurveyStatsDTO ( S.id, S.name, St.name, count(*), avg(length)) from Surveys S join Participation P on (S.id = P.surveyId) join Statuses St on (St.id = P.statusId) group by S.id, S.name, St.name")
        List<SurveyStatsDTO> findAllSurveysWithStats();



//        select S.id, S.name, St.name, count(*), avg(length)
//        from surveys S
//        join participation P on (S.id = P.survey_Id)
//        join statuses St on (St.id = P.status_Id)
//        group by S.id, S.name, St.name

        //or

//        select id, name,
//        (select count() from participation Pc where statusId = 4 and S.id = Pc.surveyId) as numCompleted,
//        (select count() from participation Pf where statusId = 3 and S.id = Pf.surveyId) as numFiltered,
//        (select count() from participation Pf where statusId = 2 and S.id = Pf.surveyId) as numRejected,
//        (select average(length) from participation Pf where and S.id = Pf.surveyId) as avgLength,
//        from surveys S



//        select Completed.surveyId, Completed.surveyName, completedCount, Filtered.filteredCount, Rejected.rejectedCount, Completed.avgLen
//        From
//                (select S.id surveyId, S.name surveyName, St.name, count(*) completedCount, avg(length) avgLen
//        from surveys S
//        join participation P on (S.id = P.survey_Id)
//        join statuses St on (St.id = P.status_Id)
//        where St.id = 4
//        group by S.id, S.name, St.name ) as Completed,
//(select S.id surveyId, S.name surveyName, St.name, count(*) filteredCount, avg(length) avgLen
//        from surveys S
//        join participation P on (S.id = P.survey_Id)
//        join statuses St on (St.id = P.status_Id)
//        where St.id = 3
//        group by S.id, S.name, St.name ) as Filtered,
//(select S.id surveyId, S.name surveyName, St.name, count(*) rejectedCount, avg(length) avgLen
//        from surveys S
//        join participation P on (S.id = P.survey_Id)
//        join statuses St on (St.id = P.status_Id)
//        where St.id = 2
//        group by S.id, S.name, St.name ) as Rejected


}
