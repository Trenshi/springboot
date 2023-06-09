package br.senac.sp.projetoInicial.Faculdade.resources;

import br.senac.sp.projetoInicial.Faculdade.entities.Aluno;
import br.senac.sp.projetoInicial.Faculdade.services.FaculdadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.security.Provider.Service;
import java.util.List;

@RestController
@RequestMapping(value = "/faculdade")
public class FaculdadeResource {
    @Autowired
    private FaculdadeService faculdadeService;

    @GetMapping(value="/{ra}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer ra){
        Aluno aluno = faculdadeService.findById(ra);
        return ResponseEntity.ok().body(aluno);
    }
    @GetMapping(value="/abertos")
    public ResponseEntity<List<Aluno>> listarAbertos(){
        List<Aluno> alunos =
                faculdadeService.listarTodosAbertos();
        return ResponseEntity.ok().body(alunos);
    }
    @GetMapping(value="/fechados")
    public ResponseEntity<List<Aluno>> listarFechados(){
        List<Aluno> alunos =
                faculdadeService.listarTodosFechados();
        return ResponseEntity.ok().body(alunos);
    }
    @GetMapping
    public List<Aluno> findAll(){
        List<Aluno> aluno = faculdadeService.findAll();
        return aluno;
    }
    @GetMapping(value="/nome/{nome}")
    public ResponseEntity<Aluno> findByNome(@PathVariable String nome){
        Aluno aluno = faculdadeService.findByNome(nome);
        return ResponseEntity.ok().body(aluno);
    }
    @PostMapping//(value="/gravar")
    public ResponseEntity<Aluno> gravarAluno(
            @RequestBody Aluno aluno){
        aluno = faculdadeService.gravarAluno(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ra}").
                buildAndExpand(aluno.getRa()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }
    @DeleteMapping(value={"/{ra}"})
    public ResponseEntity<Void> deletar(@PathVariable Integer ra) {
        faculdadeService.deletar(ra);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value={"/{ra}"})
    public ResponseEntity<Aluno> update(@PathVariable Integer ra, @RequestBody Aluno aluno) {
        Aluno alterado = faculdadeService.update(ra, aluno);
        return ResponseEntity.ok().body(alterado);
    }
}
