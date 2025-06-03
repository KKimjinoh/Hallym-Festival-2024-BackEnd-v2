package com.kkimjinoh.movieadmin.domain.community.entity;

import com.kkimjinoh.global.entity.DateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 커뮤니티 게시글 Entity
 */
@Entity
@Table(name = "community")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class CommunityEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "고유 ID", example = "1")
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    @Schema(description = "커뮤니티 글 내용", example = "안녕하세요!")
    private String content;

    @Column(nullable = false)
    @Schema(description = "비밀번호(댓글 수정·삭제용)", example = "1234")
    private String password;

    @Column(nullable = false, length = 50)
    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;
}