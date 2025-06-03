package com.kkimjinoh.movie.domain.community.dto.request;

import com.kkimjinoh.movie.domain.community.entity.CommunityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 커뮤니티 글 작성 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "커뮤니티 글 작성 요청")
public class RequestCreateMessageDto {

    @Schema(description = "글 내용", example = "안녕하세요!")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String content;

    @Schema(description = "비밀번호(삭제·수정용)", example = "1234")
    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    private String password;

    @Schema(description = "닉네임", example = "홍길동", maxLength = 50)
    @NotBlank(message = "닉네임은 비어 있을 수 없습니다.")
    private String nickname;

    /**
     * RequestCreateMessageDto -> CommunityEntity
     */
    public CommunityEntity toEntity() {
        return CommunityEntity.builder()
                .content(content)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
