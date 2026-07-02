package io.github.cursodsousa.libraryapi.controller.dto;

import io.github.cursodsousa.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

//DTO - DAta Transferencia
public record AutorDTO(UUID id,
                       @NotBlank(message = "Campo obrigatório")
                       @Size(min = 2, max = 100,message = "Campo fora do tamanho")
                       String nome,
                       @NotNull(message = "Campo obrigatório")
                       @Past(message = "Não pode ser uma data futura")
                       LocalDate dataNascimento,
                       @NotBlank(message = "Campo obrigatório")
                       @Size(min = 2, max = 50,message = "Campo fora do tamanho")
                       String nacionalidade) {


    /* Para mapeamento sem o Mapper
    public Autor mapearParaAutor(){
        Autor autor = new Autor();

        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
*/


}
