package se.iths.vuegroup4backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.vuegroup4backend.entity.BookingEntity;
import se.iths.vuegroup4backend.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookingControllerRest {
    BookingRepository bookingRepository;

    public BookingControllerRest(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    //Create product
    @PostMapping("/booking")
    ResponseEntity<Void> createBooking(@RequestBody BookingEntity booking) {
        bookingRepository.save(booking);
        return ResponseEntity.ok().build();
    }

    //Read product
    @GetMapping("/booking")
    List<BookingEntity> bookingList() {
        return bookingRepository.findAll();
    }

    //Read product by ID
    @GetMapping("/booking/{id}")
    Optional booking(@PathVariable("id") Long id) {
        return bookingRepository.findById(id);
    }

    //Delete product
    @DeleteMapping("/booking/{id}")
    public void deleteBooking(@PathVariable("id") Long id) {
        bookingRepository.deleteById(id);
    }

    //Change product
    @PatchMapping("/booking/{id}")
    ResponseEntity<Void> updateBooking(@RequestBody BookingEntity updatedBooking, @PathVariable Long id) {
        Optional<BookingEntity> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            BookingEntity existingBooking = optionalBooking.get();

            // Update the existing user's fields with the data from the updatedUser object
            existingBooking.setBookedSeats(updatedBooking.getBookedSeats());
            existingBooking.setTotalSeats(updatedBooking.getTotalSeats());

            bookingRepository.save(existingBooking);

            return ResponseEntity.ok().build();
        } else {
            // If the user with the given ID does not exist, return a response with HTTP status code 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }
}
