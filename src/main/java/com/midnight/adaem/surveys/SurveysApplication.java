package com.midnight.adaem.surveys;

import com.midnight.adaem.surveys.com.midnight.adaem.surveys.model.Members;
import com.midnight.adaem.surveys.repository.MemberRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SurveysApplication {

	@Autowired
	private MemberRepository memberRepository;

	public static void main(String[] args) {
		SpringApplication.run(SurveysApplication.class, args);
	}


}
