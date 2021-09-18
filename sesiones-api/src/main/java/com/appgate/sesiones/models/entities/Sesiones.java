package com.appgate.sesiones.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "sesiones")
@Getter
@Setter
public class Sesiones implements Serializable {
    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false)
    private UUID id;
}
