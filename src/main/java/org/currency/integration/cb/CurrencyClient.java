package org.currency.integration.cb;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.currency.wsdl.GetCursOnDate;
import org.currency.wsdl.GetCursOnDateResponse;
import org.currency.wsdl.GetCursOnDateResponse.GetCursOnDateResult;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Slf4j
public class CurrencyClient extends WebServiceGatewaySupport {

  public GetCursOnDateResult getCursOnDate(LocalDate date) {
    var request = new GetCursOnDate();
    var gCal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
    var xmlDate = DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(gCal);
    request.setOnDate(xmlDate);
    log.info("Requesting curs for date {}", date);
    var response = (GetCursOnDateResponse) getWebServiceTemplate()
        .marshalSendAndReceive(
            "https://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx",
            request,
            new SoapActionCallback("http://web.cbr.ru/GetCursOnDate")
        );
    return response.getGetCursOnDateResult();
  }

}