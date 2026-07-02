package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;



    @Test
    void salvarTeste(){
        Livro livro = new Livro();

        livro.setIsbn("845874-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setTitulo("Livro");
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setDataPublicacao(LocalDate.of(2020,1,1));
        Autor autor = autorRepository
                .findById(UUID.fromString("e3dfcdb8-4b4c-4a15-a67c-8256d0865931"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void salvarCascadeTeste(){

        Livro livro = new Livro();

        livro.setIsbn("54578-84874");
        livro.setPreco(BigDecimal.valueOf(180.85));
        livro.setTitulo("Spider Man");
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setDataPublicacao(LocalDate.of(2003,1,1));

        Autor autor = new Autor();
        autor.setNome("Stan Lee");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1961,6,23));

        livro.setAutor(autor);

        repository.save(livro);
    }


    @Test
    void atualizarAutorDoLivro(){
        UUID idLivro = UUID.fromString("173b724b-edb1-4ec7-9568-d78e3b1ec7ea") ;
        var livroParaAtualizar = repository.findById(idLivro).orElse(null);


        UUID idAutor = UUID.fromString("45d32b70-f746-4b50-8a5d-ea2f3407666a");
        Autor nomeAutor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(nomeAutor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("173b724b-edb1-4ec7-9568-d78e3b1ec7ea");

        repository.deleteById(id);
    }

    @Test
    void buscarLivroTeste(){
    UUID id = UUID.fromString("e206408c-813d-4aca-aa33-578c8f5f7abb");

    Livro livro = repository.findById(id).orElse(null);

        System.out.println("Título do livro: "+livro.getTitulo());
        System.out.println("Autor do Lívro: "+livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Desenvolvimento Java");
        lista.forEach(System.out::println);
    }




    @Test
    void pesquisarPorTituloAndPrecoTest(){
        List<Livro> lista = repository.findByTituloAndPreco("Desenvolvimento Java",BigDecimal.valueOf(110));
        lista.forEach(System.out::println);

    }


    @Test
    void pesquisarTodosOsLivrosTest(){
        List<Livro> lista = repository.listarTodos();
        lista.forEach(System.out::println);
    }
    @Test
    void pesquisarPorTituloEAutorTest(){
        List<Livro> lista = repository.listarTodosOrdenarPorTituloEAutor();
        lista.forEach(System.out::println);
    }

    @Test
    void listarAutorPorLivroTest(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetitivos(){
        var resultado = repository.listtarNomesDifierentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosDeAutoresBrasileiro(){
        var resultado = repository.listarGenerosAutoresBrasileiro();
        resultado.forEach(System.out::println);
    }

    @Test
    void listaLivroPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.FANTASIA,"preco");
        resultado.forEach(System.out::println);
    }
    @Test
    void listaLivroPorGeneroPostionParametersTest(){
        var resultado = repository.findByPositionalParameters(GeneroLivro.FANTASIA,"preco");
        resultado.forEach(System.out::println);
    }


    @Test
    void deletePOrGeneroTest(){
        repository.deleteByGenero(GeneroLivro.MISTERIO);
    }

    @Test
    void updateDataPublicacaoLivroTest(){
        repository.updateDataPublicacao(LocalDate.of(2000,1,1));
    }


}