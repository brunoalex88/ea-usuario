package com.estacioneaqui.eausuario.controllers;

import com.estacioneaqui.eausuario.dtos.UsuarioDto;
import com.estacioneaqui.eausuario.models.UsuarioModel;
import com.estacioneaqui.eausuario.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrarUsuario(@RequestBody @Validated UsuarioDto usuarioDto) {
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

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Object> alterarUsuario(@PathVariable UUID idUsuario,
                                                 @RequestBody @Validated(UsuarioDto.UsuarioView.UsuarioPut.class) UsuarioDto usuarioDto) {
        Optional<UsuarioModel> usuarioModelOptional = this.usuarioService.buscarPorId(idUsuario);
        if (usuarioModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        var usuarioModel = usuarioModelOptional.get();
        usuarioModel.setNome(usuarioDto.getNome());
        usuarioModel.setEmail(usuarioDto.getEmail());
        usuarioModel.setTelefone(usuarioDto.getTelefone());
        usuarioModel.setDataAlteracao(LocalDateTime.now(ZoneId.of("UTC")));
        this.usuarioService.salvar(usuarioModel);
        return ResponseEntity.ok(usuarioModel);
    }

}
