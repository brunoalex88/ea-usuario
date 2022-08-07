package com.estacioneaqui.eausuario.controllers;

import com.estacioneaqui.eausuario.dtos.UsuarioDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerRegistrarIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveRegistrarUmUsuario_quandoOsDadosForemValidos() throws Exception {
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nome").value("Bruno"))
                .andExpect(jsonPath("$.usuario").value("obruno"))
                .andExpect(jsonPath("$.email").value("email@email"))
                .andExpect(jsonPath("$.cpf").value("362.710.578-88"))
                .andExpect(jsonPath("$.telefone").value("123"))
                .andExpect(jsonPath("$.dataCadastro").isNotEmpty())
                .andExpect(jsonPath("$.dataAlteracao").isNotEmpty());

    }

    @Test
    void deveRetornar409_quandoUsuarioJaExistir() throws Exception {
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

}
