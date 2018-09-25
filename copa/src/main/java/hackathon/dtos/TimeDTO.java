package hackathon.dtos;

import hackathon.entities.Jogador;

import java.util.List;

public class TimeDTO {
    private String nomeSelecao;
    private String nomeEstadio;
    private int cnpj;
    private List<Jogador> jogadores;

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

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }


}
