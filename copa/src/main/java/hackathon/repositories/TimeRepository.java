package hackathon.repositories;

import hackathon.entities.Selecao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TimeRepository extends JpaRepository<Selecao, Integer> {

    Selecao findByCnpj(int cnpj);

    Page<Selecao> findByNomeSelecaoOrNomeEstadio(String selecao, String estadio, Pageable pageable);

    Page<Selecao> findAll(Pageable pageable);




}
