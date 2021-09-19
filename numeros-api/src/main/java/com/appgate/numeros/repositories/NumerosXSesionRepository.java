package com.appgate.numeros.repositories;

import com.appgate.numeros.models.entities.NumerosXSesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface NumerosXSesionRepository extends JpaRepository<NumerosXSesion, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM NumerosXSesion nxs WHERE nxs.sesionId = ?1")
    void borrarNumerosPorSesion(UUID sesionId);

    List<NumerosXSesion> findAllBySesionId(UUID sesionId);

}
