package com.escolaTI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "pets")
public class Pet {

    @Id
    private String id;

    private String nome;
    private String raca;
    private String fotoUrl;
    private String criadorId;
    private List<String> coTutoresIds;

    public Pet() {
    }

    public Pet(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
    }

    public Pet(String nome, String raca, String fotoUrl, String criadorId, List<String> coTutoresIds) {
        this.nome = nome;
        this.raca = raca;
        this.fotoUrl = fotoUrl;
        this.criadorId = criadorId;
        this.coTutoresIds = coTutoresIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getCriadorId() {
        return criadorId;
    }

    public void setCriadorId(String criadorId) {
        this.criadorId = criadorId;
    }

    public List<String> getCoTutoresIds() {
        return coTutoresIds;
    }

    public void setCoTutoresIds(List<String> coTutoresIds) {
        this.coTutoresIds = coTutoresIds;
    }
}