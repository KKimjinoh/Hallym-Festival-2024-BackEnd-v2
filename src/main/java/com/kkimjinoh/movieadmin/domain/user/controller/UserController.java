package com.kkimjinoh.movieadmin.domain.user.controller;

import com.kkimjinoh.global.dto.StatusOkResponseDto;
import com.kkimjinoh.movieadmin.domain.user.docs.DeleteMyAccountDoc;
import com.kkimjinoh.movieadmin.domain.user.docs.GetMyInfoDoc;
import com.kkimjinoh.movieadmin.domain.user.docs.GetUserListDoc;
import com.kkimjinoh.movieadmin.domain.user.docs.UpdateMyInfoDoc;
import com.kkimjinoh.movieadmin.domain.user.dto.request.RequestUpdateMyInfoDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDetailDto;
import com.kkimjinoh.movieadmin.domain.user.dto.response.ResponseGetUserDto;
import com.kkimjinoh.movieadmin.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사용자 관련 API 컨트롤러
 * 모든 사용자 조회 및 내 정보 조회, 수정, 삭제 기능을 담당한다.
 */
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Tag(name = "사용자", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @GetUserListDoc
    public ResponseEntity<List<ResponseGetUserDto>> getUserList() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping()
    @GetMyInfoDoc
    public ResponseEntity<ResponseGetUserDetailDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @PutMapping()
    @UpdateMyInfoDoc
    public ResponseEntity<ResponseGetUserDetailDto> updateMyInfo(@Valid @RequestBody RequestUpdateMyInfoDto body) {
        return ResponseEntity.ok(userService.updateMyInfo(body));
    }

    @DeleteMapping()
    @DeleteMyAccountDoc
    public ResponseEntity<StatusOkResponseDto> deleteMyAccount() {
        return ResponseEntity.ok(userService.deleteMyAccount());
    }

}
