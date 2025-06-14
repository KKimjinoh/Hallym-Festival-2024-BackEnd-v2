package com.kkimjinoh.movieadmin.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 내 정보 수정 요청 RequestDto
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "내 정보 수정 요청 DTO")
public class RequestUpdateMyInfoDto {

    @Schema(description = "이름", example = "홍길동")
    @NotBlank
    private String name;

    @Schema(description = "전화번호", example = "010-1234-5678")
    @NotBlank
    private String phoneNumber;

    @Schema(description = "학번", example = "202412345")
    @NotBlank
    private String studentNumber;

    @Schema(description = "소속 그룹 ID", example = "1")
    private Long userGroupId;

    @Schema(description = "개인정보 수집 동의", example = "true")
    private boolean privacyAgree;

    @Schema(description = "알림 수신 동의", example = "true")
    private boolean pushAgree;


}
