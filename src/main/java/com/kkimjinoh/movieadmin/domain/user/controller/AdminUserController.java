package com.kkimjinoh.movieadmin.domain.user.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.user.docs.DeleteUserDoc;
import com.kkimjinoh.movieadmin.domain.user.docs.GetUserDetailListDoc;
import com.kkimjinoh.movieadmin.domain.user.docs.UpdateUserDoc;
import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateUserDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import com.kkimjinoh.movieadmin.domain.user.service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 관련 API 컨트롤러 (Admin 전용)
 * 사용자 상세 조회, 수정, 삭제 기능을 담당한다.
 */
@RestController
@RequestMapping("api/admin/user")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "사용자", description = "사용자 관련 API")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping("/all")
    @GetUserDetailListDoc
    public ResponseEntity<List<ResponseGetUserDetailDto>> getUserDetailList() {
        return ResponseEntity.ok(adminUserService.getUserDetailList());
    }

    @PutMapping("/{id}")
    @UpdateUserDoc
    public ResponseEntity<ResponseGetUserDetailDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdateUserDto body
    ) {
        return ResponseEntity.ok(adminUserService.updateUser(id, body));
    }

    @DeleteMapping("/{id}")
    @DeleteUserDoc
    public ResponseEntity<StatusOkResponseDto> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.deleteUser(id));
    }

}
