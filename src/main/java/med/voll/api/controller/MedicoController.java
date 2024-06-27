package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired //Injeção de dependencias
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public void cadastar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));

    }

    //  @GetMapping Antes de alterar.
    //  public List<DadosListagemMedico> listar(Pageable paginacao){
    //      return repository.findAll(paginacao).stream().map(DadosListagemMedico::new).toList();}

    //Alteração que permite a paginação e ordenação
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @Transactional
    @PutMapping
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    //    @Transactional
    //    @DeleteMapping("/{id}")
    //    public void excluir(@PathVariable Long id){
    //      repository.deleteById(id);} // Exclusão Física - Não recomendado

    @Transactional
    @DeleteMapping("/{id}")
    // Exclusão Lógica
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }


}
