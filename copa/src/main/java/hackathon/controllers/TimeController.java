package hackathon.controllers;

import hackathon.ServicesImpl.TimeServiceImpl;
import hackathon.dtos.JogadorDTO;
import hackathon.dtos.TimeDTO;
import hackathon.entities.Jogador;
import hackathon.entities.Selecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Optional;

@RestController
@RequestMapping("/api/selecao")
@CrossOrigin(origins = "*")
public class TimeController {

    @Autowired
    private TimeServiceImpl timeService;

    /**
     * Retorna um time dado um CNPJ.
     *
     * @param cnpj
     * @return ResponseEntity
     */
    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity buscarPorCnpj(@PathVariable("cnpj") int cnpj) {
        try {
            Selecao selecao = this.timeService.findByCnpj(cnpj);
            return ResponseEntity.ok(this.converterTimeDTO(selecao));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Retorna todas as seleções
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(value = "todas")
    public ResponseEntity buscarTodas(Pageable pageable){
        try {
            return ResponseEntity.ok(this.timeService.buscarTodas(pageable));
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Retorna uma seleção dado seu nome ou nome do seu estadio.
     * @param selecao
     * @param estadio
     * @param pageable
     * @return ResponseEntity
     */
    @RequestMapping
    public ResponseEntity buscarSelecaoOuEstadio(@RequestParam(value = "selecao") String selecao,
                                                @RequestParam(value = "estadio") String estadio,
                                                Pageable pageable) {
       try {
           return ResponseEntity.ok(timeService.buscarSelecaoOuEstadio(selecao, estadio, pageable));
       }catch (Exception e){
           return ResponseEntity.unprocessableEntity().body(e.getMessage());
       }
    }

    /**
     * Retorna o status de uma seleção dado um CNPJ.
     * @param cnpj
     * @return ResponseEntity
     */
    @GetMapping(value = "/estaApto/{cnpj}")
    public ResponseEntity verificarAptoParaJogar(@PathVariable("cnpj") int cnpj){
        try {
            String response = this.timeService.estaApto(cnpj) ? "O time está apto à jogar." : "O time não está apto a jogar.";

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Adiciona uma seleção.
     * @param selecao
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity persistir(@Valid @RequestBody Selecao selecao) {
        try{
            return ResponseEntity.ok(this.timeService.persistir(selecao));
        } catch(Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Atualiza uma seleção dado um CNPJ.
     * @param cnpj
     * @param novosDadosSelecao
     * @return ResponseEntity
     */
    @PutMapping(value = "/{cnpj}")
    public ResponseEntity atualizar(@PathVariable("cnpj") int cnpj, @Valid @RequestBody TimeDTO novosDadosSelecao){
        try{

            TimeDTO selecaoDTO = this.converterTimeDTO(this.timeService.findByCnpj(cnpj));
            selecaoDTO.setNomeEstadio(novosDadosSelecao.getNomeEstadio());
            selecaoDTO.setNomeSelecao(novosDadosSelecao.getNomeSelecao());

            Selecao selecao = converterTimeDTOParaSelecao(selecaoDTO);

            return ResponseEntity.ok(this.timeService.atualizar(selecao));

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    /**
     * Deleta uma seleção dado um CNPJ.
     * @param cnpj
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/{cnpj}")
    public ResponseEntity remover(@PathVariable("cnpj") int cnpj){
        try{
            this.timeService.delete(cnpj);
            return ResponseEntity.ok("Removido com sucesso.");
        } catch(Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    private TimeDTO converterTimeDTO(Selecao selecao) {
        TimeDTO selecaoDTO = new TimeDTO();
        selecaoDTO.setCnpj(selecao.getCnpj());
        selecaoDTO.setNomeSelecao(selecao.getNomeSelecao());
        selecaoDTO.setNomeEstadio(selecao.getNomeEstadio());
        selecaoDTO.setJogadores(selecao.getJogadores());

        return selecaoDTO;
    }

    private Selecao converterTimeDTOParaSelecao(TimeDTO selecaoDTO){
        Selecao selecao = new Selecao();
        selecao.setCnpj(selecaoDTO.getCnpj());
        selecao.setNomeSelecao(selecaoDTO.getNomeSelecao());
        selecao.setNomeEstadio(selecaoDTO.getNomeEstadio());
        selecao.setJogadores(selecaoDTO.getJogadores());

        return selecao;
    }

}
