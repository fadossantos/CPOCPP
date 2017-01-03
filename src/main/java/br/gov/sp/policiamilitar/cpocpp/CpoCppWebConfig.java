package br.gov.sp.policiamilitar.cpocpp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.gov.sp.policiamilitar.cpocpp.web.conversion.DateFormatter;
import br.gov.sp.policiamilitar.cpocpp.web.conversion.VarietyFormatter;

@Configuration
public class CpoCppWebConfig extends WebMvcConfigurerAdapter{

	public CpoCppWebConfig() {
		super();
	}

	/*
	 * -------------------------------------- FORMATTERS
	 * --------------------------------------
	 */

	@Bean
	public VarietyFormatter varietyFormatter() {
		return new VarietyFormatter();
	}

	@Bean
	public DateFormatter dateFormatter() {
		return new DateFormatter();
	}

	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	  }

}
