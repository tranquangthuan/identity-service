package com.thuan.identiy_service.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageSourceConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

//    @Bean
//    public MessageSource messageSource() {
//        return new MessageSource() {
//            @Override
//            public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
//                Properties properties = loadYamlForLocale(locale);
//                return properties.getProperty(code, defaultMessage);
//            }
//
//            @Override
//            public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
//                Properties properties = loadYamlForLocale(locale);
//                String message = properties.getProperty(code);
//                if (message == null) {
//                    throw new NoSuchMessageException(code, locale);
//                }
//                return message;
//            }
//
//            @Override
//            public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
//                Properties properties = loadYamlForLocale(locale);
//                for (String code : resolvable.getCodes()) {
//                    String message = properties.getProperty(code);
//                    if (message != null) {
//                        return message;
//                    }
//                }
//                throw new NoSuchMessageException(resolvable.getCodes()[0], locale);
//            }
//
//            private Properties loadYamlForLocale(Locale locale) {
//                String language = locale.getLanguage();
//                if("en".equals(language)) {
//                    language = "";
//                }
//                System.out.println("Language = " + language);
//                String fileName = "messages" + (language.isEmpty() ? "" : "_" + language) + ".yml";
//
//                YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
//                bean.setResources(new ClassPathResource(fileName));
//                return bean.getObject();
//            }
//        };
//    }
}
