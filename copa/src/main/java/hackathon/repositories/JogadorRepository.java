package hackathon.repositories;

import hackathon.entities.Jogador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.List;

@Repository
    @Transactional
    public interface JogadorRepository extends JpaRepository<Jogador, Integer>{

        Jogador findByCpf(int cpf);

        Page<Jogador> findByPosicaoOrCamisa(String posicao, String camisa, Pageable pageable);

        Jogador save(Jogador jogador);

        Page<Jogador> findAll(Pageable pageable);
    }