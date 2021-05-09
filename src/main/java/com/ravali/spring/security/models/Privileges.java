package com.ravali.spring.security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authority;
    private String description;
    private Date createdDate;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "privileges")
    @JsonBackReference
    private List<Role> roles;
    private Role role;

}
