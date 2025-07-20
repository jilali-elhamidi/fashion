package com.store.fashion.mapper;

import com.store.fashion.Entity.Produit;
import com.store.fashion.dto.ProduitDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    ProduitDTO toDTO(Produit produit);
    Produit toEntity(ProduitDTO dto);
}
