package se.iths.vuegroup4backend.repository;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.vuegroup4backend.entity.BookingEntity;

public interface BookingRepository extends ListCrudRepository<BookingEntity, Long> {

}
