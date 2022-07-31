package com.estacioneaqui.eausuario.controllers;

import com.estacioneaqui.eausuario.dtos.UsuarioDto;
import com.estacioneaqui.eausuario.models.UsuarioModel;
import com.estacioneaqui.eausuario.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerRegistrarTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void retornaStatus400AoRegistrar_quandoOsDadosForemInvalidos() throws Exception {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome("nome")
                .usuario("123")
                .senha("123")
                .email("email")
                .cpf("123")
                .telefone("123").build();

        String body = objectMapper.writeValueAsString(usuarioDto);

        mockMvc.perform(post("/usuarios/registrar")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

    }

    @Test
    void retornaStatus409AoRegistrar_quandoUsuarioJaExistir() throws Exception {

        when(usuarioService.existsByUsuario(anyString())).thenReturn(true);

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome("Bruno")
                .usuario("obruno")
                .senha("123123")
                .email("email@email")
                .cpf("362.710.578-88")
                .telefone("123").build();

        String body = objectMapper.writeValueAsString(usuarioDto);

        mockMvc.perform(post("/usuarios/registrar")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isConflict());
    }

    @Test
    void retornaStatus200AoRegistrar_quandoOsDadosForemValidos() throws Exception {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nome("Bruno")
                .usuario("obruno")
                .senha("123123")
                .email("email@email")
                .cpf("362.710.578-88")
                .telefone("123").build();

        String body = objectMapper.writeValueAsString(usuarioDto);

        mockMvc.perform(post("/usuarios/registrar")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).salvar(any(UsuarioModel.class));

    }

}
