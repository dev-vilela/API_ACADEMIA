package com.spring.mastersys.service;

import com.spring.mastersys.domain.Aluno;
import com.spring.mastersys.dto.AlunoFiltroRequest;
import com.spring.mastersys.dto.AlunoRequest;
import com.spring.mastersys.dto.AlunoResponse;
import com.spring.mastersys.exception.RegraNegocioException;
import com.spring.mastersys.repository.AlunoRepository;
import com.spring.mastersys.specigication.AlunoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    public  final AlunoRepository alunoRepository;

    public  AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrar(AlunoRequest request){
        if (request.email() != null && alunoRepository.existsByEmail(request.email())){
            throw  new RegraNegocioException("Já existe um aluno cadastrado com esse email!");
        }

        Aluno aluno = request.toEntity();
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return  AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(AlunoFiltroRequest filtro, Pageable pageable){
        return  alunoRepository.findAll(AlunoSpecification.comFiltros(filtro), pageable).map(AlunoResponse::fromEntity);
    }

    public  AlunoResponse buscarPorId(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest request){
        Aluno aluno = buscarEntidadePorId(id);
        request.preecher(aluno);
        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return  AlunoResponse.fromEntity(alunoAtualizado);
    }

    public  void  excluir(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        alunoRepository.delete(aluno);
    }

    private Aluno buscarEntidadePorId(Long id){
        return alunoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Aluno não encontrado!"));
    }

}
