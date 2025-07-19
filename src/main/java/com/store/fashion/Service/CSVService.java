package com.store.fashion.Service;


import com.store.fashion.Dao.ProduitRepository;
import com.store.fashion.Entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CSVService {

    @Autowired
    private ProduitRepository produitRepository;

    public void importerDepuisCSV() {
        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(getClass().getClassLoader().getResourceAsStream("fashion.csv")))
        ) {
            String ligne = br.readLine(); // sauter l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] col = ligne.split(",");

                // ⚠ Vérifie que le tableau a au moins 9 colonnes
                if (col.length < 9) continue;

                Produit p = new Produit();
                p.setProductId(col[0]);
                p.setGender(col[1]);
                p.setCategory(col[2]);
                p.setSubcategory(col[3]);
                p.setArticleType(col[4]);
                p.setBaseColor(col[5]);
                p.setUsage(col[6]);
                p.setProductTitle(col[7]);
                p.setImage(col[8]);
                p.setImageUrl(col[9]);

                produitRepository.save(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
