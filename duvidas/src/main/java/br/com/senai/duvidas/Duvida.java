package br.com.senai.duvidas;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.time.ZoneId;

//Classe de Entidade (representa uma tabela)
@Table(name="duvida")
@Entity(name ="Duvida")
public class Duvida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY ) 
    private Long id;  

    @Column(columnDefinition = "TEXT")
    private String texto; 

    @Column(name="dataHora",nullable=false)
    private LocalDateTime dataHora;

    public Duvida(){ }
    public Duvida(DadosCadastroDuvida dados){
       this.texto = dados.texto();
       this.dataHora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    

    
}


   


