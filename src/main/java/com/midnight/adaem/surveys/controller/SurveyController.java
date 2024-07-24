package com.midnight.adaem.surveys.controller;


import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Members;
import com.midnight.adaem.surveys.repository.MemberRepository;
import com.midnight.adaem.surveys.repository.ParticipationRepository;
import com.midnight.adaem.surveys.repository.StatusesRepository;
import com.midnight.adaem.surveys.repository.SurveysRespository;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;
    private final StatusesRepository statusesRepository;
    private final SurveysRespository surveysRespository;

    public SurveyController (
            final MemberRepository memberRepository,
            final ParticipationRepository participationRepository,
            final StatusesRepository statusesRepository,
            final SurveysRespository surveysRespository
            ) {
        this.memberRepository = memberRepository;
        this.participationRepository = participationRepository;
        this.statusesRepository = statusesRepository;
        this.surveysRespository = surveysRespository;
    }


    @GetMapping("/members")
    public Iterable<Members> getAllMembers() {
        Iterable<Members> members = memberRepository.findAll();
        return members;
    }


    //a. Fetch all respondents who completed the questionnaire for a given survey ID.
    @GetMapping("/members/surveys/{surveyId}")
    public Iterable<Members> getMembersByCompletedSurvey(@PathVariable @NonNull Long surveyId) {
        return memberRepository.findAllBySurvey(surveyId);
    }


        //b. Fetch all surveys that were completed by a given member ID.
        //c. Fetch the list of points (with the related survey ID) that a member has collected so far (input is the member ID).
        //d. Fetch the list of members who can be invited to a given survey (not participated in that survey yet and are active).
        /*
        e. Fetch the list of surveys with statistics, including:
        - Survey ID
        - Survey name
        - Number of completed questionnaires
        - Number of filtered participants
        - Number of rejected participants
        - Average length of time spent on the survey (Participation.length)
        */


}
