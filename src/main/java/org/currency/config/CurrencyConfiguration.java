package org.currency.config;

import org.currency.integration.cb.CurrencyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CurrencyConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    var marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("org.currency.wsdl");
    return marshaller;
  }

  @Bean
  public CurrencyClient currencyClient(Jaxb2Marshaller marshaller) {
    var client = new CurrencyClient();
    client.setDefaultUri("https://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
