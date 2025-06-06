package com.kkimjinoh.movieadmin.domain.userGroup.entity;

import com.kkimjinoh.global.entity.DateEntity;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestUpdateUserGroupDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserGroupEntity extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 그룹 ID", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "사용자 그룹명", example = "한림대학교 총학생회")
    private String name;

}
