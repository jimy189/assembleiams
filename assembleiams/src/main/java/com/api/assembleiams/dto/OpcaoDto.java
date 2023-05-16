package com.api.assembleiams.dto;

import com.api.assembleiams.enums.StatusVoto;
import com.api.assembleiams.models.PautaModel;
import com.api.assembleiams.models.SessaoModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class VotarDto {


    private Integer idOpcao;
    private StatusVoto statusVoto;
 // private String cpf;
    private Integer idSessao;
    private Integer idPauta;
}
