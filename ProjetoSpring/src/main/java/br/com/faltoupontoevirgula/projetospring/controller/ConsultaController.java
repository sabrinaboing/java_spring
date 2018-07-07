package br.com.faltoupontoevirgula.projetospring.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faltoupontoevirgula.projetospring.model.Consulta;
import br.com.faltoupontoevirgula.projetospring.model.Medico;
import br.com.faltoupontoevirgula.projetospring.model.Paciente;
import br.com.faltoupontoevirgula.projetospring.model.Procedimento;
import br.com.faltoupontoevirgula.projetospring.model.ProcedimentoRealizado;
import br.com.faltoupontoevirgula.projetospring.repository.ConsultaRepository;
import br.com.faltoupontoevirgula.projetospring.repository.MedicoRepository;
import br.com.faltoupontoevirgula.projetospring.repository.PacienteRepository;
import br.com.faltoupontoevirgula.projetospring.repository.ProcedimentoRepository;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ProcedimentoRepository procedimentoRepository;
    
    @GetMapping("")
    public ModelAndView index() {
        List<Consulta> listaConsulta = this.consultaRepository.findAll();
        
        return new ModelAndView("consulta/index","listaConsulta",listaConsulta);
    }
    
    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Consulta consulta) {
        List<Medico> listaMedico = this.medicoRepository.findAll();
        List<Paciente> listaPaciente = this.pacienteRepository.findAll();
        List<Procedimento> listaProcedimento = this.procedimentoRepository.findAll();
        
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("listaMedico", listaMedico);
        dados.put("listaPaciente", listaPaciente);
        dados.put("listaProcedimento", listaProcedimento);
        dados.put("novoprocrealizado", new ProcedimentoRealizado());
        
        return new ModelAndView("consulta/form",dados);
    }
    
    @PostMapping(params= {"save"})
    public ModelAndView save(@Valid Consulta consulta, @Valid ProcedimentoRealizado novoprocrealizado, BindingResult result, RedirectAttributes redirect) {
        consulta = this.consultaRepository.save(consulta);
        return new ModelAndView("redirect:/consulta");
    }
    @PostMapping(params= {"insertproc"})
    public ModelAndView insertproc(Consulta consulta, ProcedimentoRealizado novoprocrealizado, BindingResult result, RedirectAttributes redirect) {
        List<Medico> listaMedico = this.medicoRepository.findAll();
        List<Paciente> listaPaciente = this.pacienteRepository.findAll();
        List<Procedimento> listaProcedimento = this.procedimentoRepository.findAll();
        
        consulta.getListaProcedimentos().add(novoprocrealizado);
    
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("consulta", consulta);
        dados.put("listaMedico", listaMedico);
        dados.put("listaPaciente", listaPaciente);
        dados.put("listaProcedimento", listaProcedimento);
        dados.put("novoprocrealizado", new ProcedimentoRealizado());
        
        
        return new ModelAndView("consulta/form",dados);
    }
    
    @PostMapping(params= {"removeproc"})
    public ModelAndView removeproc(@RequestParam(name = "removeproc") int index, Consulta consulta, ProcedimentoRealizado novoprocrealizado, BindingResult result, RedirectAttributes redirect) {
        List<Medico> listaMedico = this.medicoRepository.findAll();
        List<Paciente> listaPaciente = this.pacienteRepository.findAll();
        List<Procedimento> listaProcedimento = this.procedimentoRepository.findAll();
        
        consulta.getListaProcedimentos().remove(index);
    
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("consulta", consulta);
        dados.put("listaMedico", listaMedico);
        dados.put("listaPaciente", listaPaciente);
        dados.put("listaProcedimento", listaProcedimento);
        dados.put("novoprocrealizado", new ProcedimentoRealizado());
        
        
        return new ModelAndView("consulta/form",dados);
    }
    
    @GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Consulta consulta) {
        List<Medico> listaMedico = this.medicoRepository.findAll();
        List<Paciente> listaPaciente = this.pacienteRepository.findAll();
        List<Procedimento> listaProcedimento = this.procedimentoRepository.findAll();
        
        HashMap<String, Object> dados = new HashMap<String, Object>();
        dados.put("consulta", consulta);
        dados.put("listaMedico", listaMedico);
        dados.put("listaPaciente", listaPaciente);
        dados.put("listaProcedimento", listaProcedimento);
        dados.put("novoprocrealizado", new ProcedimentoRealizado());
        
        return new ModelAndView("consulta/form",dados);
    }
    
    
    
    
    @GetMapping(value="remover/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.consultaRepository.deleteById(id);
        return new ModelAndView("redirect:/consulta");
    }
    
    
}