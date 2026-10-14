package com.spring.mastersys.controller;

import com.spring.mastersys.projection.AlunosPorCidadeProjection;
import com.spring.mastersys.projection.FaturamentoMensalProjection;
import com.spring.mastersys.projection.FaturasEmAbertoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioAcademiaController {

    @Autowired
    private  RelatorioAcademiaController relatorioAcademiaController;

//    public RelatorioAcademiaController(RelatorioAcademiaController repository){
//        this.relatorioAcademiaController = repository;
//    }

    @GetMapping("/faturamento-mensal")
    public List<FaturamentoMensalProjection> faturamentoMensal(){
        return  relatorioAcademiaController.faturamentoMensal();
    }

    @GetMapping("/alunos-cidades")
    public List<AlunosPorCidadeProjection> alunosPorCidade(){
        return relatorioAcademiaController.alunosPorCidade();
    }

    @GetMapping("/faturas-aberto")
    public List<FaturasEmAbertoProjection> faturasEmAberto(){
        return relatorioAcademiaController.faturasEmAberto();
    }

}
