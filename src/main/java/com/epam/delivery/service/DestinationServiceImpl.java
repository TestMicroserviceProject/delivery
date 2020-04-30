package com.epam.delivery.service;

import com.epam.delivery.dto.OrderDto;
import com.epam.delivery.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

  private final DestinationRepository repository;

  @Override
  public boolean closeEnough(OrderDto order) {
    var location = order.getLocation();
    var destination = repository.findByLocation(location);
    var distance = destination.getDistance();
    return distance <= 2;
  }
}
