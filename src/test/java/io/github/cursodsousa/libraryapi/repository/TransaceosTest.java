package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransaceosTest {
    @Autowired
    TransacaoService transacaoService;


    /**
     * Commit -> Confirmar as alterações
     * Rollback -> desfazer as alterações
     * */
    @Test
    void transacaoSimples(){
      transacaoService.executar();

    }

    @Test
    void transacaoAtualizacaoTest(){
        transacaoService.atualizaSemAtualizar();
    }

}
