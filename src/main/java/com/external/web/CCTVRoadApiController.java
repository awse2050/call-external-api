package com.external.web;


import com.external.api.domains.dto.CCTVStnRoadRequest;
import com.external.api.domains.service.CCTVStnRoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/road")
public class CCTVRoadApiController {

    private final CCTVStnRoadService cctvStnRoadService;

    @GetMapping("/wthr")
    public void wthr(@ModelAttribute CCTVStnRoadRequest cctvStnRoadRequest) {
        cctvStnRoadService.getCctvStnRoadWthr(cctvStnRoadRequest);
    }
}
