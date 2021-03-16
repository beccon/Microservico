package com.beccon.pessoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beccon.pessoa.data.vo.PessoaVo;
import com.beccon.pessoa.service.PessoaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private final PessoaService pessoaService;
	private final PagedResourcesAssembler<PessoaVo> assembler;
	
	@Autowired
	public PessoaController(PessoaService pessoaService, PagedResourcesAssembler<PessoaVo> assembler) {
		this.pessoaService = pessoaService;
		this.assembler = assembler;
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml", "application/x-yaml"})
	public PessoaVo findById(@PathVariable("id") Long id) {
		PessoaVo pessoaVo = pessoaService.findById(id);
		
		pessoaVo.add(linkTo(methodOn(PessoaController.class).findById(id)).withSelfRel());
		return pessoaVo;
	}

	@GetMapping(produces={"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		
		Page<PessoaVo> pessoas = pessoaService.findAll(pageable);
		pessoas.stream()
			.forEach(p -> p.add(linkTo(methodOn(PessoaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<PessoaVo>> pagedModel = assembler.toModel(pessoas);
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(produces={"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PessoaVo create(@RequestBody PessoaVo pessoaVo) {
		PessoaVo proVo = pessoaService.create(pessoaVo);
		proVo.add(linkTo(methodOn(PessoaController.class).findById(proVo.getId())).withSelfRel());		
		return proVo;
	}

	@PutMapping(produces={"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PessoaVo update(@RequestBody PessoaVo pessoaVo) {
		PessoaVo proVo = pessoaService.update(pessoaVo);
		proVo.add(linkTo(methodOn(PessoaController.class).findById(pessoaVo.getId())).withSelfRel());		
		return proVo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		pessoaService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
 