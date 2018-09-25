package hackathon.dtos;

import hackathon.entities.Selecao;

public class JogadorDTO {
    private String nome;
    private String posicao;
    private String camisa;
    private int cpf;
    private Selecao selecao;

    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public String getCamisa() {
        return camisa;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setCamisa(String camisa) {
        this.camisa = camisa;
    }
}
