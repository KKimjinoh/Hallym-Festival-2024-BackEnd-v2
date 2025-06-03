package com.kkimjinoh.movie.domain.lostitem.dto.request;

import com.kkimjinoh.movie.domain.lostitem.entity.LostItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 분실물 추가 RequestDto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "분실물 추가 요청")
public class RequestCreateLostItemDto {

    @Schema(description = "분실물 명", example = "파랑색 지갑")
    @NotBlank(message = "내용은 비어 있을 수 없습니다.")
    private String name;

    @Schema(description = "발견장소", example = "학교 운동장")
    @NotBlank(message = "발견장소는 비어 있을 수 없습니다.")
    private String location;

    @Schema(description = "분실물 사진", required = true)
    private MultipartFile lostItemImage;

    /**
     * RequestCreateLostItemDto -> LostItemEntity
     */
    public LostItemEntity toEntity(String imageUrl) {
        return LostItemEntity.builder()
                .name(this.name)
                .location(this.location)
                .imageUrl(imageUrl)
                .isReturn(false)
                .build();
    }
}
