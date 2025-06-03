package com.kkimjinoh.movieadmin.domain.notice.entity;

import com.kkimjinoh.global.entity.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 공지사항 Entity
 */
@Entity
@Table(name = "notice")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class NoticeEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "공지사항 ID", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "공지사항 제목", example = "주점예약 공지")
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    @Schema(description = "공지사항 내용", example = "공지에대한 내용 서술")
    private String content;
}