package com.estacioneaqui.eausuario.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UsuarioDto {

    public interface UsuarioView {
        interface UsuarioPut {}
    }

    @NotBlank
    @Size(min = 4, max = 150)
    @JsonView(UsuarioView.UsuarioPut.class)
    private String nome;

    @NotBlank
    @Size(min = 4, max = 20)
    private String usuario;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Email
    @NotBlank
    @JsonView(UsuarioView.UsuarioPut.class)
    private String email;

    @CPF
    private String cpf;

    @JsonView(UsuarioView.UsuarioPut.class)
    private String telefone;

}
