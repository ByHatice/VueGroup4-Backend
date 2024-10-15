package se.iths.vuegroup4backend.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.vuegroup4backend.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends ListCrudRepository<BookingEntity, Long> {
    List<BookingEntity> findAllBySwapiId(Long swapiId);

    void deleteByswapiId(Long swapiId);
}
