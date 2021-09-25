package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private DbService service;

    public String buildEmailUsingTemplate(String message, String emailTemplate) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage yor tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Sincerely" + "\n" + adminConfig.getOwnerName() + " " + adminConfig.getOwnerSurname());
        context.setVariable("end", adminConfig);
        context.setVariable("preview_trello", "New activity on Your trello board | ");
        context.setVariable("preview", "Daily status of yor application | ");
        context.setVariable("companyDetails", adminConfig.getCompanyName() + "\n" +
                adminConfig.getCompanyMail() + "\n" + adminConfig.getCompanyPhone() + "\n" + adminConfig.getCompanyGoal());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("tasks_names", service.getAllTasks());
        return templateEngine.process(emailTemplate, context);
    }
}
