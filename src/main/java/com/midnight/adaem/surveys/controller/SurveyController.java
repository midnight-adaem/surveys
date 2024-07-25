package com.midnight.adaem.surveys.controller;


import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Members;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Statuses;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.PointsDTO;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Surveys;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.custom.SurveyStatsDTO;
import com.midnight.adaem.surveys.repository.MemberRepository;
import com.midnight.adaem.surveys.repository.ParticipationRepository;
import com.midnight.adaem.surveys.repository.StatusesRepository;
import com.midnight.adaem.surveys.repository.SurveysRespository;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        return memberRepository.findAll();
    }

    @GetMapping("/members/{memberId}")
    public Members getReviewerByDisplayName(@PathVariable Long memberId) {
        Optional<Members> membersOptional = this.memberRepository.findById(memberId);
        if (membersOptional.isEmpty()) {
            // throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No member found for ID  " + memberId);
        }

        return membersOptional.get();
    }

    //a. Fetch all respondents who completed the questionnaire for a given survey ID.
    @GetMapping("/members/surveys/{surveyId}")
    public Iterable<Members> getMembersByCompletedSurvey(@PathVariable @NonNull Long surveyId) {
        return memberRepository.findAllBySurvey(surveyId);
    }


    //b. Fetch all surveys that were completed by a given member ID.
    @GetMapping("/members/{memberId}/compSurveys")
    public Iterable<Surveys> getComplSurveysForMember(@PathVariable @NonNull Long memberId) {
        return surveysRespository.findAllCompletedSurveysByMemberId(memberId);
    }


    //c. Fetch the list of points (with the related survey ID) that a member has collected so far (input is the member ID).
    @GetMapping("/members/{memberId}/points")
    public Iterable<PointsDTO> getPointsForMember(@PathVariable @NonNull Long memberId) {
        return surveysRespository.findAllPointsByMember(memberId);
    }


    //d. Fetch the list of members who can be invited to a given survey (not participated in that survey yet and are active).
    @GetMapping("/surveys/{surveyId}/invitableMembers")
    public Iterable<Members> getInvitableMembersBySurvey(@PathVariable @NonNull Long surveyId) {
        return memberRepository.findAllInvitableForSurvey(surveyId);
    }

    @GetMapping("/surveys/{surveyId}")
    public Surveys getSurveyById(@PathVariable @NonNull Long surveyId) {

        Optional<Surveys> surveysOptional = this.surveysRespository.findById(surveyId);
        if (surveysOptional.isEmpty()) {
            // throw exception
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No survey found for ID  " + surveyId);
        }

        return surveysOptional.get();

    }

    @GetMapping("/surveys")
    public Iterable<Surveys> getAllSurveys() {
        return surveysRespository.findAll();
    }


    /*
    e. Fetch the list of surveys with statistics, including:
    - Survey ID
    - Survey name
    - Number of completed questionnaires
    - Number of filtered participants
    - Number of rejected participants
    - Average length of time spent on the survey (Participation.length)
*/
    @GetMapping("/surveys/stats")
    public Iterable<SurveyStatsDTO> getAllSurveysWithStats() {
        return surveysRespository.findAllSurveysWithStats();
    }


    @GetMapping("/statuses")
    public Iterable<Statuses> getAllStatuses() {
        return statusesRepository.findAll();
    }


}
