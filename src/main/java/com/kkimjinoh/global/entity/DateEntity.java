package com.kkimjinoh.global.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

/**
 * 공통 일자(생성·수정·삭제) 컬럼을 갖는 베이스 엔티티
 */
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public abstract class DateEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false,
            columnDefinition = "datetime default current_timestamp comment '생성일시'")
    @Schema(description = "생성일시", example = "2025-06-01T20:00:00")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",
            columnDefinition = "datetime default current_timestamp on update current_timestamp comment '변경일시'")
    @Schema(description = "변경일시", example = "2025-06-01T20:00:00")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at",
            columnDefinition = "datetime comment '삭제일시'")
    @Schema(description = "삭제일시", example = "2025-06-01T20:00:00", nullable = true)
    private LocalDateTime deletedAt;

    // 소프트 삭제 처리
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    // 복원(undo delete)
    public void restore() {
        this.deletedAt = null;
    }
}
