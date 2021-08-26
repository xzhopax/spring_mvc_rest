package com.dampcave.mvc.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Вместо Web.xml
public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // null, потому что никаких RootConfig в нашем проекте нет.
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class}; // вместо  <param-value>/WEB-INF/applicationContext.xml</param-value>
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // вместо  <url-pattern>/</url-pattern>
    }
}
