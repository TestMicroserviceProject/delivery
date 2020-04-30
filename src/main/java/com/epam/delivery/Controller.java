package com.epam.delivery;

import com.epam.delivery.dto.OrderDto;
import com.epam.delivery.service.DestinationService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final DestinationService service;

  @GetMapping("/test")
  public boolean test() {
    final OrderDto orderDto = new OrderDto(1L, 1L, new ArrayList<>(), "blizko", 5.0);
    return service.closeEnough(orderDto);
  }

}
