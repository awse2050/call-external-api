package com.external.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    /*
        RestTemplate
        Spring 3 부터 지원하는 Blocking I/O 방식
     */
    @Value("${cctv.road.weather.api.service.url}")
    private String serviceUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        System.out.println("serviceUrl : " + serviceUrl);

        return restTemplateBuilder
                .requestFactory(
                        // RestTemplate은 ClientHttpRequestFactory에 처리를 위임하는데, 여기서 기본적으로 구현된 것을 사용하게 해둔다.
                        () -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000)) // connection-timeout
                .setReadTimeout(Duration.ofMillis(5000)) // read-timeout
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                .rootUri(serviceUrl)
                .interceptors(clientHttpRequestInterceptor())
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        };
    }
}
