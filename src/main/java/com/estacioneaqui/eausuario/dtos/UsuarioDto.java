package com.estacioneaqui.eausuario.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UsuarioDto {

    public interface UsuarioView {
        interface UsuarioPut {}
    }

    @NotBlank(groups = UsuarioView.UsuarioPut.class)
    @Size(min = 4, max = 150, groups = UsuarioView.UsuarioPut.class)
    @JsonView(UsuarioView.UsuarioPut.class)
    private String nome;

    @NotBlank
    @Size(min = 4, max = 20)
    private String usuario;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @Email(groups = UsuarioView.UsuarioPut.class)
    @NotBlank(groups = UsuarioView.UsuarioPut.class)
    @JsonView(UsuarioView.UsuarioPut.class)
    private String email;

    @CPF
    private String cpf;

    @JsonView(UsuarioView.UsuarioPut.class)
    private String telefone;

}
