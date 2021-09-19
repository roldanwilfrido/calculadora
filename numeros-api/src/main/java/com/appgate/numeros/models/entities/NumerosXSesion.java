package com.appgate.numeros.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "numerossesion")
@Getter
@Setter
public class NumerosXSesion implements Serializable {
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column( columnDefinition = "uuid", updatable = false)
    private UUID sesionId;

    @Column
    private Number numero;
}
