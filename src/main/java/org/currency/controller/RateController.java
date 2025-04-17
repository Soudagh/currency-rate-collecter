package org.currency.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.currency.data.dto.ExchangeRateDto;
import org.currency.service.RateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rate")
@RequiredArgsConstructor
public class RateController {

  private final RateService rateService;

  @GetMapping
  ResponseEntity<ExchangeRateDto> getCurseForDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var currencyList = rateService.getRatePerDate(date);
    var response = new ExchangeRateDto().setDate(date).setCurrencyList(currencyList);
    return ResponseEntity.ok(response);
  }
}
