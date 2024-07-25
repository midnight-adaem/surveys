package com.midnight.adaem.surveys.config;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Members;
import com.midnight.adaem.surveys.repository.MemberRepository;
import com.midnight.adaem.surveys.repository.ParticipationRepository;
import com.midnight.adaem.surveys.repository.StatusesRepository;
import com.midnight.adaem.surveys.repository.SurveysRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Component

public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    // TODO: Move this to properties
    private String membersFile = "static/CSV/Members.csv";

    private final MemberRepository memberRepository;
    private final ParticipationRepository participationRepository;
    private final StatusesRepository statusesRepository;
    private final SurveysRespository surveysRespository;

    public DataLoader(MemberRepository memberRepository, ParticipationRepository participationRepository, StatusesRepository statusesRepository, SurveysRespository surveysRespository) {
        this.memberRepository = memberRepository;
        this.participationRepository = participationRepository;
        this.statusesRepository = statusesRepository;
        this.surveysRespository = surveysRespository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup || memberRepository.findAll().iterator().hasNext()) {
            return;
        }

        getMembersFromCSV();
        alreadySetup = true;
    }

    private List<Members> getMemberDataFromCSV() {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(membersFile).getFile();
            MappingIterator<Members> readValues =
                    mapper.reader(Members.class).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            logger.error("Error occurred while loading object list from file " + membersFile, e);
            return Collections.emptyList();
        }

    }

    private void getMembersFromCSV() {
        List<Members> members = getMemberDataFromCSV();

        for (Members member : members) {
            memberRepository.save(member);
        }


    }


}

