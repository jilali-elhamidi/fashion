# 🛍️ Fashion Store API

**Fashion Store API** est une application backend complète développée en **Spring Boot** pour la gestion d’un catalogue de produits de mode et l'intégration d'un système de recommandation intelligent basé sur les images, propulsé par un service Python **FastAPI**.

---

## 🚀 Vue d'ensemble de l'Architecture

Ce projet adopte une architecture microservices simple, où l'application principale Spring Boot interagit avec un service de machine learning (ML) distinct développé en FastAPI.

-   **Spring Boot Backend :** Gère le catalogue de produits, la persistance des données et expose les API REST principales. Il agit comme un client pour le service de recommandation FastAPI.

-   **FastAPI Recommendation Service :** Une API Python légère et rapide, dédiée à la logique de recommandation de produits basée sur le traitement d'images et des modèles de Machine Learning.

---

## 📦 Fonctionnalités

### Core API (Spring Boot)

-   📥 Importation automatique des produits depuis un fichier CSV (`fashion.csv`) au démarrage.

-   🔍 API REST pour accéder, filtrer et gérer la liste des produits.

-   🗃️ Persistance des données avec Spring Data JPA + PostgreSQL.

-   🖼️ Affichage des images via URL (pour intégration frontend).

-   💡 Architecture claire basée sur les bonnes pratiques Spring (Controller / Service / Repository).

### Système de Recommandation (Spring Boot ↔️ FastAPI)

-   📸 **Recommandation par image :** Permet de soumettre une image de produit au backend Spring Boot.

-   🔗 Spring Boot transmet l'image au service FastAPI dédié au Machine Learning.

-   🧠 Le service FastAPI analyse l'image et retourne une liste d'IDs de produits pertinents.

-   🛍️ Spring Boot récupère les détails complets des produits recommandés depuis sa base de données et les renvoie au client.

---

## 🧱 Technologies utilisées

### Spring Boot Backend

-   **Java 21**

-   **Spring Boot 3.5**

-   **Spring Data JPA**

-   **PostgreSQL**

-   **Lombok**

-   **Maven**

