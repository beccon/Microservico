package com.beccon.conta.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.beccon.conta.data.vo.ContaVo;
import com.beccon.conta.service.ContaService;


@RestController
@RequestMapping("/conta")
public class ContaController {

	private final ContaService contaService;
	private final PagedResourcesAssembler<ContaVo> assembler;
	
	@Autowired
	public ContaController(ContaService contaService, PagedResourcesAssembler<ContaVo> assembler) {
		this.contaService = contaService;
		this.assembler = assembler;
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml", "application/x-yaml"})
	public ContaVo findById(@PathVariable("id") Long id) {
		ContaVo contaVo = contaService.findById(id);
		
		contaVo.add(linkTo(methodOn(ContaController.class).findById(id)).withSelfRel());
		return contaVo;
	}

	@GetMapping(produces={"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "data"));
		
		Page<ContaVo> contas = contaService.findAll(pageable);
		contas.stream()
			.forEach(p -> p.add(linkTo(methodOn(ContaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ContaVo>> pagedModel = assembler.toModel(contas);
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(produces={"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ContaVo create(@RequestBody ContaVo contaVo) {
		ContaVo proVo = contaService.create(contaVo);
		proVo.add(linkTo(methodOn(ContaController.class).findById(proVo.getId())).withSelfRel());		
		return proVo;
	}

	@PutMapping(produces={"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ContaVo update(@RequestBody ContaVo contaVo) {
		ContaVo proVo = contaService.update(contaVo);
		proVo.add(linkTo(methodOn(ContaController.class).findById(contaVo.getId())).withSelfRel());		
		return proVo;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		contaService.delete(id);
		return ResponseEntity.ok().build();
	}
}
