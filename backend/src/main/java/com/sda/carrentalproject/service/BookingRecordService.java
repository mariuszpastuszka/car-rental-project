package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.BookingRecord;
import com.sda.carrentalproject.repository.BookingRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingRecordService {

    private final BookingRecordRepository bookingRecordRepository;


    public BookingRecordService(BookingRecordRepository bookingRecordRepository) {
        this.bookingRecordRepository = bookingRecordRepository;
    }

    public List<BookingRecord> findAllBookingRecords() {
        log.info("finding all booking records");
        var records = bookingRecordRepository.findAll();

        log.info("Number of found booking records: [{}]", records.size());
        log.debug("All booking records: {}", records);

        return records;
    }
}
