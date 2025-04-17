package org.currency.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.currency.data.GetCursParser;
import org.currency.data.entity.RatePerDate;
import org.currency.data.model.Currency;
import org.currency.integration.cb.CurrencyClient;
import org.currency.repository.RatePerDateRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyService {

  private final CurrencyClient currencyClient;

  private final RatePerDateRepository currencyRepository;

  public List<Currency> getCursOnDate(LocalDate date) {
    return currencyRepository.findRatePerDateByDate(date)
        .map(RatePerDate::getCurrencies)
        .orElseGet(() -> fetchAndCacheCurrencies(date));
  }

  private List<Currency> fetchAndCacheCurrencies(LocalDate date) {
    var currencyResponse = currencyClient.getCursOnDate(date);
    var currencies = GetCursParser.parseResult(currencyResponse);
    currencyRepository.save(new RatePerDate()
        .setDate(date)
        .setCurrencies(currencies));
    return currencies;
  }
}
