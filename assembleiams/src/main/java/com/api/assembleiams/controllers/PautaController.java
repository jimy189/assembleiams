package com.api.assembleiams.controllers;

import com.api.assembleiams.dto.PautaDto;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.services.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pauta-ms")
public class AssembleiaController {

    final PautaService pautaService;

    public AssembleiaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }
    @PostMapping
    public ResponseEntity<Object> savePauta(@RequestBody @Valid PautaDto pautaDto){

        var pautaModel = new PautaModel();
        BeanUtils.copyProperties(pautaDto, pautaModel);
        pautaDto.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.save(pautaModel));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PautaModel>> getAllPauta(){
        List<PautaModel> pautaList =
                pautaService.findAll();
        if(pautaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<PautaModel>>(pautaList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaModel> getOnePauta(@PathVariable(value = "id") Integer id){
        Optional<PautaModel> parkingSpotModelOptional = pautaService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<PautaModel>(parkingSpotModelOptional.get(), HttpStatus.OK);
    }
}
