package com.estacioneaqui.eausuario.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UsuarioDto {

    @NotBlank
    @Size(min = 4, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 4, max = 20)
    private String usuario;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Email
    @NotBlank
    private String email;

    @CPF
    private String cpf;
    private String telefone;

}
