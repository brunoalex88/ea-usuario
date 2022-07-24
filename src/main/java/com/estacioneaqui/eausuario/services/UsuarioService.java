package com.estacioneaqui.eausuario.services;

import com.estacioneaqui.eausuario.models.UsuarioModel;

import java.util.List;

public interface UsuarioService {

    UsuarioModel salvar(UsuarioModel usuario);
    boolean existsByUsuario(String nome);
    List<UsuarioModel> buscarTodos();

}
