package br.com.user.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class Dates {

  public Date parseDate(LocalDateTime date) {
    return date == null ? null : Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
  }

  public String format(LocalDateTime date) {
    return date == null ? null : DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(date);
  }
}
