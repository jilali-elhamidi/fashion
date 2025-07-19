package com.store.fashion.Service;

import com.store.fashion.Dao.ProduitRepository;
import com.store.fashion.Entity.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Tu peux ajouter d'autres méthodes (filtrage, recherche...) ici
}