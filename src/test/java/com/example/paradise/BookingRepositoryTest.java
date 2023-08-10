package com.example.paradise;

import com.example.paradise.entity.Booking;
import com.example.paradise.repo.BookingRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingRepositoryTest {

    @Autowired
    private BookingRepo bookingRepo;

    @Test
    @Order(1)
    public void shouldSaveBooking() {
        Booking booking = createBooking();

        bookingRepo.save(booking);

        Assertions.assertThat(booking.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void shouldGetBooking() {
        Booking booking = createBooking();
        bookingRepo.save(booking);

        Booking bookingCreated = bookingRepo.findById(booking.getId()).get();

        Assertions.assertThat(bookingCreated.getId()).isEqualTo(booking.getId());
    }

    @Test
    @Order(3)
    public void shouldGetListOfBookings() {
        Booking booking = createBooking();
        bookingRepo.save(booking);

        List<Booking> bookings = bookingRepo.findAll();

        Assertions.assertThat(bookings).isNotEmpty();
    }

    @Test
    @Order(4)
    public void shouldUpdateBooking() {
        Booking booking = createBooking();
        bookingRepo.save(booking);

        Booking booking1 = bookingRepo.findById(booking.getId()).get();
        booking1.setFullname("new name");
        Booking bookingUpdated = bookingRepo.save(booking1);

        Assertions.assertThat(bookingUpdated.getFullname()).isEqualTo("new name");
    }

    @Test
    @Order(5)
    public void shouldDeleteBooking() {
        Booking booking = createBooking();
        bookingRepo.save(booking);

        Booking booking1 = bookingRepo.findById(booking.getId()).get();
        bookingRepo.delete(booking1);

        Optional<Booking> optionalBooking = bookingRepo.findBookingByFullname("jenish");

        Assertions.assertThat(optionalBooking).isEmpty();
    }

    private Booking createBooking() {
        return Booking.builder()
                .fullname("jenish")
                .rooms("single")
                .checkin("23/2/2023")
                .checkout("30/2/2023")
                .mobileNo("9810000000")
                .number_of_people("2")
                .build();
    }
}
