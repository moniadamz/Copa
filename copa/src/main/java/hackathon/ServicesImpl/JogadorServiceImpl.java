package hackathon.ServicesImpl;

import hackathon.entities.Jogador;

import hackathon.repositories.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorServiceImpl {

    @Autowired
    private JogadorRepository jogadorRepository;
    private TimeServiceImpl timeService;
    private static String GOLEIRO;

    public Jogador buscarPorCpf(int cpf) {
        return jogadorRepository.findByCpf(cpf);
    }

    public Jogador persistir(Jogador jogador) {
        if (this.buscarPorCpf(jogador.getCpf()) == null && this.adicionarJogadorAoTime(jogador)) {
            return jogadorRepository.save(jogador);
        }else{
            throw new RuntimeException("Não foi possível adicionar o jogador.");
        }

    }

    public Page<Jogador> buscarTodos(Pageable pageable){
        return jogadorRepository.findAll(pageable);
    }

    public Page<Jogador> buscarPorPosicaoOuCamisa(String posicao, String camisa, Pageable pageable){
        return jogadorRepository.findByPosicaoOrCamisa(posicao, camisa, pageable);
    }

    public Jogador atualizar(Jogador jogador) {
        if (this.buscarPorCpf(jogador.getCpf()) != null) {
            return jogadorRepository.save(jogador);
        }else{
            throw new RuntimeException("Não foi possível atualizar o jogador.");
        }

    }

    public void remover(int cpf) {
        this.jogadorRepository.delete(cpf);
    }

    public boolean adicionarJogadorAoTime(Jogador novoJogador) {

        List<Jogador> jogadores = this.timeService.findByCnpj(novoJogador.getSelecao().getCnpj()).getJogadores();

        if (jogadores.size() < 11) {
            for (Jogador jogador : jogadores){
                 if(jogador.getCamisa().equals(novoJogador.getCamisa())){
                     return false;
                 }
                 if(jogador.getPosicao().equals(GOLEIRO) && novoJogador.getPosicao().equals(GOLEIRO)){
                    return false;
                 }
                 else{
                     return true;
                 }
            }
        }
        return false;
    }

}
