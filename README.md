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
│   ├── fashion.ipynb               # Notebook Jupyter pour l'exploration et le développement du modèle
│   ├── reco_api.py                 # Le point d'entrée de votre API FastAPI
│   ├── requirements.txt            # Liste des dépendances Python
│   └── ... (vos modèles ML si sauvegardés localement, autres scripts, etc.)
```

#### b. Installation des dépendances Python

Il est **fortement recommandé** d'utiliser un [environnement virtuel Python](https://docs.python.org/3/library/venv.html) pour isoler les dépendances de votre projet et éviter les conflits avec d'autres projets Python sur votre système.

1.  **Naviguez** dans le dossier `fastapi-recommendation-service/` dans votre terminal :
    ```bash
    cd fastapi-recommendation-service/
    ```

2.  **Créez un environnement virtuel** (si vous n'en avez pas déjà un) :
    ```bash
    python -m venv venv
    ```
    Ceci créera un dossier `venv/` (ou `.venv/`) contenant un environnement Python isolé.

3.  **Activez l'environnement virtuel :**
    * **Sur Windows (CMD) :**
        ```bash
        venv\Scripts\activate.bat
        ```
    * **Sur Windows (PowerShell) :**
        ```powershell
        .\venv\Scripts\Activate.ps1
        ```
    * **Sur Linux/macOS ou Git Bash :**
        ```bash
        source venv/bin/activate
        ```
    Une fois activé, votre invite de commande devrait afficher `(venv)` au début, indiquant que vous êtes dans l'environnement isolé.

4.  **Installez les dépendances :**
    Assurez-vous d'avoir un fichier `requirements.txt` dans ce dossier avec le contenu suivant :
    ```
    fastapi
    uvicorn[standard]
    Pillow
    scikit-learn
    numpy
    tensorflow
    tensorflow-hub
    tqdm
    matplotlib
    scikit-image
    ```
    Puis installez-les :
    ```bash
    pip install -r requirements.txt
    ```
    Ceci installera toutes les bibliothèques nécessaires dans votre environnement virtuel.

#### c. Démarrer le service FastAPI

Une fois les dépendances installées et l'environnement virtuel activé :

1.  **Assurez-vous d'être toujours dans le dossier `fastapi-recommendation-service/`**.
2.  **Lancez l'application Uvicorn :**
    ```bash
    uvicorn reco_api:app --host 0.0.0.0 --port 8000
    ```
    > **Note :** Ici, `reco_api:app` indique que votre application FastAPI est définie dans le fichier `reco_api.py` et que l'instance de l'application s'appelle `app` (ex: `app = FastAPI()`). Le port `8000` doit correspondre à `fastapi.url` configuré dans votre `application.properties`.

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
    *Exemple avec **Postman** ou **Insomnia** (Recommandé pour les tests manuels)* :
    1.  Ouvrez Postman ou Insomnia.
    2.  Créez une nouvelle requête `POST`.
    3.  Définissez l'URL : `http://localhost:8080/api/produits/get-recommendations`
    4.  Allez dans l'onglet `Body` (Corps de la requête).
    5.  Sélectionnez l'option `form-data`.
    6.  Ajoutez une nouvelle clé :
        * `KEY` : `image`
        * `VALUE` : Changez le type de `Text` à `File` et sélectionnez un fichier image depuis votre ordinateur.
    7.  Cliquez sur `Send` (Envoyer).

---

## 📁 Structure du projet

```bash
fashion-store-api/
├── .git/                                   # Dépôt Git pour l'application Spring Boot
├── fastapi-recommendation-service/         # Service de recommandation FastAPI (Dépôt Git SEPARE)
│   ├── .git/                               # Son propre dépôt Git
│   ├── reco_api.py                 # Le code principal de l'API FastAPI
│   ├── requirements.txt            # Liste des dépendances Python
│   ├── models/                             # Vos modèles ML
│   ├── .gitignore                          # Fichier .gitignore spécifique à FastAPI
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

## 📊 Notebook d'exploration (`Fashion.ipynb`)

Le fichier `Fashion.ipynb` est un Jupyter Notebook qui a été utilisé pour l'exploration initiale des données, le test du modèle de caractéristiques d'images, et la validation de la logique de similarité.

Il contient les étapes pour :
-   Charger les données `fashion.csv`.
-   Charger et utiliser le modèle `google/experts/bit/r50x1/in21k/consumer_goods/1` de TensorFlow Hub.
-   Extraire les caractéristiques des images.
-   Calculer la similarité cosinus.
-   Visualiser les résultats de recommandation.

Ce notebook est une excellente ressource pour comprendre le fonctionnement sous-jacent du modèle de recommandation.

---

💬 N’hésitez pas à cloner, tester et améliorer !
