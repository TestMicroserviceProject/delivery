package com.epam.delivery.service;

import com.epam.delivery.dto.OrderDto;

public interface DestinationService {

  boolean closeEnough(OrderDto order);

}
