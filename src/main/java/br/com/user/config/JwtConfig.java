package br.com.user.config;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtConfig {

  public static final String issuer = "service-user";
  public static final Algorithm algorithm = Algorithm.HMAC256("F5Mc7x1nBDhfq2pI4AZc6fGOlIZEVDCOvc2I");

}
