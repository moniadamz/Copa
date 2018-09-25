package hackathon.copa.repositories;

import hackathon.entities.Jogador;
import hackathon.repositories.JogadorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class JogadorRepositoryTest{

    @Autowired
    private JogadorRepository jogadorRepository;

    @Test
    public void testFindByPosicaoOrCamisa(){
        PageRequest page = new PageRequest(0, 10);
        Page<Jogador> jogador = jogadorRepository.findByPosicaoOrCamisa("lateral esquerdo","11", page);
        assertEquals(2, jogador.getTotalElements());
    }

    @Test
    public void testFindByCpf(){
        Jogador jogador = jogadorRepository.findByCpf(506465010);
        assertEquals(506465010, jogador.getCpf());
    }
}
