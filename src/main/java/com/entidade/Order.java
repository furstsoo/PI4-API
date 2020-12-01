package com.entidade;

import lombok.Data;

@Data
public class Order {
  private int id_order;
  private String addressee;
  private String status;
  private String apartment;
  private String block;
  private String dt_delivery;
  private String dt_pickup;
  private int cd_user;
}
