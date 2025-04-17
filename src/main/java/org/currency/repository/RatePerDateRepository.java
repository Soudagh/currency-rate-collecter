package org.currency.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.currency.data.entity.RatePerDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatePerDateRepository extends CrudRepository<RatePerDate, Long> {

  Optional<RatePerDate> findRatePerDateByDate(LocalDate date);
}
