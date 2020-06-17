package br.com.bookapi.models;

import java.util.UUID;

/**
 * @author user
 */
public class Autor {
    
    private UUID id;
    private String nome;

    public Autor() {
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
