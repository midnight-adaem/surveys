package com.midnight.adaem.surveys.repository;

import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Surveys;
import org.springframework.data.repository.CrudRepository;

public interface SurveysRespository extends CrudRepository<Surveys, Long> {

        /*

        a. Fetch all respondents who completed the questionnaire for a given survey ID.

        b. Fetch all surveys that were completed by a given member ID. (participation)

        select s
        from surveys S
        where id in (select surveyId from Participation where memberId= :memberId)


        c. Fetch the list of points (with the related survey ID) that a member has collected so far (input is the member ID). (participation, surveys)

        select P.surveyId, sum(completedSurveys.compPts), sum(filteredSurveys.filteredPts)
        from Participation P
        join surveys as completedSurveys on (P.surveyId = completedSurveys.id AND P.status=4)
        join surveys as filteredSurveys on (P.surveyId = filteredSurveys.id AND P.status=3)
        where memberId = :memberID


        e. Fetch the list of surveys with statistics, including:
        - Survey ID (surveys)
        - Survey name (surveys)
        - Number of completed questionnaires (participation)
        - Number of filtered participants (participation)
        - Number of rejected participants (participation)
        - Average length of time spent on the survey (Participation.length)

        select id, name, St.name, count(*), average(length)
        from surveys S
        right outer join participation P on (S.id = P.surveyId)
        join status St on (St.id = S.statusId)
        group by id, name, St.name
        having status <> 1

        or

        select id, name,
        (select count() from participation Pc where statusId = 4 and S.id = Pc.surveyId) as numCompleted,
        (select count() from participation Pf where statusId = 3 and S.id = Pf.surveyId) as numFiltered,
        (select count() from participation Pf where statusId = 2 and S.id = Pf.surveyId) as numRejected,
        (select average(length) from participation Pf where and S.id = Pf.surveyId) as avgLength,
        from surveys S


     */
}
