-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 14 juil. 2026 à 12:32
-- Version du serveur : 8.4.7
-- Version de PHP : 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `quack_shelter`
--

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `capacite_vol` bit(1) DEFAULT NULL,
  `date_naissance` date NOT NULL,
  `gestante` bit(1) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `pondeuse` bit(1) DEFAULT NULL,
  `quack_shelter` int NOT NULL,
  `sauvage` bit(1) DEFAULT NULL,
  `sterilisation` bit(1) DEFAULT NULL,
  `type_animal` varchar(31) NOT NULL,
  `couleur` varchar(50) DEFAULT NULL,
  `espece` varchar(50) DEFAULT NULL,
  `nom_animal` varchar(50) DEFAULT NULL,
  `race` varchar(50) DEFAULT NULL,
  `regime_alimentaire` varchar(200) DEFAULT NULL,
  `traitement` varchar(200) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `famille` enum('Bovide','Canin','Equide','Felin','Galide','Muscilide') DEFAULT NULL,
  `genre` enum('Femelle','Male') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaisx4d8l91bnl2dwgogtmafos` (`quack_shelter`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `animal`
--

INSERT INTO `animal` (`capacite_vol`, `date_naissance`, `gestante`, `id`, `pondeuse`, `quack_shelter`, `sauvage`, `sterilisation`, `type_animal`, `couleur`, `espece`, `nom_animal`, `race`, `regime_alimentaire`, `traitement`, `description`, `famille`, `genre`) VALUES
(NULL, '2022-05-12', b'0', 1, NULL, 1, b'0', b'1', 'Chien', 'Noir', NULL, 'Rex', 'Berger Allemand', 'Croquettes', 'Aucun', 'Chien très affectueux et joueur', 'Canin', 'Male'),
(NULL, '2023-03-20', b'0', 2, NULL, 1, b'0', b'1', 'Chat', 'Gris', NULL, 'Mia', 'Européen', 'Croquettes', 'Aucun', 'Chat calme habitué aux enfants', 'Felin', 'Femelle'),
(NULL, '2021-11-02', b'0', 3, NULL, 1, b'0', b'0', 'Chien', 'Blanc', NULL, 'Rocky', 'Husky', 'Croquettes', 'Anti inflammatoire', 'Chien blessé à la patte', 'Canin', 'Male'),
(b'1', '2024-06-10', NULL, 4, b'1', 1, b'0', NULL, 'Canard', 'Vert', NULL, 'Coincoin', 'Canard colvert', 'Graines', 'Aucun', 'Canard recueilli après abandon', 'Galide', 'Male'),
(NULL, '2022-09-01', b'0', 5, NULL, 1, b'0', b'1', 'Chat', 'Blanc et roux', NULL, 'Nala', 'Européen', 'Croquettes', 'Aucun', 'Chat sociable', 'Felin', 'Femelle'),
(NULL, '2025-01-15', b'0', 6, NULL, 1, b'0', NULL, 'NAC', 'Marron', 'Hamster', 'Ratatouille', NULL, 'Graines', 'Aucun', 'Hamster récupéré chez un particulier', 'Muscilide', 'Male'),
(b'0', '2024-04-15', NULL, 7, b'1', 1, b'0', NULL, 'Poule', 'Blanche', NULL, 'Bella', 'Poule rousse', 'Graines', 'Aucun', 'Poule domestique', 'Galide', 'Femelle'),
(b'1', '2023-08-01', NULL, 8, b'1', 1, b'0', NULL, 'Canard', 'Blanc', NULL, 'Donald', 'Pékin', 'Graines', 'Aucun', 'Canard calme', 'Galide', 'Male');

-- --------------------------------------------------------

--
-- Structure de la table `caracteres`
--

DROP TABLE IF EXISTS `caracteres`;
CREATE TABLE IF NOT EXISTS `caracteres` (
  `id_animal` int NOT NULL,
  `caracteres` enum('Affectueux','Agressif','Calin','Calme','Craintif','Gentil','Joueur','Timide') DEFAULT NULL,
  KEY `FKt22sp5r99e3is59kni163haw` (`id_animal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `caracteres`
