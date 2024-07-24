package com.midnight.adaem.surveys.repository;

import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Participation;
import org.springframework.data.repository.CrudRepository;

public interface ParticipationRepository extends CrudRepository<Participation, Long> {

        /*


        c. Fetch the list of points (with the related survey ID) that a member has collected so far (input is the member ID). (participation, surveys)



        d. Fetch the list of members who can be invited to a given survey (not participated in that survey yet and are active). (members)

        // Need a join here
        e. Fetch the list of surveys with statistics, including:
        - Survey ID (surveys)
        - Survey name (surveys)
        - Number of completed questionnaires (participation)
        - Number of filtered participants (participation)
        - Number of rejected participants (participation)
        - Average length of time spent on the survey (Participation.length)

     */


}
