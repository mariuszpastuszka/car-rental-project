package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.dto.BookingRecordDto;
import com.sda.carrentalproject.service.BookingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class BookingRecordController {

    private final BookingRecordService bookingRecordService;

    public BookingRecordController(BookingRecordService bookingRecordService) {
        this.bookingRecordService = bookingRecordService;
    }

    @GetMapping("/booking-records")
    public List<BookingRecordDto> allBookingRecords() {
        return Collections.emptyList();
    }
}
