package com.store.fashion.Service;

import com.store.fashion.Dao.ProduitRepository;
import com.store.fashion.Entity.Produit;
import com.store.fashion.dto.ProduitDTO;
import com.store.fashion.mapper.ProduitMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public ProduitDTO getProduitDetailsById(String productId) {
        Optional<Produit> produitOptional = produitRepository.findById(productId); // Utilisez findById ou une méthode personnalisée si l'ID est dans un autre champ
        return produitOptional.map(produitMapper::toDTO).orElse(null); // Convertir l'entité en DTO
    }

    // Méthode pour obtenir plusieurs produits par leurs IDs
    public List<ProduitDTO> getProduitsByIds(List<String> productIds) {
        List<Produit> produits = produitRepository.findAllById(productIds); // Méthode de JpaRepository
        return produits.stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Tu peux aussi ajouter : getById, update, delete etc. en DTO

}
