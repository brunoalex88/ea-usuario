package com.estacioneaqui.eausuario.services.impl;

import com.estacioneaqui.eausuario.models.UsuarioModel;
import com.estacioneaqui.eausuario.repositories.UsuarioRepository;
import com.estacioneaqui.eausuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioModel salvar(UsuarioModel usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return this.usuarioRepository.existsByUsuario(usuario);
    }

    @Override
    public List<UsuarioModel> buscarTodos() {
        return this.usuarioRepository.findAll();
    }

}
