package hackathon.ServicesImpl;

import hackathon.entities.Jogador;
import hackathon.entities.Selecao;
import hackathon.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeServiceImpl {

    @Autowired
    private TimeRepository timeRepository;
    private static String GOLEIRO;


    public Selecao findByCnpj(int cnpj){
        return this.timeRepository.findByCnpj(cnpj);
    }

    public Page<Selecao> buscarSelecaoOuEstadio(String selecao, String estadio, Pageable pageable){
        return timeRepository.findByNomeSelecaoOrNomeEstadio(selecao, estadio, pageable);
    }

    public Page<Selecao> buscarTodas(Pageable pageable){
        return this.timeRepository.findAll(pageable);
    }

    public Selecao persistir(Selecao selecao){
        if(this.findByCnpj(selecao.getCnpj()) == null){
            return this.timeRepository.save(selecao);
        }else{
            throw new RuntimeException("Essa seleção já existe.");
        }
    }

    public Selecao atualizar(Selecao selecao){
        if(this.findByCnpj(selecao.getCnpj()) != null){
            return this.timeRepository.save(selecao);
        }else{
            throw new RuntimeException("Não foi possível atualizar a seleção.");
        }
    }

    public boolean estaApto(int cnpj){
        List<Jogador> jogadores = this.findByCnpj(cnpj).getJogadores();

        if(jogadores.size() < 11){
            return false;
        }else{
            for(Jogador jogador: jogadores){
                if (jogador.getPosicao().equals(GOLEIRO)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void delete(int cnpj){
        this.timeRepository.delete(cnpj);
    }
}
