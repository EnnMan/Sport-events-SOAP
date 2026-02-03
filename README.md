# SportEventsSOAP 🏆

## 📌 Description du projet

Ce projet est une **application web SOAP** pour la gestion d'événements sportifs. Il permet de gérer des **équipes**, des **stades** et des **matchs** via des services web SOAP.

**Technologies principales :** Jakarta EE, JPA (Hibernate), MySQL, JAX-WS, Maven, Tomcat 11

### Objectifs pédagogiques

Ce projet académique a pour but de :

- Comprendre l'architecture en couches (SOAP → Service → DAO → Database)
- Maîtriser JPA et Hibernate pour la persistance des données
- Créer et exposer des services web SOAP avec JAX-WS
- Déployer une application web sur Tomcat
- Manipuler Maven pour la gestion des dépendances

---

## 🛠️ Technologies utilisées

| Catégorie           | Technologie     | Version | Utilisation                           |
| ------------------- | --------------- | ------- | ------------------------------------- |
| **Langage**         | Java SE         | 21      | Langage de programmation              |
| **Framework**       | Jakarta EE      | 10.0    | Plateforme pour services web          |
| **Services Web**    | JAX-WS          | 4.0     | Framework SOAP                        |
| **Persistance**     | JPA / Hibernate | 6.4     | ORM (Object-Relational Mapping)       |
| **Base de données** | MySQL           | 8.x     | Système de gestion de base de données |
| **Build**           | Maven           | 3.x     | Gestion des dépendances et build      |
| **Serveur**         | Apache Tomcat   | 11.x    | Serveur d'applications web            |

---

## 🏗️ Architecture du projet

Le projet suit une **architecture en couches** claire et simple :

```
┌─────────────────────────────────┐
│     Client SOAP                 │  (Postman, SoapUI, Application cliente)
│  (Envoie des requêtes XML)      │
└────────────┬────────────────────┘
             │
             ↓  HTTP/SOAP
┌─────────────────────────────────┐
│       SOAP Endpoints            │  @WebService (TeamSOAP, StadiumSOAP, MatchSOAP)
│       (soap/)                   │  → Reçoit les requêtes SOAP
└────────────┬────────────────────┘
             │
             ↓
┌─────────────────────────────────┐
│       Services métier           │  (TeamService, StadiumService, MatchService)
│       (service/)                │  → Logique métier, validation
└────────────┬────────────────────┘
             │
             ↓
┌─────────────────────────────────┐
│       DAO (Data Access)         │  (TeamDAO, StadiumDAO, MatchDAO)
│       (dao/)                    │  → Accès aux données via JPA
└────────────┬────────────────────┘
             │
             ↓  JPA/Hibernate
┌─────────────────────────────────┐
│       Base de données MySQL     │  Tables: teams, stadiums, matches
│                                 │
└─────────────────────────────────┘
```

**Avantages de cette architecture :**

- ✅ Séparation claire des responsabilités
- ✅ Facilite les tests unitaires
- ✅ Code maintenable et évolutif
- ✅ Respecte les principes SOLID

---

## 📂 Structure du projet

```
SportEventsSoap/
├── .github/                         # 🔄 Configuration GitHub
│   └── workflows/
│       └── ci.yml                   # Pipeline CI/CD (build automatique)
│
├── src/
│   ├── main/
│   │   ├── java/com/tripleMen/sportevents/
│   │   │   ├── entity/              # 🗂️ Entités JPA
│   │   │   │   ├── Team.java
│   │   │   │   ├── Stadium.java
│   │   │   │   └── Match.java
│   │   │   │
│   │   │   ├── dao/                 # 💾 Accès aux données
│   │   │   │   ├── interfaces/      # Contrats DAO
│   │   │   │   │   ├── TeamDAO.java
│   │   │   │   │   ├── StadiumDAO.java
│   │   │   │   │   └── MatchDAO.java
│   │   │   │   └── impl/            # Implémentations DAO
│   │   │   │       ├── TeamDAOImpl.java
│   │   │   │       ├── StadiumDAOImpl.java
│   │   │   │       └── MatchDAOImpl.java
│   │   │   │
│   │   │   ├── service/             # 🎯 Logique métier
│   │   │   │   ├── TeamService.java
│   │   │   │   ├── StadiumService.java
│   │   │   │   └── MatchService.java
│   │   │   │
│   │   │   ├── soap/                # 🌐 Endpoints SOAP
│   │   │   │   ├── TeamSOAP.java
│   │   │   │   ├── StadiumSOAP.java
│   │   │   │   └── MatchSOAP.java
│   │   │   │
│   │   │   └── util/                # 🔧 Utilitaires
│   │   │       └── JPAUtil.java
│   │   │
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml  # Configuration JPA
│   │   │
│   │   └── webapp/
│   │       ├── index.jsp
│   │       └── WEB-INF/
│   │           ├── web.xml          # Configuration Servlet
│   │           └── sun-jaxws.xml    # Configuration JAX-WS
│   │
│   └── test/                        # Tests unitaires 
│
├── .gitignore                       # Fichiers ignorés par Git
├── pom.xml                          # Configuration Maven
└── README.md                        # Documentation du projet
```

