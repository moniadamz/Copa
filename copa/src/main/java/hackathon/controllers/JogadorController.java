package hackathon.controllers;

import hackathon.ServicesImpl.JogadorServiceImpl;
import hackathon.dtos.JogadorDTO;
import hackathon.entities.Jogador;
import hackathon.entities.Selecao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/api/jogador")
@CrossOrigin(origins = "*")
public class JogadorController {
    private static final Logger log = LoggerFactory.getLogger(JogadorController.class);

    @Autowired
    private JogadorServiceImpl jogadorService;

    /**
     * Retorna um jogador dado um CPF.
     * @param cpf
     * @return ResponseEntity
     */
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity buscarPorCpf(@PathVariable("cpf") int cpf){
        try{
            log.info("Buscando jogador por cpf: {} ", cpf);

            Jogador jogador = jogadorService.buscarPorCpf(cpf);
            return ResponseEntity.ok(this.converterJogadorDTO(jogador));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Retorna todos os jogadores.
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(value = "/todos")
    public ResponseEntity buscarTodos(Pageable pageable){
        try {
            return ResponseEntity.ok(jogadorService.buscarTodos(pageable));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     *
     * @param posicao
     * @param camisa
     * @param pageable
     * @return ResponseEntity
     */
    @RequestMapping
    public ResponseEntity buscarPorPosicaoOuCamisa(@RequestParam(value = "posicao") String posicao,
                                                  @RequestParam(value = "camisa") String camisa,
                                                  Pageable pageable){
        try {
            return ResponseEntity.ok(jogadorService.buscarPorPosicaoOuCamisa(posicao, camisa, pageable));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Cria um jogador.
     * @param jogador
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity adicionar(@Valid @RequestBody Jogador jogador){
        try {
            return ResponseEntity.ok(this.jogadorService.persistir(jogador));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Deleta um jogador dado um CPF.
     * @param cpf
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity remover(@PathVariable("cpf") int cpf){
       try{
           this.jogadorService.remover(cpf);
           return ResponseEntity.ok("Removido com sucesso");
       } catch (Exception e) {
           return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Atualiza um jogador.
     * @param novosDadosJogador
     * @return ResponseEntity
     */
    @PutMapping(value = "/{cpf}")
    public ResponseEntity atualizar(@PathVariable("cpf") int cpf, @Valid @RequestBody JogadorDTO novosDadosJogador){
        try{
            JogadorDTO jogadorDTO = this.converterJogadorDTO(this.jogadorService.buscarPorCpf(cpf));
            jogadorDTO.setPosicao(novosDadosJogador.getPosicao());
            jogadorDTO.setNome(novosDadosJogador.getNome());
            jogadorDTO.setCamisa(novosDadosJogador.getCamisa());
            jogadorDTO.setSelecao(novosDadosJogador.getSelecao());
            Jogador jogador = this.converterJogadorDTOparaJogador(jogadorDTO);

            return ResponseEntity.ok(this.jogadorService.atualizar(jogador));

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    private JogadorDTO converterJogadorDTO(Jogador jogador) {
        JogadorDTO jogadorDTO = new JogadorDTO();
        jogadorDTO.setCpf(jogador.getCpf());
        jogadorDTO.setNome(jogador.getNome());
        jogadorDTO.setCamisa(jogador.getCamisa());
        jogadorDTO.setPosicao(jogador.getPosicao());
        jogadorDTO.setSelecao(jogador.getSelecao());

        return jogadorDTO;
    }

    private Jogador converterJogadorDTOparaJogador(JogadorDTO jogadorDTO) {
        Jogador jogador = new Jogador();
        jogador.setCpf(jogadorDTO.getCpf());
        jogador.setNome(jogadorDTO.getNome());
        jogador.setCamisa(jogadorDTO.getCamisa());
        jogador.setPosicao(jogadorDTO.getPosicao());
        jogador.setSelecao(jogadorDTO.getSelecao());

        return jogador;
    }

}
