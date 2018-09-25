package hackathon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "selecao")
public class Selecao {

    @Column(name = "nome_selecao", nullable = false)
    private String nomeSelecao;
    @Column(name = "nome_estadio", nullable = false)
    private String nomeEstadio;
    @Id
    private int cnpj;
    @OneToMany(mappedBy="selecao",fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Jogador> jogadores;

    public Selecao(){
        jogadores = new ArrayList<Jogador>();
    }

    public int getCnpj() {
        return cnpj;
    }
    public String getNomeSelecao() {
        return nomeSelecao;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public String toString() {
        return "Selecao{" +
                "nomeSelecao='" + nomeSelecao + '\'' +
                ", nomeEstadio='" + nomeEstadio + '\'' +
                ", cnpj=" + cnpj +
                ", jogadores=" + jogadores +
                '}';
    }
}