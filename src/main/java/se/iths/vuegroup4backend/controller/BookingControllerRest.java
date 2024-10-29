package se.iths.vuegroup4backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.vuegroup4backend.entity.BookingEntity;
import se.iths.vuegroup4backend.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookingControllerRest {
    @Autowired
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

    // read all movies by id

    @GetMapping("/swapi/{swapiId}")
    public List<BookingEntity> getBookingsBySwapiId(@PathVariable Long swapiId) {
        return bookingRepository.findAllBySwapiId(swapiId);
    }

    //Delete product
    @DeleteMapping("/swapi/{swapiId}")
    public void deleteBooking(@PathVariable("swapiId") Long swapiId) {
        bookingRepository.deleteByswapiId(swapiId);
    }


    @PatchMapping("/booking/{id}")
    public ResponseEntity<String> updateBooking(@RequestBody BookingEntity updatedBooking, @PathVariable Long id) {
        Optional<BookingEntity> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            BookingEntity existingBooking = optionalBooking.get();

            int availableSeats = existingBooking.getTotalSeats() - existingBooking.getBookedSeats();

            if (updatedBooking.getBookedSeats() <= availableSeats) {
                existingBooking.setBookedSeats(existingBooking.getBookedSeats() + updatedBooking.getBookedSeats());

                bookingRepository.save(existingBooking);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Inte tillräckligt med platser tillgängliga.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
