package br.com.user.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

  public static boolean isEmpty(String value) {
    return (value == null) || value.isEmpty();
  }

  public static boolean isNonEmpty(String value) {
    return (value != null) && !value.isEmpty();
  }

  public static boolean isAnyEquals(String value, String... items) {
    for(String item : items) {
      if (equals(value, item)) {
        return true;
      }
    }

    return false;
  }

  public static boolean equals(String a, String b) {
    if (a != null && b != null) {
      return a.equalsIgnoreCase(b);
    } else {
      return a == b;
    }
  }

  public static boolean startsWith(String text, String prefix) {
    return text != null && prefix != null ? text.toLowerCase().startsWith(prefix.toLowerCase()) : false;
  }
}
