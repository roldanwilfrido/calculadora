package com.appgate.sesiones.repositories;

import com.appgate.sesiones.models.entities.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SesionesRepository extends JpaRepository<Sesiones, UUID> {
}
