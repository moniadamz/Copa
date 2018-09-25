package hackathon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    private int cpf;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "posicao", nullable = false)
    private String posicao;
    @Column(name = "camisa", nullable  = false)
    private String camisa;
    @ManyToOne(optional = true)
    @JsonIgnore
    private Selecao selecao;


    public Jogador() {}

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getCamisa() {
        return camisa;
    }

    public void setCamisa(String camisa) {
        this.camisa = camisa;
    }

    public Selecao getSelecao() {
        return selecao;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", posicao='" + posicao + '\'' +
                ", camisa='" + camisa + '\'' +
                ", selecao=" + selecao +
                '}';
    }
}