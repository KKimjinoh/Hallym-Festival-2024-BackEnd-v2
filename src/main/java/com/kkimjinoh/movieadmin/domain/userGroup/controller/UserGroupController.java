package com.kkimjinoh.movieadmin.domain.userGroup.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.userGroup.docs.CreateUserGroupDoc;
import com.kkimjinoh.movieadmin.domain.userGroup.docs.DeleteUserGroupDoc;
import com.kkimjinoh.movieadmin.domain.userGroup.docs.GetUserGroupListDoc;
import com.kkimjinoh.movieadmin.domain.userGroup.docs.UpdateUserGroupDoc;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestCreateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.request.RequestUpdateUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.service.UserGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 그룹 Controller
 * 사용자 그룹 생성, 조회, 수정, 삭제 기능을 담당한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/user-group")
@Tag(name = "사용자 그룹", description = "사용자 그룹 관리 기능")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @GetMapping
    @GetUserGroupListDoc
    public ResponseEntity<List<ResponseGetUserGroupDto>> getUserGroupList() {
        return ResponseEntity.ok(userGroupService.getUserGroupList());
    }

    @PostMapping
    @CreateUserGroupDoc
    public ResponseEntity<ResponseGetUserGroupDto> createMessage(
            @Valid @RequestBody RequestCreateUserGroupDto body) {
        return ResponseEntity.ok(userGroupService.createUserGroup(body));
    }

    @PutMapping("/{id}")
    @UpdateUserGroupDoc
    public ResponseEntity<ResponseGetUserGroupDto> updateUserGroup(
            @PathVariable("id") Long id,
            @Valid @RequestBody RequestUpdateUserGroupDto body) {
        return ResponseEntity.ok(userGroupService.updateUserGroup(id, body));
    }

    @DeleteMapping("/{id}")
    @DeleteUserGroupDoc
    public ResponseEntity<StatusOkResponseDto> deleteUserGroup(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(userGroupService.deleteUserGroup(id));
    }
}
