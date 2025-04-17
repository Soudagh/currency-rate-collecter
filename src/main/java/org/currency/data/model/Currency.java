package org.currency.data.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Currency {

  private String name;

  private String code;

  private BigDecimal rate;
}
