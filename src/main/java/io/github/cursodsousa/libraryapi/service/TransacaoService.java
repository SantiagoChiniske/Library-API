package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public void salvarLivroComFoto(){



    }

    @Transactional
    public void atualizaSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("276cdec2-7f43-48d1-8023-c87088a4f40d")).orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024,6,1));

    }


    @Transactional
    public void executar(){

        //Salvar o autor
        Autor autor = new Autor();
        autor.setNome("Test Francisco");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1961,6,23));

        autorRepository.saveAndFlush(autor);

        if(autor.getNome().equals("Stan Lee")){
            throw  new RuntimeException("Rollback");
        }

        //Salvar o livro
        Livro livro = new Livro();
        livro.setIsbn("54578-84874");
        livro.setPreco(BigDecimal.valueOf(180.85));
        livro.setTitulo("Test Livro do Fransisco");
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setDataPublicacao(LocalDate.of(2003,1,1));

        livro.setAutor(autor);
        livroRepository.saveAndFlush(livro);

        if(autor.getNome().equals("Test Francisco")){
            throw  new RuntimeException("Rollback");
        }

    }

}
