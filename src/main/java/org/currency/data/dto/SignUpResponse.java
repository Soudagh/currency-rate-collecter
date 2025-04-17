package org.currency.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SignUpResponse {

  private Long id;

  private String email;
}
