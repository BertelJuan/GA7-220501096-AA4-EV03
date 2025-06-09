package com.universidad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    private Usuario docente;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Nota> notas;

    public Long getId() { return id; }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getDocente() {
        return docente;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }

    public List<Nota> getNotas() {
        return notas;
    }
    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