--

INSERT INTO `caracteres` (`id_animal`, `caracteres`) VALUES
(1, 'Affectueux'),
(1, 'Joueur'),
(1, 'Calin'),
(2, 'Calme'),
(2, 'Gentil'),
(3, 'Craintif'),
(3, 'Timide'),
(4, 'Calme'),
(4, 'Gentil'),
(5, 'Affectueux'),
(5, 'Joueur'),
(6, 'Timide'),
(7, 'Calme'),
(8, 'Gentil');

-- --------------------------------------------------------

--
-- Structure de la table `emplacement`
--

DROP TABLE IF EXISTS `emplacement`;
CREATE TABLE IF NOT EXISTS `emplacement` (
  `complet` bit(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `nb_place` int NOT NULL,
  `type_box` enum('Aquarium','Box','Cage','Poulailler','Pre','Vivarium') NOT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Déchargement des données de la table `emplacement`
--

INSERT INTO `emplacement` (`complet`, `id`, `nb_place`, `type_box`) VALUES
(b'0', 1, 5, 'Box'),
(b'0', 2, 10, 'Cage'),
(b'0', 3, 4, 'Poulailler'),
(b'0', 4, 3, 'Vivarium'),
(b'0', 5, 6, 'Aquarium');

-- --------------------------------------------------------

--
-- Structure de la table `historique_sante`
--

DROP TABLE IF EXISTS `historique_sante`;
CREATE TABLE IF NOT EXISTS `historique_sante` (
  `animal_id` int NOT NULL,
  `date` date NOT NULL,
  `duree` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `poids` decimal(6,2) NOT NULL,
  `time` time(6) NOT NULL,
  `commentaire` varchar(255) NOT NULL,
  `cause` enum('Accouchement','Blessure','Maladie','Operation','Sterilisation','Vaccin') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsu3vjahurr21596r7e21tw350` (`animal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `historique_sante`
--

INSERT INTO `historique_sante` (`animal_id`, `date`, `duree`, `id`, `poids`, `time`, `commentaire`, `cause`) VALUES
(1, '2026-01-20', 0, 1, 32.50, '10:30:00.000000', 'Vaccination annuelle', 'Vaccin'),
(2, '2026-02-15', 0, 2, 4.80, '11:00:00.000000', 'Contrôle général', 'Vaccin'),
(3, '2026-03-02', 15, 3, 28.00, '09:30:00.000000', 'Patte blessée après accident', 'Blessure'),
(3, '2026-03-05', 7, 4, 27.50, '14:00:00.000000', 'Suivi vétérinaire', 'Operation'),
(4, '2026-03-10', 0, 5, 3.20, '15:00:00.000000', 'Contrôle santé', 'Vaccin'),
(5, '2026-04-05', 0, 6, 5.40, '16:00:00.000000', 'Bon état général', 'Vaccin'),
(6, '2026-04-20', 0, 7, 0.30, '10:15:00.000000', 'Contrôle NAC', 'Vaccin');

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cp` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `voie` varchar(255) DEFAULT NULL,
  `type` enum('APPARTEMENT','MAISON','SHELTER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK338m44gg9c91afn6hvgb9eh82` (`numero`,`voie`,`ville`,`cp`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`id`, `cp`, `numero`, `ville`, `voie`, `type`) VALUES
(1, '44100', '14', 'Nantes', 'Rue Qwack', 'SHELTER'),
(2, '44000', '25', 'Nantes', 'Rue des Lilas', 'APPARTEMENT'),
(3, '44200', '8', 'Nantes', 'Boulevard Victor Hugo', 'MAISON'),
(4, '44300', '12', 'Nantes', 'Rue des Animaux', 'APPARTEMENT');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `admin` bit(1) DEFAULT NULL,
  `date_embauche` date DEFAULT NULL,
  `date_engagement` date DEFAULT NULL,
  `date_inscription` date DEFAULT NULL,
  `habitation` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `quack_shelter` int DEFAULT NULL,
  `salaire` double DEFAULT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('BENEVOLE','EMPLOYE','PATRON','VISITEUR') DEFAULT NULL,
  `statut_activite` enum('ABSENT','BALADE','DISPO','NETTOYAGE','SOIN','VISITE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8l3j8smom1kapeu3y4f4d6g3g` (`login`),
  KEY `FKcnf0kemv2nq2rtp9s5cgpqfm6` (`habitation`),
  KEY `FKbg1j7i7oc6vrnxof4gqqvrkud` (`quack_shelter`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`admin`, `date_embauche`, `date_engagement`, `date_inscription`, `habitation`, `id`, `quack_shelter`, `salaire`, `nom`, `prenom`, `login`, `password`, `role`, `statut_activite`) VALUES
(b'0', NULL, NULL, '2026-01-10', 2, 1, 1, 0, 'Yohann', 'Yohann', 'Yohann', '$2a$10$o2PDl0TBktd9.OhRVayk..Qr76B1mVD1R6O1.vP/HKvBtd5iCycni', 'VISITEUR', 'VISITE'),
(b'1', '2025-01-01', NULL, NULL, 3, 2, 1, 3500, 'Ronan', 'Ronan', 'Ronan', '$2a$10$fd7IGOoCnCebuhFhY39PQe2UtoGOSqibGktOp5j6iw6hrdkiXnCUW', 'PATRON', 'DISPO'),
(b'0', '2025-03-15', NULL, NULL, 4, 3, 1, 1800, 'Clea', 'Clea', 'Clea', '$2a$10$IR0oIzkL54RQ.Q/afvgJ.OhjiK5zKkBowHQ1weGxpkHzWqz9h.poy', 'EMPLOYE', 'SOIN'),
(b'0', NULL, '2026-02-01', '2026-02-01', 2, 4, 1, 0, 'Marie', 'Marie', 'Marie', '$2a$10$WNFxOIRBg13nl763FIw0n.HOn6HivhvVXnCndnsdiu8Qr/3C1jvkC', 'BENEVOLE', 'NETTOYAGE');

-- --------------------------------------------------------

--
-- Structure de la table `quack_shelter`
--

DROP TABLE IF EXISTS `quack_shelter`;
CREATE TABLE IF NOT EXISTS `quack_shelter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lieu_id` int DEFAULT NULL,
  `nb_place` int NOT NULL,
  `tresorerie` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa1uoun508g4rq5asd4068moup` (`lieu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `quack_shelter`
--

INSERT INTO `quack_shelter` (`id`, `lieu_id`, `nb_place`, `tresorerie`) VALUES
(1, 1, 50, 25000.00);

-- --------------------------------------------------------

--
-- Structure de la table `statut_animal`
--

DROP TABLE IF EXISTS `statut_animal`;
CREATE TABLE IF NOT EXISTS `statut_animal` (
  `adoptant` int DEFAULT NULL,
  `animal` int NOT NULL,
  `date_arrivee` date NOT NULL,
  `date_depart` date DEFAULT NULL,
  `emplacement` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `statut` enum('Adopte','EnBalade','EnSoin','Mort','Present') NOT NULL,
  `statut_adoption` enum('ACCEPTE','EN_ATTENTE','REFUSE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk5ap2sp78chklifuxh5r4otae` (`animal`),
  KEY `FKsb66m9a215g3kpyfxwxgem13a` (`adoptant`),
  KEY `FKs10i95h11ygjyj397l21hd73d` (`emplacement`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `statut_animal`
--

INSERT INTO `statut_animal` (`adoptant`, `animal`, `date_arrivee`, `date_depart`, `emplacement`, `id`, `statut`, `statut_adoption`) VALUES
(1, 1, '2026-01-15', '2026-03-01', 1, 1, 'Adopte', 'ACCEPTE'),
(NULL, 2, '2026-02-10', NULL, 2, 2, 'Present', NULL),
(NULL, 3, '2026-03-01', NULL, 1, 3, 'EnSoin', NULL),
(NULL, 4, '2026-03-05', NULL, 3, 4, 'Present', NULL),
(NULL, 5, '2026-04-01', NULL, 2, 5, 'EnBalade', NULL),
(NULL, 6, '2026-04-15', NULL, 4, 6, 'Present', NULL),
(NULL, 7, '2026-05-01', NULL, 3, 7, 'Present', NULL),
(NULL, 8, '2026-05-15', NULL, 3, 8, 'Present', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `visite`
--

DROP TABLE IF EXISTS `visite`;
CREATE TABLE IF NOT EXISTS `visite` (
  `animal` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `quack_shelter` int NOT NULL,
  `visiteur` int NOT NULL,
  `date_visite` datetime(6) DEFAULT NULL,
  `statut` enum('ACCEPTE','EN_ATTENTE','REFUSE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpahu0hfa8ox6hgsgcdht5xdal` (`animal`),
  KEY `FKr71bw9vkpc6i54ekdni2ytgc1` (`quack_shelter`),
  KEY `FKmnwwggjoxsl81vxn3r1qut0mu` (`visiteur`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `visite`
--

INSERT INTO `visite` (`animal`, `id`, `quack_shelter`, `visiteur`, `date_visite`, `statut`) VALUES
(2, 1, 1, 1, '2026-07-01 14:00:00.000000', 'ACCEPTE'),
(5, 2, 1, 1, '2026-07-05 15:30:00.000000', 'ACCEPTE'),
(3, 3, 1, 1, '2026-07-10 10:00:00.000000', 'EN_ATTENTE'),
(6, 4, 1, 4, '2026-07-02 09:30:00.000000', 'ACCEPTE'),
(8, 5, 1, 1, '2026-07-08 16:00:00.000000', 'REFUSE');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `FKaisx4d8l91bnl2dwgogtmafos` FOREIGN KEY (`quack_shelter`) REFERENCES `quack_shelter` (`id`);

--
-- Contraintes pour la table `caracteres`
--
ALTER TABLE `caracteres`
  ADD CONSTRAINT `FKt22sp5r99e3is59kni163haw` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id`);

--
-- Contraintes pour la table `historique_sante`
--
ALTER TABLE `historique_sante`
  ADD CONSTRAINT `FKsu3vjahurr21596r7e21tw350` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`);

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `FKbg1j7i7oc6vrnxof4gqqvrkud` FOREIGN KEY (`quack_shelter`) REFERENCES `quack_shelter` (`id`),
  ADD CONSTRAINT `FKcnf0kemv2nq2rtp9s5cgpqfm6` FOREIGN KEY (`habitation`) REFERENCES `lieu` (`id`);

--
-- Contraintes pour la table `quack_shelter`
--
ALTER TABLE `quack_shelter`
  ADD CONSTRAINT `FKouk5ynwnqndfbilf4fnf73h61` FOREIGN KEY (`lieu_id`) REFERENCES `lieu` (`id`);

--
-- Contraintes pour la table `statut_animal`
--
ALTER TABLE `statut_animal`
  ADD CONSTRAINT `FK8ecw9xu6gdtjoxn394y1etejb` FOREIGN KEY (`animal`) REFERENCES `animal` (`id`),
  ADD CONSTRAINT `FKs10i95h11ygjyj397l21hd73d` FOREIGN KEY (`emplacement`) REFERENCES `emplacement` (`id`),
  ADD CONSTRAINT `FKsb66m9a215g3kpyfxwxgem13a` FOREIGN KEY (`adoptant`) REFERENCES `personne` (`id`);

--
-- Contraintes pour la table `visite`
--
ALTER TABLE `visite`
  ADD CONSTRAINT `FKmnwwggjoxsl81vxn3r1qut0mu` FOREIGN KEY (`visiteur`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `FKpahu0hfa8ox6hgsgcdht5xdal` FOREIGN KEY (`animal`) REFERENCES `animal` (`id`),
  ADD CONSTRAINT `FKr71bw9vkpc6i54ekdni2ytgc1` FOREIGN KEY (`quack_shelter`) REFERENCES `quack_shelter` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