-   **Spring WebFlux** (pour `WebClient` pour l'appel FastAPI)

### FastAPI Recommendation Service

-   **Python 3.10+**

-   **FastAPI**

-   **Uvicorn**

-   **Pillow** (pour la manipulation d'images)

-   **Bibliothèques ML :** ( `TensorFlow`,  `Scikit-learn`, `Numpy`, `Pandas`)

---

## 🛠️ Configuration et Démarrage

Pour faire fonctionner l'ensemble de l'application, vous devrez configurer et démarrer les deux services.

### 1. Créer une base PostgreSQL :

```sql
CREATE DATABASE fashion_db;
```

### 2. Configurer `application.properties` dans `src/main/resources/` :

```properties
spring.application.name=fashion
spring.datasource.url=jdbc:postgresql://localhost:5432/fashion_db
spring.datasource.username=postgres
spring.datasource.password=ton_mot_de_passe # <-- REMPLACE ton_mot_de_passe

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuration du client FastAPI (pour le service de recommandation)
# Assurez-vous que cette URL correspond à l'adresse et au port où votre service FastAPI est lancé.
fastapi.url=http://localhost:8000
```

> Remplace `ton_mot_de_passe` par le mot de passe réel de ton utilisateur PostgreSQL.

### 3. Placer le fichier `fashion.csv`

Place le fichier dans le dossier suivant :

```
src/main/resources/fashion.csv
```

Il doit contenir des colonnes comme :

```csv
productId,gender,category,subcategory,articleType,baseColor,usage,productTitle,image,imageUrl
1001,Men,Topwear,Tshirts,Round Neck,Blue,Casual,Cool Blue Tee,1001.jpg,[https://example.com/images/1001.jpg](https://example.com/images/1001.jpg)
```

### 4. Configuration et Démarrage du Service FastAPI

Le service FastAPI est une application Python distincte.

#### a. Structure du dossier FastAPI

Assurez-vous que votre service FastAPI est dans un dossier séparé, comme `fastapi-recommendation-service/` à la racine de votre projet Spring Boot.

```bash
fashion-store-api/
├── fastapi-recommendation-service/ # <-- VOTRE SERVICE FASTAPI EST ICI
│   ├── fashion.ipynb                  # une petite description comment fonctionne le model et comment integrer 
│   ├── reco_api.py                    #Le point d'entrée de votre API FastAPI
│   ├── requirements.txt
└── 
```

#### b. Installation des dépendances Python

Naviguez dans le dossier `fastapi-recommendation-service/` et installez les dépendances :

```bash
cd fastapi-recommendation-service/
pip install -r requirements.txt
```

#### c. Démarrer le service FastAPI

Assurez-vous d'être dans le dossier `fastapi-recommendation-service/` :

```bash
uvicorn main:app --host 0.0.0.0 --port 8000 
> **Note :** Le `main:app` suppose que votre fichier principal s'appelle `main.py` et que votre instance FastAPI est nommée `app`. Adaptez si vos noms sont différents. Le port `8000` doit correspondre à `fastapi.url` configuré dans votre `application.properties`.
#dans ce cas le app est reco_api.py   


### 5. 🚀 Démarrer l’application Spring Boot

Une fois que votre service FastAPI est lancé et accessible (étape 4c), vous pouvez démarrer l'application Spring Boot.

#### Depuis l’IDE :
Lancer la classe `FashionApplication.java`

#### Depuis le terminal :

```bash
# Assurez-vous d'être dans le répertoire racine de votre projet Spring Boot (fashion-store-api/)
./mvnw spring-boot:run
```

---

## 🌐 API Endpoints

Une fois les deux services démarrés :

### Endpoints Spring Boot

-   **Accéder à tous les produits :**
    ```
    GET http://localhost:8080/api/produits
    ```
-   **Obtenir des recommandations basées sur une image :**
    ```
    POST http://localhost:8080/api/produits/get-recommendations
    ```
    **Comment l'utiliser :** Envoyez une requête `multipart/form-data` avec un champ de fichier nommé `image`.
    *Exemple avec `curl` (depuis Git Bash ou Linux)* :
    ```bash
    curl -X POST \
      -F "image=@/chemin/vers/ton/image.jpg" \
      http://localhost:8080/api/produits/get-recommendations
    ```
   

---

## 📁 Structure du projet

```bash
fashion-store-api/
├── .git/                                   # Dépôt Git pour l'application Spring Boot
├── fastapi-recommendation-service/         # Service de recommandation FastAPI (Dépôt Git SEPARE)
│   ├── .git/                               # Son propre dépôt Git
│   ├── main.py                             # Code de l'API FastAPI
│   ├── requirements.txt                    # Dépendances Python
│   ├── models/                             # Vos modèles ML
│   ├── .gitignore                          # .gitignore spécifique à FastAPI
│   └── ...
├── src/
│   ├── main/
│   │   ├── java/com/store/fashion/
│   │   │   ├── controller/                 # Contrôleurs REST (ex. ProduitController.java)
│   │   │   ├── service/                    # Logique métier (ex. CSVService.java, FastApiClientService.java)
│   │   │   ├── entity/                     # Entités JPA (ex. Produit.java)
│   │   │   ├── dao/                        # Interfaces JpaRepository (ex. ProduitRepository.java)
│   │   │   └── dto/                        # Data Transfer Objects (ex. ProduitDTO.java, FastAPIRecommendationResponse.java)
│   │   └── resources/
│   │       ├── application.properties
│   │       └── fashion.csv
├── pom.xml
├── .gitignore                              # .gitignore pour l'application Spring Boot (qui ignore 'fastapi-recommendation-service/')
└── README.md
```

---

💬 N’hésitez pas à cloner, tester et améliorer !
