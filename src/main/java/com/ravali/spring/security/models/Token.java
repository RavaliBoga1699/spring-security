package com.ravali.spring.security.models;

import com.ravali.spring.security.dto.enums.TokenStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String userName;
    private String token;
    private TokenStatus status;
    private int validUpto;
    private Date createdDate;


}
