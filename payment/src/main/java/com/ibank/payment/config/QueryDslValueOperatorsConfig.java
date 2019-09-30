package com.ibank.payment.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.experimental.QuerydslHttpRequestContextAwareServletFilter;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.experimental.QuerydslPredicateArgumentResolverBeanPostProcessor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import com.ibank.payment.model.Payment;


@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class QueryDslValueOperatorsConfig {
	
	@Bean
	public FilterRegistrationBean querydslHttpRequestContextAwareServletFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new QuerydslHttpRequestContextAwareServletFilter(
				querydslHttpRequestContextAwareServletFilterMappings()));
		bean.setAsyncSupported(false);
		bean.setEnabled(true);
		bean.setName("querydslHttpRequestContextAwareServletFilter");
		bean.setUrlPatterns(Arrays.asList(new String[] { "/payments/*" }));
		bean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return bean;
	}

	private Map<String, Class<?>> querydslHttpRequestContextAwareServletFilterMappings() {
		Map<String, Class<?>> mappings = new HashMap<>();
		mappings.put("/payments", Payment.class);
		return mappings;
	}


	@Bean
	public QuerydslPredicateArgumentResolverBeanPostProcessor querydslPredicateArgumentResolverBeanPostProcessor(
			QuerydslBindingsFactory factory, DefaultFormattingConversionService conversionServiceDelegate) {
		return new QuerydslPredicateArgumentResolverBeanPostProcessor(factory, conversionServiceDelegate);
	}
	
	@Bean(name="defaultConversionService")
	public DefaultFormattingConversionService getConversionService() {

	    return new DefaultFormattingConversionService();
	}

}
