package org.currency.data;

import jakarta.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.currency.data.model.Currency;
import org.currency.wsdl.GetCursOnDateResponse.GetCursOnDateResult;
import org.w3c.dom.Element;

public class GetCursParser {

  public static List<Currency> parseResult(GetCursOnDateResult response) {
    var result = response.getAny();
    List<Currency> list = new ArrayList<>();
    try {
      if (result instanceof JAXBElement<?> jaxb) {
        result = jaxb.getValue();
      }
      if (result instanceof Element element) {
        var nodes = element.getElementsByTagName("ValuteCursOnDate");
        for (int i = 0; i < nodes.getLength(); i++) {
          var row = (Element) nodes.item(i);
          var code = row.getElementsByTagName("VchCode").item(0).getTextContent();
          var name = row.getElementsByTagName("Vname").item(0).getTextContent().trim();
          var value = row.getElementsByTagName("Vcurs").item(0).getTextContent();
          var currency = new Currency()
              .setCode(code)
              .setName(name)
              .setRate(new BigDecimal(value));
          list.add(currency);
        }
      } else {
        throw new IllegalStateException("Unexpected type: " + result.getClass().getName());
      }
    } catch (Exception e) {
      throw new RuntimeException("XML parsing exception", e);
    }
    return list;
  }
}
