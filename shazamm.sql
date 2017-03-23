-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 23 Mars 2017 à 15:57
-- Version du serveur :  5.7.17-0ubuntu0.16.04.1
-- Version de PHP :  7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `shazamm`
--

-- --------------------------------------------------------

--
-- Structure de la table `Card`
--

CREATE TABLE `Card` (
  `ID_CARD` tinyint(4) UNSIGNED NOT NULL,
  `NAME` varchar(16) NOT NULL,
  `DESCRIPTION` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Card`
--

INSERT INTO `Card` (`ID_CARD`, `NAME`, `DESCRIPTION`) VALUES
(1, 'Mutisme', 'Aucun sort n’a plus d’effet pour les deux joueurs, jusqu’à la fin de la\r\nmanche. Les autres sorts éventuellement posés sont perdus.'),
(2, 'Clone', 'Je pose devant moi une des cartes jouées par l’adversaire au tour\r\nprécédent. Cette carte est appliquée à ce tour, comme si je l’avais\r\njouée normalement.'),
(3, 'Larcin', 'Tous les sorts joués à ce tour sont sous mon contrôle. Chacun est, à\r\nmon choix, appliqué comme si je l’avais joué, ou annulé et défaussé.'),
(4, 'Fin de manche', 'La manche est finie ! Les sorciers se replacent à 3 pas du mur de feu\r\n(dans sa position actuelle), et on commence une nouvelle manche.'),
(5, 'Milieu', 'Je replace immédiatement le mur de feu à égale distance des deux\r\nsorciers. Le tour continue ensuite normalement.'),
(6, 'Recyclage', 'Je peux rectifier ma mise, en ajoutant ou retranchant jusqu’à 5\r\npoints de mana.'),
(7, 'Boost attaque', 'La puissance de mon attaque est augmentée de 7.'),
(8, 'Double dose', 'La puissance de mon attaque est multipliée par deux.'),
(9, 'Qui perd gagne', 'Le mur de feu avance en sens inverse : vers celui qui a gagné ce\r\ntour. N’a pas d’effet si le mur de feu ne devait pas bouger.'),
(10, 'Brasier', 'Le mur de feu se déplace de deux cases au lieu d’une. Seulement s’il\r\ndevait se déplacer, bien sûr.'),
(11, 'Résistance', 'Si le mur de feu devait avancer vers moi, il ne bouge pas.'),
(12, 'Harpagon', 'Si je perds ce tour (i.e. si le mur de feu avance effectivement vers\r\nmoi), ma mise n’est pas retranchée de ma réserve de Mana.'),
(13, 'Boost réserve', 'Ma réserve de Mana s’augmente de 13 points. Après que j’ai payé\r\nce que je dois.'),
(14, 'Aspiration', 'Ma réserve de Mana s’augmente du montant de la mise de\r\nl’adversaire.');

-- --------------------------------------------------------

--
-- Structure de la table `Config`
--

CREATE TABLE `Config` (
  `BRIDGE_MAX_SIZE` tinyint(3) UNSIGNED NOT NULL,
  `MAX_MANA` tinyint(3) UNSIGNED NOT NULL,
  `SHUFFLE_STEPS` tinyint(3) UNSIGNED NOT NULL,
  `HAND_REFILL_SIZE` tinyint(3) UNSIGNED NOT NULL,
  `END_OF_ROUND_DRAW` tinyint(3) UNSIGNED NOT NULL,
  `FIRST_ROUND_DRAW` tinyint(3) UNSIGNED NOT NULL,
  `ID_CONFIG` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Config`
--

INSERT INTO `Config` (`BRIDGE_MAX_SIZE`, `MAX_MANA`, `SHUFFLE_STEPS`, `HAND_REFILL_SIZE`, `END_OF_ROUND_DRAW`, `FIRST_ROUND_DRAW`, `ID_CONFIG`) VALUES
(9, 30, 100, 5, 3, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `Player`
--

CREATE TABLE `Player` (
  `USERNAME` varchar(16) NOT NULL,
  `TOTAL_OF_GAME` smallint(5) UNSIGNED NOT NULL,
  `TOTAL_OF_VICTORY` smallint(6) UNSIGNED NOT NULL,
  `TOTAL_OF_EQUALITY` smallint(6) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Card`
--
ALTER TABLE `Card`
  ADD PRIMARY KEY (`ID_CARD`),
  ADD UNIQUE KEY `ID_CARD` (`ID_CARD`);

--
-- Index pour la table `Config`
--
ALTER TABLE `Config`
  ADD PRIMARY KEY (`ID_CONFIG`),
  ADD UNIQUE KEY `ID_CONFIG` (`ID_CONFIG`);

--
-- Index pour la table `Player`
--
ALTER TABLE `Player`
  ADD PRIMARY KEY (`USERNAME`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Card`
--
ALTER TABLE `Card`
  MODIFY `ID_CARD` tinyint(4) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `Config`
--
ALTER TABLE `Config`
  MODIFY `ID_CONFIG` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