---

## 🧩 Explication des couches

### 📦 1. **entity/** - Entités JPA

Les entités représentent les tables de la base de données sous forme de classes Java.

**Entités du projet :**

| Entité      | Description     | Attributs principaux                                             |
| ----------- | --------------- |------------------------------------------------------------------|
| **Team**    | Équipe sportive | id, name, country, foundedYear                                   |
| **Stadium** | Stade           | id, name, city, capacity                                         |
| **Match**   | Match sportif   | id, homeTeam, awayTeam, stadium, matchDate, homeScore, awayScore |

**Annotations JPA utilisées :**

- `@Entity` : Déclare une classe comme entité JPA
- `@Table(name = "...")` : Spécifie le nom de la table
- `@Id` : Clé primaire
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` : Auto-incrémentation
- `@Column` : Configuration des colonnes
- `@ManyToOne` : Relation plusieurs-à-un

### 📦 2. **dao/** - Data Access Object

Les DAO gèrent l'accès aux données via JPA/Hibernate.

**Structure :**

- **`interfaces/`** : Définit les contrats (méthodes CRUD)
- **`impl/`** : Implémente la logique d'accès aux données

**Méthodes CRUD typiques :**

```java
- findAll()         // Récupérer toutes les entités
- findById(Long id) // Récupérer par ID
- save(Entity e)    // Créer/Enregistrer
- update(Entity e)  // Mettre à jour
- delete(Long id)   // Supprimer
```

### 📦 3. **service/** - Services métier

Les services contiennent la logique métier de l'application.

**Rôle :**

- Valider les données avant insertion
- Appliquer les règles métier
- Orchestrer les appels DAO
- Gérer les transactions

### 📦 4. **soap/** - Endpoints SOAP

Les endpoints exposent les services web SOAP.

**Annotations JAX-WS :**

- `@WebService(serviceName = "...")` : Déclare un service SOAP
- `@WebMethod` : Expose une méthode comme opération SOAP

**Exemple de méthode SOAP :**

```java
@WebMethod
public Team createTeam(String name, String country, Integer foundedYear) {
    // Logique pour créer une équipe
}
```

### 📦 5. **util/** - Utilitaires

**JPAUtil.java** : Classe utilitaire pour gérer l'EntityManager.

```java
- getEntityManager() : Fournit un EntityManager
- close()            : Ferme l'EntityManagerFactory
```

### 📦 6. **.github/workflows/** - Intégration Continue (Optionnel)

**ci.yml** : Configuration GitHub Actions pour automatiser le build du projet.

**Fonctionnalités :**
- Compile automatiquement le projet à chaque push
- Vérifie que `mvn clean package` fonctionne
- Utile pour valider que le code est fonctionnel avant de merger

**Note :** Cette fonctionnalité est optionnelle et n'est pas requise pour le fonctionnement du projet. Elle démontre une bonne pratique de développement en équipe.

---

## 🗄️ Base de données

### Structure des tables

La base de données **SportEventSoap** contient 3 tables :

#### Table `teams`

| Colonne      | Type                        | Description                 |
| ------------ | --------------------------- | --------------------------- |
| id           | BIGINT (PK, AUTO_INCREMENT) | Identifiant unique          |
| name         | VARCHAR(100)                | Nom de l'équipe             |
| country      | VARCHAR(50)                 | Pays                        |
| founded_year | INT                         | Année de fondation          |

#### Table `stadiums`

| Colonne  | Type                        | Description        |
| -------- | --------------------------- | ------------------ |
| id       | BIGINT (PK, AUTO_INCREMENT) | Identifiant unique |
| name     | VARCHAR(100)                | Nom du stade       |
| city     | VARCHAR(100)                | Ville              |
| capacity | INT                         | Capacité           |

#### Table `matches`

| Colonne      | Type                        | Description             |
| ------------ | --------------------------- | ----------------------- |
| id           | BIGINT (PK, AUTO_INCREMENT) | Identifiant unique      |
| home_team_id | BIGINT (FK)                 | Équipe à domicile       |
| away_team_id | BIGINT (FK)                 | Équipe extérieure       |
| stadium_id   | BIGINT (FK)                 | Stade du match          |
| match_date   | DATETIME                    | Date et heure du match  |
| home_score   | INT                         | Score équipe domicile   |
| away_score   | INT                         | Score équipe extérieure |

**Note :** Les tables sont créées automatiquement par Hibernate grâce à la propriété `hibernate.hbm2ddl.auto=update` dans `persistence.xml`.

---

## ⚙️ Installation et exécution

### Prérequis

Avant de commencer, assurez-vous d'avoir :

- ☕ **Java JDK 21** ou supérieur
- 📦 **Maven 3.x**
- 🐬 **MySQL 8.x** (ou MariaDB)
- 🐱 **Apache Tomcat 11.x**
- 💻 Un IDE Java (IntelliJ IDEA, Eclipse, NetBeans, VS Code)

### Étapes d'installation

#### 1️⃣ Cloner le projet

```bash
git clone https://github.com/EnnMan/Sport-events-SOAP.git
cd Sport-events-SOAP
```

#### 2️⃣ Créer la base de données MySQL

Ouvrez MySQL et exécutez :

```sql
CREATE DATABASE SportEventSoap CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### 3️⃣ Configurer la connexion à la base de données

