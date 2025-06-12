package br.com.user.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

  public boolean isEmpty(String value) {
    return (value == null) || value.isEmpty();
  }

  public boolean isNonEmpty(String value) {
    return (value != null) && !value.isEmpty();
  }

  public boolean isAnyEquals(String value, String... items) {
    for(String item : items) {
      if (equals(value, item)) {
        return true;
      }
    }

    return false;
  }

  public boolean equals(String a, String b) {
    if (a != null && b != null) {
      return a.equalsIgnoreCase(b);
    } else {
      return a == b;
    }
  }
}
