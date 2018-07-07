package br.com.faltoupontoevirgula.projetospring.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.faltoupontoevirgula.projetospring.model.Paciente;
import br.com.faltoupontoevirgula.projetospring.repository.PacienteRepository;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteControllerAPI {
    @Autowired
    private PacienteRepository pacienteRepository;
   
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> lista = pacienteRepository.findAll();
        return new ResponseEntity<List<Paciente>>(lista,HttpStatus.OK);
    }
   
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Paciente paciente){
        pacienteRepository.save(paciente);
        return ResponseEntity.ok().build();
    }
   
    @PutMapping(path="/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Paciente newPaciente){
        Optional<Paciente> talvezPaciente = pacienteRepository.findById(id);
        if (!talvezPaciente.isPresent())
            return ResponseEntity.notFound().build();
       
        Paciente oldPaciente = talvezPaciente.get();
       
        oldPaciente.setNome(newPaciente.getNome());
        oldPaciente.setSexo(newPaciente.getSexo());
       
        pacienteRepository.save(oldPaciente);
       
        return ResponseEntity.ok().build();
    }
   
    @DeleteMapping(path="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<Paciente> talvezPaciente = pacienteRepository.findById(id);
        if (!talvezPaciente.isPresent())
            return ResponseEntity.notFound().build();
       
        pacienteRepository.delete(talvezPaciente.get());
       
        return ResponseEntity.ok().build();
    }
}