Modifiez le fichier `src/main/resources/META-INF/persistence.xml` :

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/SportEventSoap"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="votre_mot_de_passe"/>
```

#### 4️⃣ Importer le projet dans votre IDE

**Pour IntelliJ IDEA :**

1. `File` → `Open`
2. Sélectionnez le dossier du projet
3. IntelliJ détecte automatiquement le projet Maven

**Pour Eclipse :**

1. `File` → `Import...`
2. `Maven` → `Existing Maven Projects`
3. Sélectionnez le dossier du projet

**Pour tous les IDE :**
Maven téléchargera automatiquement toutes les dépendances.

#### 5️⃣ Compiler le projet

Dans un terminal :

```bash
mvn clean package
```

Cela génère `target/SportEventsSoap.war`.

#### 6️⃣ Déployer sur Tomcat

**Option A - Depuis l'IDE :**

- Configurez un serveur Tomcat local dans votre IDE
- Déployez l'artifact `SportEventsSoap:war exploded`

**Option B - Manuellement :**

1. Copiez `target/SportEventsSoap.war` dans le dossier `webapps/` de Tomcat
2. Démarrez Tomcat :
   ```bash
   cd /chemin/vers/tomcat/bin
   ./catalina.sh run      # Linux/Mac
   catalina.bat run       # Windows
   ```

#### 7️⃣ Vérifier le déploiement

Ouvrez un navigateur et visitez :

```
http://localhost:8080/SportEventsSoap/services/team?wsdl
```

✅ Si vous voyez du XML (le WSDL), votre service SOAP est bien déployé !

---

## 🔄 Comment fonctionne l'application

### Flux d'une requête SOAP (exemple : créer une équipe)

```
1️⃣ Client (Postman) envoie une requête SOAP XML
         ↓
2️⃣ Tomcat reçoit la requête sur http://localhost:8080/SportEventsSoap/services/team
         ↓
3️⃣ JAX-WS (WSServlet) route vers TeamSOAP.createTeam()
         ↓
4️⃣ TeamSOAP appelle TeamService.createTeam(...)
         ↓
5️⃣ TeamService valide les données et appelle TeamDAO.save(team)
         ↓
6️⃣ TeamDAO utilise EntityManager pour persister en base
         ↓
7️⃣ Hibernate génère et exécute INSERT INTO teams (...)
         ↓
8️⃣ MySQL enregistre les données
         ↓
9️⃣ La réponse remonte en XML jusqu'au client
```

### Opérations CRUD disponibles

Pour chaque entité (Team, Stadium, Match), les opérations suivantes sont prévues :

| Opération    | Description                   | Méthode SOAP                                                |
| ------------ | ----------------------------- | ----------------------------------------------------------- |
| **Create**   | Créer une nouvelle entité     | `createTeam(...)`, `createStadium(...)`, `createMatch(...)` |
| **Read**     | Récupérer une entité par ID   | `getTeamById(id)`, `getStadiumById(id)`, `getMatchById(id)` |
| **Read All** | Récupérer toutes les entités  | `getAllTeams()`, `getAllStadiums()`, `getAllMatches()`      |
| **Update**   | Modifier une entité existante | `updateTeam(...)`, `updateStadium(...)`, `updateMatch(...)` |
| **Delete**   | Supprimer une entité          | `deleteTeam(id)`, `deleteStadium(id)`, `deleteMatch(id)`    |

---

## 📋 URLs des services SOAP

Une fois l'application déployée, les services SOAP sont accessibles aux URLs suivantes :

| Service            | Description         | WSDL                                                                                                                       | Endpoint            |
| ------------------ | ------------------- | -------------------------------------------------------------------------------------------------------------------------- | ------------------- |
| **TeamService**    | Gestion des équipes | [http://localhost:8080/SportEventsSoap/services/team?wsdl](http://localhost:8080/SportEventsSoap/services/team?wsdl)       | `/services/team`    |
| **StadiumService** | Gestion des stades  | [http://localhost:8080/SportEventsSoap/services/stadium?wsdl](http://localhost:8080/SportEventsSoap/services/stadium?wsdl) | `/services/stadium` |
| **MatchService**   | Gestion des matchs  | [http://localhost:8080/SportEventsSoap/services/match?wsdl](http://localhost:8080/SportEventsSoap/services/match?wsdl)     | `/services/match`   |

**Qu'est-ce qu'un WSDL ?**  
Le WSDL (Web Services Description Language) est un document XML qui décrit le service SOAP : ses méthodes, ses paramètres, et comment l'appeler.

---

## 🧪 Tester les services SOAP

### Avec Postman

1. **Créez une nouvelle requête :**
   - Type : **POST**
   - URL : `http://localhost:8080/SportEventsSoap/services/team`

