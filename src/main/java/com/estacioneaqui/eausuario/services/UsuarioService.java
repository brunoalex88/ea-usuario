package com.estacioneaqui.eausuario.services;

import com.estacioneaqui.eausuario.models.UsuarioModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    void salvar(UsuarioModel usuario);
    boolean existsByUsuario(String nome);
    List<UsuarioModel> buscarTodos();
    Optional<UsuarioModel> buscarPorId(UUID idUsuario);

}
