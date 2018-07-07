package br.com.faltoupontoevirgula.projetospring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProcedimentoRealizado {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(length=10000)
    private String descricao;
    private float valor;
    @ManyToOne(cascade= {CascadeType.REFRESH,CascadeType.MERGE})
    private Procedimento procedimento;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public Procedimento getProcedimento() {
        return procedimento;
    }
    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }
}