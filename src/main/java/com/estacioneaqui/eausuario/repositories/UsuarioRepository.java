package com.estacioneaqui.eausuario.repositories;

import com.estacioneaqui.eausuario.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    boolean existsByUsuario(String nome);

}
