package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.app.owner.name}")
    String ownerName;

    @Value("${info.app.owner.surname}")
    String ownerSurname;

    @Value("${info.company.name}")
    String companyName;

    @Value("${info.company.email}")
    String companyMail;

    @Value("${info.company.phone}")
    String companyPhone;

    @Value("${info.company.goal}")
    String companyGoal;
}
