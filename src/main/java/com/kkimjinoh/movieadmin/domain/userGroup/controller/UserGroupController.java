package com.kkimjinoh.movieadmin.domain.userGroup.controller;

import com.kkimjinoh.movieadmin.domain.userGroup.docs.GetUserGroupListDoc;
import com.kkimjinoh.movieadmin.domain.userGroup.dto.response.ResponseGetUserGroupDto;
import com.kkimjinoh.movieadmin.domain.userGroup.service.UserGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/manager/user-group")
@Tag(name = "사용자 그룹", description = "사용자 그룹 관리 기능")
public class UserGroupController {

    private final UserGroupService userGroupService;

    @GetMapping
    @GetUserGroupListDoc
    public ResponseEntity<List<ResponseGetUserGroupDto>> getUserGroupList() {
        return ResponseEntity.ok(userGroupService.getUserGroupList());
    }
}
