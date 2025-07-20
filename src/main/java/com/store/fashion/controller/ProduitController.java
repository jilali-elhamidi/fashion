package com.store.fashion.controller;

import com.store.fashion.Service.ProduitService;
import com.store.fashion.dto.ProduitDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<ProduitDTO> getProduits() {
        return produitService.getAllProduits();
    }
}
