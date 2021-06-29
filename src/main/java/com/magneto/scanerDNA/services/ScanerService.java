package com.magneto.scanerDNA.services;

import com.magneto.scanerDNA.models.PersonaRepository;
import com.magneto.scanerDNA.models.Stats;
import org.springframework.beans.factory.annotation.Autowired;

public class ScanerService {

    @Autowired
    private PersonaRepository repository;

    public final static String baseN = "ATCG";


    public boolean isMutant(String [] dna) {
        int secuences = 0;  //contador de secuencias

        if(isComplete(dna)) { //Evalua si la matriz es NxN y que mínimo sea de 4x4

            for (int i = 0; i < dna.length; i++) {

                dna[i] = dna[i].toUpperCase();

                for (int j = 0; j < dna[i].length(); j++) {
                    if (baseN.indexOf(dna[i].charAt(j)) != -1) {  //valida que los caracteres sean correctos

                        //Escaneo horizontal
                        if (j + 3 < dna[i].length()) {
                            if (evaluatorChars(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2), dna[i].charAt(j + 3))) {
                                secuences++;
                            }
                        }

                        //Escaneo Vertical
                        if (i + 3 < dna.length) {
                            if (evaluatorChars(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j), dna[i + 3].charAt(j))) {
                                secuences++;
                            }

                        }

                        //Escaneo diagonal izquierda a derecha
                        if (i + 3 < dna.length && j + 3 < dna[i].length()) {
                            if (evaluatorChars(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                                secuences++;

                            }
                        }

                        //Escaneo diagonal derecha a izquierda
                        if (j > 2 && i + 3 < dna.length) {
                            if (evaluatorChars(dna[i].charAt(j), dna[i + 1].charAt(j - 1), dna[i + 2].charAt(j - 2), dna[i + 3].charAt(j - 3))) {
                                secuences++;
                            }
                        }

                    }

                }

            }

        }
        else {
            System.out.println("ADN incompleto...");
        }

        if(secuences > 1){
            return true;
        }
        else {
            return false;
        }

    }


    //metodo que evalua la secuencias de caracteres
    public boolean evaluatorChars(char a, char b, char c, char d){
        return a == b && b==c && c == d;
    }


    //metodo que evalua el tamaño de la matriz
    public boolean isComplete(String[] base){
        boolean complete = false;

        if ( base.length > 3){
            for (int i = 0; i < base.length;i++){
                if(base[i].length() == base.length){
                    complete = true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            complete = false;
        }
        return complete;

    }


}
