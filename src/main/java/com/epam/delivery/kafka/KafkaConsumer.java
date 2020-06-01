package com.epam.delivery.kafka;

import com.epam.delivery.dto.OrderDto;
import com.epam.delivery.dto.ResultDto;
import com.epam.delivery.dto.ResultDto.Check;
import com.epam.delivery.dto.ResultDto.Service;
import com.epam.delivery.service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

  private final ObjectMapper objectMapper;
  private final DestinationService destinationService;
  private final ResultKafkaProducer kafkaProducer;

  @SneakyThrows
  @KafkaListener(
      topics = "${spring.kafka.consumer.topic}",
      clientIdPrefix = "delivery-request-client",
      groupId = "delivery-request-group",
      containerFactory = "consumerFactory"
  )
  public ResultDto consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
    final OrderDto orderDto = objectMapper.readValue(record.value(), OrderDto.class);
    final boolean enough = destinationService.closeEnough(orderDto);
    ResultDto resultDto = enough
        ? new ResultDto(orderDto, Check.SUCCESS, Service.DELIVERY)
        : new ResultDto(orderDto, Check.FAIL, Service.DELIVERY);
    kafkaProducer.send(resultDto);
    acknowledgment.acknowledge();
    return resultDto;
  }
}
