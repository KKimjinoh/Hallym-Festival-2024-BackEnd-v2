package com.kkimjinoh.global.excel;

import com.kkimjinoh.movieadmin.domain.reservation.dto.response.ResponseGetReservationDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelExportUtil {

    public static void writeReservationsToExcel(List<ResponseGetReservationDto> reservations, OutputStream out) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservations");

        // Header
        Row header = sheet.createRow(0);
        String[] headers = {
                "ID", "이름", "학번", "전화번호", "인원수", "상태", "대기번호", "개인정보 동의", "생성일시"
        };
        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        // Body
        for (int i = 0; i < reservations.size(); i++) {
            Row row = sheet.createRow(i + 1);
            ResponseGetReservationDto dto = reservations.get(i);

            row.createCell(0).setCellValue(dto.getId());
            row.createCell(1).setCellValue(dto.getName());
            row.createCell(2).setCellValue(dto.getStudentNumber());
            row.createCell(3).setCellValue(dto.getPhoneNumber());
            row.createCell(4).setCellValue(dto.getPeopleCount());
            row.createCell(5).setCellValue(dto.getStatus().name());
            row.createCell(6).setCellValue(dto.getWaitingNumber() == null ? "" : String.valueOf(dto.getWaitingNumber()));
            row.createCell(7).setCellValue(dto.isPrivacyAgree() ? "Y" : "N");
            row.createCell(8).setCellValue(dto.getCreatedAt().toString());
        }

        workbook.write(out);
        workbook.close();
    }
}