2. **Configurez les headers :**
   - `Content-Type: text/xml`

3. **Corps de la requête (Body - raw - XML) :**

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:soap="http://soap.sportevents.tripleMen.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:createTeam>
         <name>FC Barcelona</name>
         <country>Spain</country>
         <foundedYear>1899</foundedYear>
      </soap:createTeam>
   </soapenv:Body>
</soapenv:Envelope>
```

4. **Cliquez sur "Send"**

**Réponse attendue :**

```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ns2:createTeamResponse xmlns:ns2="http://soap.sportevents.tripleMen.com/">
         <return>
            <id>1</id>
            <name>FC Barcelona</name>
            <country>Spain</country>
            <foundedYear>1899</foundedYear>
         </return>
      </ns2:createTeamResponse>
   </S:Body>
</S:Envelope>
```

### Avec SoapUI

1. Créez un nouveau projet SOAP
2. WSDL : `http://localhost:8080/SportEventsSoap/services/team?wsdl`
3. SoapUI génère automatiquement toutes les requêtes possibles
4. Remplissez les valeurs et exécutez

### Avec curl (ligne de commande)

```bash
curl -X POST http://localhost:8080/SportEventsSoap/services/team \
  -H "Content-Type: text/xml" \
  -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
        <soapenv:Body>
          <createTeam xmlns="http://soap.sportevents.tripleMen.com/">
            <name>Real Madrid</name>
            <country>Spain</country>
            <foundedYear>1902</foundedYear>
          </createTeam>
        </soapenv:Body>
      </soapenv:Envelope>'
```

---

**Note importante :**  
Ce projet est conçu comme une **base solide et évolutive**. L'architecture est complète et prête à recevoir les implémentations CRUD. Les étudiants peuvent ajouter progressivement les fonctionnalités selon les exigences du cours.

---

## 👥 Équipe du projet

Ce projet a été réalisé par :

- 👨‍💻 **En-nakr Ayman**
- 👨‍💻 **Benyahya Othman**
- 👨‍💻 **Tamami Youssef**

---

## 🎓 Contexte académique

Ce projet est réalisé dans le cadre d'un **module de développement Java / Jakarta EE** à l'université.

### Objectifs pédagogiques

- Comprendre et appliquer une architecture en couches
- Maîtriser JPA et Hibernate pour la persistance
- Créer et consommer des services web SOAP
- Déployer une application sur un serveur Tomcat
- Utiliser Maven pour gérer les dépendances
- Travailler en équipe sur un projet structuré

### Compétences développées

✅ Architecture logicielle  
✅ Programmation orientée objet  
✅ Services web SOAP (JAX-WS)  
✅ ORM (JPA/Hibernate)  
✅ Base de données relationnelles  
✅ Outils de build (Maven)  
✅ Déploiement d'applications web

---

## 📚 Ressources et documentation

### Documentation officielle

- [Jakarta EE Documentation](https://jakarta.ee/specifications/)
- [Hibernate ORM Documentation](https://hibernate.org/orm/documentation/)
- [JAX-WS Tutorial (Oracle)](https://docs.oracle.com/javaee/7/tutorial/jaxws.htm)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

### Tutoriels utiles

- [SOAP Web Services with JAX-WS](https://www.baeldung.com/jax-ws)
- [JPA and Hibernate Tutorial](https://www.baeldung.com/learn-jpa-hibernate)
- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

### Outils recommandés

- **Postman** : Tester les services SOAP
- **SoapUI** : Outil dédié pour SOAP
- **MySQL Workbench** : Interface graphique pour MySQL
- **DBeaver** : Client universel pour bases de données

---

## 📄 Licence

Ce projet est réalisé à des fins éducatives dans le cadre universitaire.

