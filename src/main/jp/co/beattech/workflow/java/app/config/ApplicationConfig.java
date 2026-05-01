package jp.co.beattech.workflow.java.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.beattech.workflow.java.app.filter.ApplyFilter;
import jp.co.beattech.workflow.java.app.filter.LoginFilter;
import jp.co.beattech.workflow.java.app.filter.UserFilter;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

	@Override
	public Validator getValidator() {
		var validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	
	@Bean
	MessageSource messageSource() {
		var messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("validation");
		return messageSource;
	}
	
	@Bean
	FilterRegistrationBean<LoginFilter> authFilter(){
		var bean = new FilterRegistrationBean<LoginFilter>(new LoginFilter());
		bean.addUrlPatterns("/apply/*");
		bean.addUrlPatterns("/user/*");
		return bean;
	}
	
	@Bean
	FilterRegistrationBean<UserFilter> adminFilter(){
		var bean = new FilterRegistrationBean<UserFilter>(new UserFilter());
		bean.addUrlPatterns("/user/*");
		return bean;
	}
	
	@Bean
	FilterRegistrationBean<ApplyFilter> applyFilter(){
		var bean = new FilterRegistrationBean<ApplyFilter>(new ApplyFilter());
		bean.addUrlPatterns("/apply/*");
		return bean;
	}

}
