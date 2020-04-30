package com.epam.delivery.repository;

import com.epam.delivery.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

  Destination findByLocation(String location);
}
