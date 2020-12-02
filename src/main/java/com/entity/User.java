package com.entity;

import lombok.Data;

@Data
public class User {
  private int id;
  private String name;
  private String email;
  private String password;
  private String apartment;
  private String block;
  private String typeUser; /* tp_usuario: A = Admin; C = Condomino; O = Operador/porteiro*/
}