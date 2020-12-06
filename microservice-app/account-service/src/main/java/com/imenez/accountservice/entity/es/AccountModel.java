package com.imenez.accountservice.entity.es;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(of = { "id" })
@Document(indexName = "account")
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel implements Serializable {

    @Id
    private String id ;

    private String username;

    private String name;

    private String surname;

    private Date birthDate;

    private String email;

    private Date createdAt;


}
