package org.currency.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.currency.data.model.Currency;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateService {

  private final LogService logService;

  private final UserService userService;

  private final CurrencyService currencyService;

  @Transactional
  public List<Currency> getRatePerDate(LocalDate date) {
    var user = userService.getCurrentUser();
    logService.saveLog(user, date);
    return currencyService.getCursOnDate(date);
  }
}
