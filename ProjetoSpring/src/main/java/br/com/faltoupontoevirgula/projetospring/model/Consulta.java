package br.com.faltoupontoevirgula.projetospring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy H:mm")
    @Temporal(value=TemporalType.TIMESTAMP)
    private Date data;
    @Column(length=10000)
    private String status;
   
    @ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
    private Paciente paciente;
   
    @ManyToOne(cascade= {CascadeType.MERGE,CascadeType.REFRESH})
    private Medico medicoResponsavel;
   
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
    @JoinColumn(name="consulta_id")
    private List<ProcedimentoRealizado> listaProcedimentos = new ArrayList<ProcedimentoRealizado>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public void setMedicoResponsavel(Medico medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }
   
    public List<ProcedimentoRealizado> getListaProcedimentos() {
        return listaProcedimentos;
    }

    public void setListaProcedimentos(List<ProcedimentoRealizado> listaProcedimentos) {
        this.listaProcedimentos = listaProcedimentos;
    }
}