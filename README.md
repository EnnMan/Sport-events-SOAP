# SportEventsSOAP

## 📌 Description du projet

Ce projet consiste à développer une **API SOAP** pour la gestion d’événements sportifs, en utilisant les technologies **Jakarta EE**, **JPA (Hibernate)** et une base de données **MySQL**.

L’objectif principal est de mettre en pratique les concepts suivants :
- Architecture en couches
- Services SOAP
- Mapping Objet-Relationnel (ORM) avec JPA
- Accès aux données via DAO
- Déploiement sur un serveur Tomcat

Le projet est réalisé dans un cadre académique.

---

## 🛠️ Technologies utilisées

- Java SE 21
- Jakarta EE
- SOAP (JAX-WS)
- JPA / Hibernate
- MySQL
- Maven
- Apache Tomcat 11
- IntelliJ IDEA / Eclipse JEE

---

## 🏗️ Architecture du projet

Le projet suit une architecture en couches simple et claire :
**SOAP Endpoint → Service → DAO (JPA/Hibernate) → Base de données**
Chaque couche a un rôle bien défini afin de respecter la séparation des responsabilités.

---

## 🧩 Description des couches

### 🔹 Entités (entity)
Contiennent les classes annotées JPA représentant les tables de la base de données.

### 🔹 DAO
Assurent l’accès aux données en utilisant l’API JPA (EntityManager), sans utiliser de SQL natif.

### 🔹 Service
Contient la logique métier et fait le lien entre les endpoints SOAP et la couche DAO.

### 🔹 SOAP Endpoints
Exposent les opérations SOAP permettant d’effectuer les opérations CRUD sur les entités.

---

## 🗄️ Base de données

La base de données est gérée via **Hibernate (ORM)**.  
Les tables sont générées automatiquement à partir des entités JPA grâce à la configuration définie dans `persistence.xml`.

---

## 🧪 Tests

Les services SOAP peuvent être testés à l’aide d’outils comme :
- Postman
- SOAP UI

---

## 👥 Équipe du projet

- **En-nakr Ayman**
- **Benyahya Othman**
- **Tamami Youssef**

---

## 🎓 Contexte académique

Ce projet est réalisé dans le cadre d’un module de développement Java / Jakarta EE, et a pour but de consolider les notions vues en cours à travers un cas pratique.


