package com.magneto.scanerDNA.models;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/*
 * Autor: Yeison Andrey Gomez.
 * Fecha: 28/06/2021
 * Version: 1.0.0
 * Descripción: Interfaz para el uso con la base de datos mongo para las operaciones correspondientes
 * */

@Repository
public interface PersonaRepository extends MongoRepository<PersonaModel,String> {

    //Consulta para las estadisticas de mutantes y humanos
    @Query(value = "{'mutant':?0}", count = true)
     Integer countPersons(boolean mutant);

    //Consulta para validar si existe o no ya un registro de DNA
    @Query(value = "{'dna':?0}", exists = true)
     boolean isExist(String[] dna);
}
