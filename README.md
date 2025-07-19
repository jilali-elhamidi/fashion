
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

### 1. Créer une base PostgreSQL :

```sql
CREATE DATABASE fashion_db;
```

### 2. Configurer `application.properties` dans `src/main/resources/` :

```properties
spring.application.name=fashion
spring.datasource.url=jdbc:postgresql://localhost:5432/fashion_db
spring.datasource.username=postgres
spring.datasource.password=ton_mot_de_passe

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Remplace `ton_mot_de_passe` par le mot de passe réel de ton utilisateur PostgreSQL.

---

### 3. Placer le fichier `fashion.csv`

Place le fichier dans le dossier suivant :

```
src/main/resources/fashion.csv
```

Il doit contenir des colonnes comme :

```csv
productId,gender,category,subcategory,articleType,baseColor,usage,productTitle,image,imageUrl
1001,Men,Topwear,Tshirts,Round Neck,Blue,Casual,Cool Blue Tee,1001.jpg,https://example.com/images/1001.jpg
```

---

### 4. 🚀 Démarrer l’application

#### Depuis l’IDE :
Lancer la classe `FashionApplication.java`

#### Depuis le terminal :

```bash
./mvnw spring-boot:run
```

---

✅ Tu peux maintenant appeler l’API à l’adresse :

```
GET http://localhost:8080/api/produits
```

---

## 📁 Structure du projet

```bash
fashion-store-api/
├── src/
│   ├── main/
│   │   ├── java/com/store/fashion/
│   │   │   ├── controller/       # Contrôleurs REST (ex. ProduitController.java)
│   │   │   ├── service/          # Logique métier (ex. CSVService.java)
│   │   │   ├── entity/           # Entités JPA (ex. Produit.java)
│   │   │   └── dao/              # Interfaces JpaRepository (ex. ProduitRepository.java)
│   │   └── resources/
│   │       ├── application.properties
│   │       └── fashion.csv
├── pom.xml
```

---

💬 N’hésite pas à cloner, tester et améliorer !
