# 🛍️ Fashion Store API

**Fashion Store API** est une application backend développée en **Spring Boot** pour la gestion d’un catalogue de produits de mode. Les données sont extraites automatiquement d’un fichier CSV et stockées dans une base de données PostgreSQL.

---

## 📦 Fonctionnalités

- 📥 Importation automatique des produits depuis un fichier CSV (`fashion.csv`)
- 🔍 API REST pour accéder à la liste des produits
- 🗃️ Persistance des données avec Spring Data JPA + PostgreSQL
- 🖼️ Affichage des images via URL (pour intégration frontend)
- 💡 Architecture claire basée sur les bonnes pratiques Spring (Controller / Service / Repository)

---

## 🧱 Technologies utilisées

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

---

## 🛠️ Configuration

1. **Créer une base PostgreSQL** :

   ```sql
   CREATE DATABASE fashion_db;
