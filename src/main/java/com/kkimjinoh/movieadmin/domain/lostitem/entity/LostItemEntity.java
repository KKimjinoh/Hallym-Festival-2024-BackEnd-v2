package com.kkimjinoh.movieadmin.domain.lostitem.entity;

import com.kkimjinoh.global.entity.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 분실물 Entity
 */
@Entity
@Table(name = "lostItem")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class LostItemEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "분실물 ID", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "분실물 명", example = "파랑색 지갑")
    private String name;

    @Column(nullable = true)
    @Schema(description = "발견장소", example = "학교 운동장")
    private String location;

    @Column(nullable = false)
    @Schema(description = "분실물 사진 Url", example = "s3://이미지 주소")
    private String imageUrl;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Schema(description = "회수여부", example = "false")
    private boolean isReturn;
}