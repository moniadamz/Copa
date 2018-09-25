package hackathon.copa.repositories;

import hackathon.entities.Jogador;
import hackathon.entities.Selecao;
import hackathon.repositories.TimeRepository;
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
public class TimeRepositoryTest {

    @Autowired
    private TimeRepository timeRepository;

    @Test
    public void testFindByNomeSelecaoOrNomeEstadio(){
        PageRequest page = new PageRequest(0, 10);
        Page<Selecao> selecao = timeRepository.findByNomeSelecaoOrNomeEstadio("Brasil","Maracana", page);
        assertEquals(1, selecao.getTotalElements());
    }

    @Test
    public void testFindByCnpj(){
        Selecao selecao = timeRepository.findByCnpj(984890480);
        assertEquals(984890480, selecao.getCnpj());
    }

}
