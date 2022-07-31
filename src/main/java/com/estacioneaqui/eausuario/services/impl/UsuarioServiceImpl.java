package com.estacioneaqui.eausuario.services.impl;

import com.estacioneaqui.eausuario.models.UsuarioModel;
import com.estacioneaqui.eausuario.repositories.UsuarioRepository;
import com.estacioneaqui.eausuario.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void salvar(UsuarioModel usuario) {
        this.usuarioRepository.save(usuario);
    }

    @Override
    public boolean existsByUsuario(String usuario) {
        return this.usuarioRepository.existsByUsuario(usuario);
    }

    @Override
    public List<UsuarioModel> buscarTodos() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> buscarPorId(UUID idUsuario) {
        return this.usuarioRepository.findById(idUsuario);
    }

    @Override
    public void excluir(UUID idUsuario) {
        this.usuarioRepository.deleteById(idUsuario);
    }

}
