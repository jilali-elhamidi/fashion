package com.store.fashion.controller;

import com.store.fashion.Service.FastAPIClientService; // AJOUTEZ CET IMPORT
import com.store.fashion.Service.ProduitService;
import com.store.fashion.dto.ProduitDTO;
import org.springframework.http.HttpStatus; // AJOUTEZ CET IMPORT
import org.springframework.http.ResponseEntity; // AJOUTEZ CET IMPORT
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // AJOUTEZ CET IMPORT
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // AJOUTEZ CET IMPORT
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile; // AJOUTEZ CET IMPORT

import java.io.IOException; // AJOUTEZ CET IMPORT
import java.util.List;

@RestController
@RequestMapping("/api/produits") // Vous pouvez ajuster ce RequestMapping si nécessaire
public class ProduitController {

    private final ProduitService produitService;
    private final FastAPIClientService fastApiClientService; // AJOUTEZ L'INJECTION DU SERVICE CLIENT FASTAPI

    // MODIFIÉ : Le constructeur doit injecter les deux services
    public ProduitController(ProduitService produitService, FastAPIClientService fastApiClientService) {
        this.produitService = produitService;
        this.fastApiClientService = fastApiClientService;
    }

    @GetMapping // Endpoint pour récupérer tous les produits (existant)
    public List<ProduitDTO> getProduits() {
        return produitService.getAllProduits();
    }

    // AJOUTEZ CET ENDPOINT POUR LA RECOMMANDATION
    @PostMapping("/get-recommendations") // Endpoint pour récupérer les recommandations via une image
    public ResponseEntity<List<ProduitDTO>> getRecommendations(@RequestParam("image") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] imageBytes = imageFile.getBytes();
            String filename = imageFile.getOriginalFilename();

            // 1. Appelle le service client FastAPI pour obtenir les IDs des produits recommandés
            List<String> recommendedProductIds = fastApiClientService.getRecommendations(imageBytes, filename);

            // 2. Utilise le ProduitService pour obtenir les détails complets (incluant l'URL de l'image) depuis PostgreSQL
            List<ProduitDTO> recommendedProducts = produitService.getProduitsByIds(recommendedProductIds);

            return new ResponseEntity<>(recommendedProducts, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace(); // Affichez l'exception pour le débogage
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}