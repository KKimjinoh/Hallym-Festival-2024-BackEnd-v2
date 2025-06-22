package com.kkimjinoh.movieadmin.domain.reservation.controller;

import com.kkimjinoh.global.excel.ExcelExportUtil;
import com.kkimjinoh.movieadmin.domain.reservation.docs.CancelReservationDoc;
import com.kkimjinoh.movieadmin.domain.reservation.docs.ExportReservationExcelDoc;
import com.kkimjinoh.movieadmin.domain.reservation.docs.GetAllReservationsDoc;
import com.kkimjinoh.movieadmin.domain.reservation.docs.ReserveDoc;
import com.kkimjinoh.movieadmin.domain.reservation.dto.request.RequestReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
import com.kkimjinoh.movieadmin.domain.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 예약 관련 API 를 처리하는 Controller
 */
@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
@Tag(name = "예약", description = "예약 관련 API")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    @GetAllReservationsDoc
    public ResponseEntity<List<ResponseGetReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    @ReserveDoc
    public ResponseEntity<ResponseGetReservationDto> reserve(@Valid @RequestBody RequestReservationDto body) {
        return ResponseEntity.ok(reservationService.reserve(body));
    }

    @DeleteMapping("/{reservationId}")
    @CancelReservationDoc
    public ResponseEntity<Void> cancel(@PathVariable Long reservationId) {
        reservationService.cancel(reservationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/export")
    @ExportReservationExcelDoc
    public void exportReservationsToExcel(HttpServletResponse response) throws IOException {
        List<ResponseGetReservationDto> reservations = reservationService.getAllReservations();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=reservations.xlsx");
        ExcelExportUtil.writeReservationsToExcel(reservations, response.getOutputStream());
    }
}