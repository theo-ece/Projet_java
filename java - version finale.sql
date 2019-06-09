-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 07 juin 2019 à 22:30
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `java`
--

-- --------------------------------------------------------

--
-- Structure de la table `anneescolaire`
--

DROP TABLE IF EXISTS `anneescolaire`;
CREATE TABLE IF NOT EXISTS `anneescolaire` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `anneescolaire`
--

INSERT INTO `anneescolaire` (`Id`, `date`) VALUES
(1, '2016-2017'),
(2, '2017-2018'),
(3, '2018-2019'),
(4, '2019-2020');

-- --------------------------------------------------------

--
-- Structure de la table `bulletin`
--

DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE IF NOT EXISTS `bulletin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_trimestre` int(11) NOT NULL,
  `Id_inscription` int(11) NOT NULL,
  `Appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_trimestre` (`Id_trimestre`),
  KEY `Id_inscritpion` (`Id_inscription`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bulletin`
--

INSERT INTO `bulletin` (`Id`, `Id_trimestre`, `Id_inscription`, `Appreciation`) VALUES
(7, 1, 7, 'Bon trimestre'),
(8, 2, 7, 'Trimestre passable'),
(9, 3, 7, 'Trimestre correct'),
(10, 4, 19, 'Excellent trimestre'),
(11, 5, 19, 'Des efforts sont perçus'),
(12, 10, 19, 'Excellent trimestre'),
(13, 1, 17, 'Trimestre globalement bon'),
(14, 2, 17, 'Trimestre bon mais avec une légère baisse'),
(15, 3, 17, 'Excellent trimestre');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Id_ecole` int(11) NOT NULL,
  `Id_niveau` int(11) NOT NULL,
  `Id_annee` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_ecole` (`Id_ecole`),
  KEY `Id_niveau` (`Id_niveau`),
  KEY `Id_annee` (`Id_annee`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`Id`, `Nom`, `Id_ecole`, `Id_niveau`, `Id_annee`) VALUES
(6, 'A', 4, 6, 3),
(7, 'A', 4, 7, 3),
(8, 'A', 4, 8, 3),
(9, 'A', 4, 9, 3),
(10, 'A', 4, 10, 3),
(11, 'TD1', 2, 1, 3),
(12, 'TD1', 2, 3, 3),
(13, 'TD1', 2, 2, 3),
(14, 'TD1', 2, 4, 3),
(15, 'TD1', 2, 5, 3),
(16, 'B', 4, 6, 3),
(17, 'B', 4, 7, 3),
(18, 'B', 4, 8, 3),
(19, 'B', 4, 9, 3),
(21, 'B', 4, 10, 3),
(22, 'TD2', 2, 1, 3),
(23, 'TD2', 2, 3, 3),
(24, 'TD2', 2, 2, 3),
(25, 'TD2', 2, 4, 3),
(26, 'TD2', 2, 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `detail_bulletin`
--

DROP TABLE IF EXISTS `detail_bulletin`;
CREATE TABLE IF NOT EXISTS `detail_bulletin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_bulletin` int(11) NOT NULL,
  `Id_enseignement` int(11) NOT NULL,
  `Appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_bulletin` (`Id_bulletin`),
  KEY `Id_enseignement` (`Id_enseignement`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `detail_bulletin`
--

INSERT INTO `detail_bulletin` (`Id`, `Id_bulletin`, `Id_enseignement`, `Appreciation`) VALUES
(7, 7, 11, 'Bien'),
(8, 8, 19, 'Manque de travail'),
(9, 10, 14, 'Excellent'),
(10, 12, 12, 'Très bien'),
(11, 9, 22, 'Correct'),
(12, 11, 10, 'Continuez vos efforts'),
(13, 13, 10, 'Projets et TP excellents mais un manque de travail lors des DS'),
(14, 14, 10, 'Une augmentation dans la compréhension du cours mais avec une légère baisse dans le travail'),
(15, 15, 16, 'Noël avant l\'heure, excellent résultat.');

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `discipline`
--

INSERT INTO `discipline` (`Id`, `Nom`) VALUES
(1, 'Mathématiques'),
(2, 'Physique'),
(3, 'Langage C++'),
(4, 'Electronique'),
(5, 'Thermodynamique'),
(6, 'Java'),
(7, 'Langage C'),
(8, 'Réseaux'),
(9, 'Lecture'),
(10, 'Ecriture'),
(11, 'Arts Plastiques'),
(12, 'EPS'),
(13, 'Musique'),
(14, 'Poésie');

-- --------------------------------------------------------

--
-- Structure de la table `ecole`
--

DROP TABLE IF EXISTS `ecole`;
CREATE TABLE IF NOT EXISTS `ecole` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Region` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ecole`
--

INSERT INTO `ecole` (`Id`, `Nom`, `Region`) VALUES
(1, 'La Pro', 'IDF'),
(2, 'ECE Paris', 'IDF'),
(3, 'Louis Pasteur', 'Bretagne'),
(4, 'Jules Michelet', 'IDF');

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_classe` int(11) NOT NULL,
  `Id_discipline` int(11) NOT NULL,
  `Id_personne` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_classe` (`Id_classe`),
  KEY `Id_discipline` (`Id_discipline`),
  KEY `Id_personne` (`Id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignement`
--

INSERT INTO `enseignement` (`Id`, `Id_classe`, `Id_discipline`, `Id_personne`) VALUES
(9, 13, 6, 3),
(10, 6, 11, 6),
(11, 11, 4, 23),
(12, 11, 1, 5),
(13, 11, 2, 16),
(14, 12, 3, 10),
(15, 11, 7, 17),
(16, 13, 5, 7),
(17, 13, 8, 27),
(18, 6, 9, 32),
(19, 6, 12, 31),
(20, 6, 14, 29),
(21, 6, 13, 30),
(22, 6, 10, 33);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_db` int(11) NOT NULL,
  `Note` double NOT NULL,
  `Appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_db` (`Id_db`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`Id`, `Id_db`, `Note`, `Appreciation`) VALUES
(11, 7, 15, 'Bonne copie'),
(12, 8, 9.25, 'Cette copie manque de travail'),
(13, 9, 20, 'Excellente copie'),
(14, 10, 18.5, 'Très bonne copie'),
(15, 11, 14.25, 'Bonne copie'),
(16, 12, 11.75, 'De bonnes choses mais travaillez plus'),
(17, 7, 15, 'Bien'),
(18, 11, 11, 'passable'),
(19, 8, 8.5, 'a REVOIR'),
(20, 8, 8, 'A revoir'),
(21, 12, 13, 'Un bon début'),
(22, 11, 10, 'Juste mais passable'),
(23, 10, 17.5, 'Continuez comme ça'),
(24, 10, 18.2, 'Quelques infimes erreurs '),
(25, 8, 5.5, 'Essayez encore'),
(26, 8, 2, 'Raté, pas de chance'),
(27, 8, 7, 'Non, le théorème de Chuck Norris ne le permet pas'),
(28, 8, 13, 'Le travail paye enfin !'),
(29, 9, 20, 'Rien à redire'),
(30, 9, 19.5, 'Presque parfait'),
(31, 9, 15, 'La prochaine fois n\'oubliez pas votre carte étudiante'),
(32, 9, 19, '-1 point pour faute dans votre nom'),
(33, 9, 14.5, 'Un léger mal entendu'),
(34, 9, 18.5, 'La glissade inverse'),
(35, 7, 14.5, 'bonne copie'),
(36, 7, 12, 'Correct'),
(37, 7, 16, 'Très bien'),
(38, 7, 15, 'Bonne copie'),
(39, 7, 15.5, 'Beau travail'),
(40, 11, 13.5, 'Pourquoi pas'),
(41, 11, 19.5, 'Excellente copie'),
(42, 11, 3.5, 'Une erreur de compréhension ?'),
(43, 11, 15.5, 'Beau travail'),
(44, 12, 9.5, 'Des erreurs d\'inattention '),
(45, 12, 10.5, 'Quelques points a revoir'),
(46, 10, 10.5, 'Attention aux erreurs sur les développements '),
(47, 12, 16.5, 'Des efforts se font sentir'),
(48, 12, 8.5, 'La récurrence des erreurs se fait sentir'),
(49, 10, 16, 'Bonne copie'),
(50, 12, 13, 'Bien.'),
(51, 10, 14, 'Non, la carte étudiante n\'est pas optionnelle'),
(52, 10, 18.5, 'Un excellent travail'),
(53, 13, 13, 'Bien.'),
(54, 13, 18.5, 'Excellent '),
(55, 13, 9.5, 'Un manque de rigueur se fait percevoir'),
(56, 13, 19.5, 'Projet parfait, rien à redire.'),
(57, 13, 15, 'Bonne copie'),
(58, 13, 16, 'Parfait sauf, ou est le dernier exercice ?'),
(59, 13, 12, 'Sérialisation à revoir'),
(60, 14, 15, 'Attention aux erreurs de syntaxe '),
(61, 14, 16, 'Une légère baisse de motivation se fait ressentir'),
(62, 14, 14.5, 'Il faut apprendre son cours ! Un \"perfect\" aurait pu voir le jour'),
(63, 14, 16.5, 'Excellent travail'),
(64, 14, 15, 'Bonne copie'),
(65, 14, 16.5, 'Cours bien compris.'),
(66, 14, 15, 'Travail suffisant mais il se doit d\'aller plus loin dans le projet.'),
(67, 15, 20, 'Champagne ! '),
(68, 15, 19.5, 'Moins 0.5, c\'est cadeau (orthographe).'),
(69, 15, 17.5, 'Excellent devoir'),
(70, 15, 18.5, 'Très bon travail'),
(71, 15, 20, 'Attention à ne pas utiliser de fonctions trop avancées.'),
(72, 15, 20, 'Plus rien à vous apprendre.'),
(73, 15, 16, 'Oublis de révision pour le partiel, mais excellent travail');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Id_classe` int(11) NOT NULL,
  `Id_personne` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_classe` (`Id_classe`),
  KEY `Id_personne` (`Id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`Id`, `Id_classe`, `Id_personne`) VALUES
(7, 6, 19),
(8, 6, 20),
(9, 7, 22),
(10, 7, 8),
(11, 11, 18),
(13, 11, 14),
(14, 12, 24),
(15, 12, 25),
(16, 7, 26),
(17, 13, 2),
(18, 13, 1),
(19, 11, 4),
(20, 12, 9),
(21, 12, 13),
(22, 13, 15),
(25, 6, 21),
(26, 7, 28);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`Id`, `Nom`) VALUES
(1, 'Ing1'),
(2, 'Ing3'),
(3, 'Ing2'),
(4, 'Ing4'),
(5, 'Ing5'),
(6, 'CP'),
(7, 'CE1'),
(8, 'CE2'),
(9, 'CM1'),
(10, 'CM2');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Type` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`Id`, `Nom`, `Prenom`, `Type`) VALUES
(1, 'Roussel', 'Louis', 0),
(2, 'Witz', 'Theo', 0),
(3, 'Busca', 'Jean-Michel', 1),
(4, 'Girard', 'Flora', 0),
(5, 'Meunier', 'Marc', 1),
(6, 'Daz', 'Elisabeth', 1),
(7, 'Gallantis', 'Serena', 1),
(8, 'Jardin', 'Marie', 0),
(9, 'Da Silva', 'Maxime', 0),
(10, 'Segado', 'Jean-Pierre', 1),
(13, 'Boileau', 'Marie', 0),
(14, 'Clément', 'Robin', 0),
(15, 'Dublin', 'Stéphanie', 0),
(16, 'Sapin', 'Géraldine', 1),
(17, 'Louvel', 'Paul', 1),
(18, 'Larousse', 'Jeanne', 0),
(19, 'Polar', 'Dimitri', 0),
(20, 'Garot', 'Laurine', 0),
(21, 'Nivel', 'Jean-Baptiste', 0),
(22, 'Lafitte', 'Grégoire', 0),
(23, 'Roux', 'Pascal', 1),
(24, 'Albert', 'Michel', 0),
(25, 'Justin', 'Théo', 0),
(26, 'Lafeuille', 'Appoline', 0),
(27, 'Boucher', 'Stéphanie', 1),
(28, 'Archibald', 'Théodore', 0),
(29, 'Bellier', 'Marguerite', 1),
(30, 'Dron', 'Charles', 1),
(31, 'Hubert', 'Jean', 1),
(32, 'Tan', 'Marcel', 1),
(33, 'Marcelle', 'Alice', 1);

-- --------------------------------------------------------

--
-- Structure de la table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
CREATE TABLE IF NOT EXISTS `trimestre` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` int(11) NOT NULL,
  `Debut` varchar(255) NOT NULL,
  `Fin` varchar(255) NOT NULL,
  `Id_annee` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_annee` (`Id_annee`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `trimestre`
--

INSERT INTO `trimestre` (`Id`, `Numero`, `Debut`, `Fin`, `Id_annee`) VALUES
(1, 1198, '2016-09-03', '2016-11-31', 1),
(2, 6543, '2016-12-01', '2017-02-28', 1),
(3, 5423, '2017-03-01', '2017-06-20', 1),
(4, 764, '2017-09-03', '2017-11-31', 2),
(5, 543, '2017-12-01', '2018-02-28', 2),
(6, 87, '2018-03-01', '2018-06-20', 2),
(7, 52154, '2018-09-03', '2018-11-31', 3),
(8, 26172, '2019-12-01', '2019-02-28', 3),
(9, 76, '2019-03-01', '2019-06-20', 3),
(10, 5431, '2019-09-03', '2019-11-31', 4),
(11, 123, '2019-12-01', '2020-02-28', 4),
(12, 78756, '2020-03-01', '2020-06-20', 4);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`Id_inscription`) REFERENCES `inscription` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`Id_trimestre`) REFERENCES `trimestre` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`Id_annee`) REFERENCES `anneescolaire` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `classe_ibfk_2` FOREIGN KEY (`Id_ecole`) REFERENCES `ecole` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `classe_ibfk_3` FOREIGN KEY (`Id_niveau`) REFERENCES `niveau` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `detail_bulletin`
--
ALTER TABLE `detail_bulletin`
  ADD CONSTRAINT `detail_bulletin_ibfk_1` FOREIGN KEY (`Id_bulletin`) REFERENCES `bulletin` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_bulletin_ibfk_2` FOREIGN KEY (`Id_enseignement`) REFERENCES `enseignement` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `enseignement`
--
ALTER TABLE `enseignement`
  ADD CONSTRAINT `enseignement_ibfk_1` FOREIGN KEY (`Id_classe`) REFERENCES `classe` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enseignement_ibfk_2` FOREIGN KEY (`Id_discipline`) REFERENCES `discipline` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `enseignement_ibfk_3` FOREIGN KEY (`Id_personne`) REFERENCES `personne` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`Id_db`) REFERENCES `detail_bulletin` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`Id_classe`) REFERENCES `classe` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`Id_personne`) REFERENCES `personne` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `trimestre`
--
ALTER TABLE `trimestre`
  ADD CONSTRAINT `trimestre_ibfk_1` FOREIGN KEY (`Id_annee`) REFERENCES `anneescolaire` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
