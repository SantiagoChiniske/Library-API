package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTeste(){
        Autor autor = new Autor();
        autor.setNome("Marcos");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951,12,16));
        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTeste(){
        var id = UUID.fromString("5938d078-bb34-43b6-ac44-068dbee22dbf");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1989,8,15));
            repository.save(autorEncontrado);
        }

    }

    @Test
    public void listarTeste(){
       List<Autor> lista = repository.findAll();
       lista.forEach(System.out::println);
    }

    @Test
    public void countTeste(){
        System.out.println("Contagem de autores: "+repository.count());
    }

    @Test
    public void deletePorIdTeste(){
        var id = UUID.fromString("5938d078-bb34-43b6-ac44-068dbee22dbf");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            repository.deleteById(id);
        }

    }

    @Test
    public void deleteTeste(){
        var id = UUID.fromString("f7f6f4d9-04da-4961-a81c-8ea488edd472");

        var maria = repository.findById(id).get();

        repository.delete(maria);


    }


    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Santiago");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2003,8,15));

        Livro livro = new Livro();
        livro.setIsbn("486855-84874");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setTitulo("Desenvolvimento WEB");
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setDataPublicacao(LocalDate.of(2020,12,18));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("808049-24548");
        livro2.setPreco(BigDecimal.valueOf(110));
        livro2.setTitulo("Desenvolvimento Java");
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setDataPublicacao(LocalDate.of(2025,2,18));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros()); //Só utilizo isso aqui quando não tenho na classe Autor @OneToMany(cascade = CascadeType.ALL) O Cascade
    }

    @Test
    void listarLivrosAutor(){
        UUID id = UUID.fromString("9919ee4e-80ca-4285-b9c3-a58a1ec276bb");
        var autor = repository.findById(id).orElse(null);

        List<Livro> livrosLista = livroRepository.findByAutor(autor);

        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }




}
