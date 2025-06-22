package com.kkimjinoh.movieadmin.domain.lostitem.dto.request;

import com.kkimjinoh.movieadmin.domain.lostitem.entity.LostItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 분실물 수정 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "분실물 수정 요청")
public class RequestUpdateLostItemDto {

    @Schema(description = "분실물 명", example = "파랑색 지갑")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String name;

    @Schema(description = "발견장소", example = "학교 운동장")
    @NotBlank(message = "발견장소는 비어 있을 수 없습니다.")
    private String location;

    @Schema(description = "분실물 사진", required = false)
    private MultipartFile lostItemImage;

    @Schema(description = "회수 여부", example = "true")
    private boolean isReturn;


}
