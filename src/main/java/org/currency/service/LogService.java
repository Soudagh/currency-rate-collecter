package org.currency.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.currency.data.entity.Log;
import org.currency.data.entity.User;
import org.currency.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

  private final LogRepository logRepository;

  public void saveLog(User user, LocalDate localDate) {
    logRepository.save(new Log().setUser(user).setDate(localDate));
  }
}
