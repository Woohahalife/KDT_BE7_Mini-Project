package com.core.miniproject.src.accommodation.controller;

import com.core.miniproject.src.accommodation.domain.dto.AccommodationInsertRequest;
import com.core.miniproject.src.accommodation.domain.dto.AccommodationInsertResponse;
import com.core.miniproject.src.accommodation.service.AccommodationService;
import com.core.miniproject.src.common.response.BaseResponse;
import com.core.miniproject.src.common.security.JwtAuthentication;
import com.core.miniproject.src.common.security.principal.MemberInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "숙소 생성 & 수정 & 삭제 api", description = "숙소 관련 api - 보안 접근 필요")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping("/v1/accommodation/admin")
    public BaseResponse<AccommodationInsertResponse> createAccommodation(
            @RequestBody AccommodationInsertRequest request,
            @JwtAuthentication MemberInfo memberInfo) {

        AccommodationInsertResponse accommodationInsertResponse =
                accommodationService.createAccommodation(request, memberInfo);

        return BaseResponse.response(accommodationInsertResponse);
    }
}
