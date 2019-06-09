-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  jeu. 30 mai 2019 à 19:33
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

CREATE TABLE `anneescolaire` (
  `Id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `bulletin` (
  `Id` int(11) NOT NULL,
  `Id_trimestre` int(11) NOT NULL,
  `Id_inscription` int(11) NOT NULL,
  `Appreciation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bulletin`
--

INSERT INTO `bulletin` (`Id`, `Id_trimestre`, `Id_inscription`, `Appreciation`) VALUES
(1, 1, 2, 'Excellent trimestre'),
(2, 1, 1, 'Très bon trimestre'),
(3, 2, 1, 'Continuez sur cette voie pour la suite'),
(4, 1, 1, 'Manque de travail pour ce trimestre'),
(5, 1, 1, 'Poursuivez vos efforts pour la suite'),
(6, 1, 4, 'Aucune implication ce trimestre');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `Id` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Id_ecole` int(11) NOT NULL,
  `Id_niveau` int(11) NOT NULL,
  `Id_annee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`Id`, `Nom`, `Id_ecole`, `Id_niveau`, `Id_annee`) VALUES
(1, 'TD3', 2, 2, 3),
(2, 'TD5', 2, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `detail_bulletin`
--

CREATE TABLE `detail_bulletin` (
  `Id` int(11) NOT NULL,
  `Id_bulletin` int(11) NOT NULL,
  `Id_enseignement` int(11) NOT NULL,
  `Appreciation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `detail_bulletin`
--

INSERT INTO `detail_bulletin` (`Id`, `Id_bulletin`, `Id_enseignement`, `Appreciation`) VALUES
(1, 4, 3, 'Manque de travail global'),
(2, 1, 4, 'Excellent élève'),
(3, 5, 5, 'Continuez vos efforts'),
(4, 3, 6, 'Elève sérieux, continuez vos efforts'),
(5, 2, 1, 'Elève très sérieux et impliqué'),
(6, 6, 2, 'Il faut se ressaisir et travailler plus efficacement');

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

CREATE TABLE `discipline` (
  `Id` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(8, 'Réseaux');

-- --------------------------------------------------------

--
-- Structure de la table `ecole`
--

CREATE TABLE `ecole` (
  `Id` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Region` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ecole`
--

INSERT INTO `ecole` (`Id`, `Nom`, `Region`) VALUES
(1, 'La Pro', 'IDF'),
(2, 'ECE Paris', 'IDF'),
(3, 'Louis Pasteur', 'Bretagne');

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

CREATE TABLE `enseignement` (
  `Id` int(11) NOT NULL,
  `Id_classe` int(11) NOT NULL,
  `Id_discipline` int(11) NOT NULL,
  `Id_personne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignement`
--

INSERT INTO `enseignement` (`Id`, `Id_classe`, `Id_discipline`, `Id_personne`) VALUES
(1, 1, 6, 3),
(2, 1, 5, 7),
(3, 1, 1, 5),
(4, 2, 1, 11),
(5, 2, 7, 10),
(6, 2, 2, 6);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `Id` int(11) NOT NULL,
  `Id_db` int(11) NOT NULL,
  `Note` double NOT NULL,
  `Appreciation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`Id`, `Id_db`, `Note`, `Appreciation`) VALUES
(1, 2, 20, 'Excellente copie'),
(2, 1, 8.5, 'Nous percevons un manque de travail dans votre copie'),
(3, 4, 14, 'Bon travail dans ce devoir'),
(4, 3, 12.75, 'Des efforts sont perçus dans cette copie'),
(5, 5, 17, 'Très bonne copie'),
(6, 6, 7, 'Travail médiocre, reprenez-vous');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `Id` int(11) NOT NULL,
  `Id_classe` int(11) NOT NULL,
  `Id_personne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`Id`, `Id_classe`, `Id_personne`) VALUES
(1, 1, 1),
(2, 1, 4),
(3, 1, 2),
(4, 2, 8),
(5, 2, 9);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `Id` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(7, '6e'),
(8, '3e'),
(11, 'CE2');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `Id` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(11, 'Bolus', 'Anne', 1);

-- --------------------------------------------------------

--
-- Structure de la table `trimestre`
--

CREATE TABLE `trimestre` (
  `Id` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `Debut` varchar(255) NOT NULL,
  `Fin` varchar(255) NOT NULL,
  `Id_annee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `trimestre`
--

INSERT INTO `trimestre` (`Id`, `Numero`, `Debut`, `Fin`, `Id_annee`) VALUES
(1, 26172, '2019-05-01', '2019-05-31', 3),
(2, 52154, '2019-05-10', '2019-05-27', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `anneescolaire`
--
ALTER TABLE `anneescolaire`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `bulletin`
--
ALTER TABLE `bulletin`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_trimestre` (`Id_trimestre`),
  ADD KEY `Id_inscritpion` (`Id_inscription`);

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_ecole` (`Id_ecole`),
  ADD KEY `Id_niveau` (`Id_niveau`),
  ADD KEY `Id_annee` (`Id_annee`);

--
-- Index pour la table `detail_bulletin`
--
ALTER TABLE `detail_bulletin`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_bulletin` (`Id_bulletin`),
  ADD KEY `Id_enseignement` (`Id_enseignement`);

--
-- Index pour la table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `ecole`
--
ALTER TABLE `ecole`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `enseignement`
--
ALTER TABLE `enseignement`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_classe` (`Id_classe`),
  ADD KEY `Id_discipline` (`Id_discipline`),
  ADD KEY `Id_personne` (`Id_personne`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_db` (`Id_db`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_classe` (`Id_classe`),
  ADD KEY `Id_personne` (`Id_personne`);

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `trimestre`
--
ALTER TABLE `trimestre`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Id_annee` (`Id_annee`) USING BTREE;

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `anneescolaire`
--
ALTER TABLE `anneescolaire`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `bulletin`
--
ALTER TABLE `bulletin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `detail_bulletin`
--
ALTER TABLE `detail_bulletin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `discipline`
--
ALTER TABLE `discipline`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `ecole`
--
ALTER TABLE `ecole`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `enseignement`
--
ALTER TABLE `enseignement`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `trimestre`
--
ALTER TABLE `trimestre`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
