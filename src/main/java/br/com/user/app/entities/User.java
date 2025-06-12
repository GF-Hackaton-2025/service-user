package br.com.user.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
@TypeAlias("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private ObjectId id;
  private String name;
  private String user;
  private String password;
  private String email;

  @Transient
  @JsonIgnore
  private String accessToken;

}
