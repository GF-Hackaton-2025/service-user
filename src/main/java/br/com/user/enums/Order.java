package br.com.user.enums;

public enum Order {

  ASC("ASC"),
  DESC("DESC");

  private final String value;

  Order(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Order getOrderEnumByValue(String value) {
    for (Order order : Order.values())
      if (order.value.equalsIgnoreCase(value))
        return order;
    throw new IllegalArgumentException("Invalid order value: " + value);
  }
}
