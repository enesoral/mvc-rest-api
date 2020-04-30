package com.enesoral.mvcrest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
}
