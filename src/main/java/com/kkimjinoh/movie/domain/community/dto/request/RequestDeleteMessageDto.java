package com.kkimjinoh.movie.domain.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 커뮤니티 글 삭제 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "글 삭제 요청 (비밀번호만 전달)")
public class RequestDeleteMessageDto {

    @Schema(description = "삭제용 비밀번호", example = "비밀번호1234")
    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    private String password;
}