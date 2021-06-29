package com.magneto.scanerDNA.controllers;

import com.magneto.scanerDNA.models.PersonaModel;
import com.magneto.scanerDNA.models.PersonaRepository;
import com.magneto.scanerDNA.models.Stats;
import com.magneto.scanerDNA.services.ScanerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
* Autor: Yeison Andrey Gomez.
* Fecha: 28/06/2021
* Version: 1.0.0
* Descripción: Controlador de la API encargado de recibir las peticiones, post con un json tipo { dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
*  con el DNA a analizar si es o no un mutuante en /mutant/; petición Get para obtener las estadistica en json  en /stats/
* */


@RestController
public class ScannerDNAController {

    @Autowired
    private PersonaRepository repository;

    private Logger logger;

    @PostMapping(value = "/mutant")
    @ApiOperation(value = "Scanner DNA", notes = "Determine if a person is mutant or not based on their DNA ")
    public ResponseEntity<String> scanDNA(  @ApiParam(name = "DNA",
                                                        type = "Json Array String",
                                                        value = "DNA - Example: {\n" +
                                                                "\"dna\":[\n" +
                                                                "    \"ATGCAA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"GGTATA\",\"TCACTG\"\n" +
                                                                "   ]\n" +
                                                                "} ",
                                                        required = true
                                                        )
                                            @RequestBody PersonaModel persona){
        boolean mutant = new ScanerService().isMutant(persona.getDna());

        if(!repository.isExist(persona.getDna())) {
            repository.insert(new PersonaModel(persona.getDna(), mutant));
        }

        if(mutant){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/stats" , produces = "application/json")
    @ApiOperation(value = "Stats ", notes=("Statistics scannes mutants and humans"))
    public ResponseEntity<Stats> getStats(){
        int mutants = repository.countPersons(true);
        int humans = repository.countPersons(false);
        double ratio = 0;
        try {
             ratio = mutants / humans;
        }catch (ArithmeticException ae){
            logger.log(Level.INFO, "Error msj" ,ae);
        }

        return  new ResponseEntity<>(new Stats(mutants,humans,ratio), HttpStatus.OK);


    }

}
