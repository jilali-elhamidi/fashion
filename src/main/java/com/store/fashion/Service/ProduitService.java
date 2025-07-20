package com.store.fashion.Service;

import com.store.fashion.Dao.ProduitRepository;
import com.store.fashion.Entity.Produit;
import com.store.fashion.dto.ProduitDTO;
import com.store.fashion.mapper.ProduitMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    public ProduitService(ProduitRepository produitRepository, ProduitMapper produitMapper) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
    }

    // Récupérer tous les produits sous forme de DTO
    public List<ProduitDTO> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return produits.stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Ajouter un produit à partir d'un DTO
    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Produit produit = produitMapper.toEntity(produitDTO);
        Produit saved = produitRepository.save(produit);
        return produitMapper.toDTO(saved);
    }

    // Tu peux aussi ajouter : getById, update, delete etc. en DTO

}
