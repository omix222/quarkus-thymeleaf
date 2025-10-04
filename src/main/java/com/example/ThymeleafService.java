package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@ApplicationScoped
public class ThymeleafService {

    private TemplateEngine templateEngine;

    @PostConstruct
    public void init() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false); // For development

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
    }

    public String process(String template, Context context) {
        return templateEngine.process(template, context);
    }
}
