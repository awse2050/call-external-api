package com.external.api.domains.service;

import com.external.api.domains.dto.CCTVStnRoadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CCTVStnRoadService {

    /*
        getCctvStnRoadWthr - CCTV지점별도로날씨조회
        getStdNodeLinkRoadWw - 표준노드링크별도로날씨조회
     */
    @Value("${cctv.road.weather.api.decoding.key}")
    private String serviceKey;

    private final RestTemplate restTemplate;

    public void getCctvStnRoadWthr(CCTVStnRoadRequest cctvStnRoadRequest) {
        System.out.println("cctvStnRoadRequest = " + cctvStnRoadRequest);
        System.out.println("this.serviceKey : " + this.serviceKey);

        String url =  "/getCctvStnRoadWthr" + "?serviceKey=" + this.serviceKey + "&" + cctvStnRoadRequest.getConvertRequestURI();
        System.out.println("url = " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // TODO : Not Registered Service Key Error
        // RestTemplate에서 URL을 인코딩을 해버린다.
        ResponseEntity<String> exchange = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        System.out.println("exchange = " + exchange);
        System.out.println("exchange getStatusCode= " + exchange.getStatusCode());
        System.out.println("exchange getBody= " + exchange.getBody());
    }

}
