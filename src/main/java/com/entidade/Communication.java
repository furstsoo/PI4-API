package com.entidade;

import lombok.Data;

@Data
public class Communication {
  private int id;
  private String title;
  private int cd_user;
  private String message;
}
