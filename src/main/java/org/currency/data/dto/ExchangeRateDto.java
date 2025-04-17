package org.currency.data.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.currency.data.model.Currency;

@Data
@Accessors(chain = true)
public class ExchangeRateDto {

  private LocalDate date;

  private List<Currency> currencyList;
}
