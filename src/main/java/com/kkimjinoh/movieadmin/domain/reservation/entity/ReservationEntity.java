package com.kkimjinoh.movieadmin.domain.reservation.entity;

import com.kkimjinoh.global.entity.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 예약 Entity
 */
@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
public class ReservationEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "예약 ID", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Column(nullable = false)
    @Schema(description = "학번", example = "202412345")
    private String studentNumber;

    @Column(nullable = false)
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Column(nullable = false)
    @Schema(description = "인원수", example = "4")
    private int peopleCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @Schema(description = "예약 상태", example = "SUCCESS")
    private ReservationStatus status;

    @Schema(description = "대기 번호", example = "13", nullable = true)
    private Integer waitingNumber;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Schema(description = "개인정보 수집 동의 여부", example = "true")
    private boolean privacyAgree;
}