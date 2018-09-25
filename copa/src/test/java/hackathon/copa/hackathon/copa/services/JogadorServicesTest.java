package hackathon.copa.hackathon.copa.services;


import hackathon.ServicesImpl.JogadorServiceImpl;
import hackathon.entities.Jogador;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JogadorServicesTest {

    @Autowired
    private JogadorServiceImpl jogadorService;

    @Test
    public void testBuscarPorCpf() {
        Jogador jogador = jogadorService.buscarPorCpf(506465010);
        assertEquals(506465010, jogador.getCpf());
    }

    @Test
    public void testPersistir(){
        Jogador jogador = jogadorService.persistir(new Jogador());
        assertNotNull(jogador);
    }
}
