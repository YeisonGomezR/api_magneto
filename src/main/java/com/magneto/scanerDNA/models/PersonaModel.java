package com.magneto.scanerDNA.models;

import io.swagger.annotations.ApiModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

/*
 * Autor: Yeison Andrey Gomez.
 * Fecha: 28/06/2021
 * Version: 1.0.0
 * */

@ApiModel
@Document("persona")
public class PersonaModel {

    private String id;
    private String[] dna;
    private boolean mutant;

    public PersonaModel(String[] dna) {
        this.dna = dna;
    }

    public PersonaModel(String[] dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }

    @Override
    public String toString() {
        return "PersonaModel{" +
                "id='" + id + '\'' +
                ", dna=" + Arrays.toString(dna) +
                ", mutant=" + mutant +
                '}';
    }
}
