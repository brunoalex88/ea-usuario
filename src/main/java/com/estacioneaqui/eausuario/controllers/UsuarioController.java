package com.estacioneaqui.eausuario.controllers;

import com.estacioneaqui.eausuario.dtos.UsuarioDto;
import com.estacioneaqui.eausuario.models.UsuarioModel;
import com.estacioneaqui.eausuario.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        if (this.usuarioService.existsByUsuario(usuarioDto.getUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já existe!");
        }

        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        usuarioModel.setDataCadastro(LocalDateTime.now(ZoneId.of("UTC")));
        usuarioModel.setDataAlteracao(LocalDateTime.now(ZoneId.of("UTC")));
        this.usuarioService.salvar(usuarioModel);

        return ResponseEntity.ok(usuarioModel);
    }

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return ResponseEntity.ok(this.usuarioService.buscarTodos());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarPorId(@PathVariable("idUsuario") UUID idUsuario) {
        return ResponseEntity.ok(this.usuarioService.buscarPorId(idUsuario));
    }

}
