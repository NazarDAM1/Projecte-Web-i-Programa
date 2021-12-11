-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 192.168.18.136    Database: projecte
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `nom_cat` text,
  `informacio` text,
  `codi` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`codi`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES ('Tuberia','Tubs dʻevacuació amb diferents mides i alta resistència, per a sistemes dʻevacuació dʻaigua.',1),('Fusteria','Tots els bàsics que necessites per fer els teus treballs de fusteria.',2),('Fontanería','Encuentra todos los accesorios y herramientas de fontanería y tratamiento de agua que necesitas para tu proyecto.',3),('Martells',' alicates, tornavisos, espàtules… eines per a diversos usos i necessitats, del més senzill, al més professional..',4);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equival`
--

DROP TABLE IF EXISTS `equival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equival` (
  `id_producte1` int DEFAULT NULL,
  `id_producte2` int DEFAULT NULL,
  KEY `id_producte1` (`id_producte1`),
  KEY `id_producte2` (`id_producte2`),
  CONSTRAINT `equival_ibfk_1` FOREIGN KEY (`id_producte1`) REFERENCES `producte` (`id`),
  CONSTRAINT `equival_ibfk_2` FOREIGN KEY (`id_producte2`) REFERENCES `producte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equival`
--

LOCK TABLES `equival` WRITE;
/*!40000 ALTER TABLE `equival` DISABLE KEYS */;
INSERT INTO `equival` VALUES (1,2),(1,3),(2,1),(2,3),(3,1),(2,2),(4,5),(4,6),(5,4),(5,6),(6,4),(6,5),(7,8),(7,9),(8,7),(8,9),(9,7),(9,8),(1,974),(974,1);
/*!40000 ALTER TABLE `equival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fet`
--

DROP TABLE IF EXISTS `fet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fet` (
  `codi_mat` int DEFAULT NULL,
  `id_prod` int DEFAULT NULL,
  KEY `codi_mat` (`codi_mat`),
  KEY `id_prod` (`id_prod`),
  CONSTRAINT `fet_ibfk_1` FOREIGN KEY (`codi_mat`) REFERENCES `material` (`codi`),
  CONSTRAINT `fet_ibfk_2` FOREIGN KEY (`id_prod`) REFERENCES `producte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fet`
--

LOCK TABLES `fet` WRITE;
/*!40000 ALTER TABLE `fet` DISABLE KEYS */;
INSERT INTO `fet` VALUES (6,1),(6,2),(6,3),(5,4),(5,5),(5,6),(2,7),(2,8),(2,9),(7,10),(7,11),(7,12),(7,13),(4,949),(6,974);
/*!40000 ALTER TABLE `fet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `nom_mat` text,
  `codi` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`codi`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES ('PVC',1),('Fusta',2),('Ferro',3),('Plastic',4),('Cobre',5),('polibutileno',6),('Laton',7);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porta`
--

DROP TABLE IF EXISTS `porta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porta` (
  `p_data` date DEFAULT NULL,
  `id_proveidor` int DEFAULT NULL,
  `id_producte` int DEFAULT NULL,
  KEY `id_proveidor` (`id_proveidor`),
  KEY `id_producte` (`id_producte`),
  CONSTRAINT `porta_ibfk_1` FOREIGN KEY (`id_proveidor`) REFERENCES `proveidor` (`id`),
  CONSTRAINT `porta_ibfk_2` FOREIGN KEY (`id_producte`) REFERENCES `producte` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porta`
--

LOCK TABLES `porta` WRITE;
/*!40000 ALTER TABLE `porta` DISABLE KEYS */;
INSERT INTO `porta` VALUES (NULL,1,1),(NULL,1,2),(NULL,1,3),(NULL,2,7),(NULL,2,8),(NULL,2,9);
/*!40000 ALTER TABLE `porta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producte`
--

DROP TABLE IF EXISTS `producte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producte` (
  `id` int NOT NULL AUTO_INCREMENT,
  `preu` decimal(10,0) DEFAULT NULL,
  `codi_cat` int DEFAULT NULL,
  `nom_prod` text NOT NULL,
  `quantitat` int NOT NULL,
  `descripcio` varchar(90) DEFAULT NULL,
  `img` text,
  PRIMARY KEY (`id`),
  KEY `codi_cat` (`codi_cat`),
  CONSTRAINT `producte_ibfk_1` FOREIGN KEY (`codi_cat`) REFERENCES `categoria` (`codi`)
) ENGINE=InnoDB AUTO_INCREMENT=975 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producte`
--

LOCK TABLES `producte` WRITE;
/*!40000 ALTER TABLE `producte` DISABLE KEYS */;
INSERT INTO `producte` VALUES (1,3,1,'Codo Polibutileno',35,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/CodoPolibutileno.PNG'),(2,6,1,'Te Igual Polibutileno',22,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/TeIgualPolibutileno.PNG'),(3,11,1,'Manguito De Union Polibutileno',19,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/ManguitoDeUnionPolibutileno.PNG'),(4,3,1,'Tubo Cobre 18mm',33333368,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/TuboCobre18mm.PNG'),(5,6,1,'Codo De 90º',355,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/CodoDe90.PNG'),(6,26,1,'Rollo De Cobre 25M 18mm',18,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes//RolloDeCobre25M18mm.PNG'),(7,3,2,'Tablero Macizo Roble',35,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/TableroMacizoRoble.PNG'),(8,6,2,'Estante Roble Macizo',22,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/EstanteRobleMacizo.PNG'),(9,26,2,'Rodaja De Roble',18,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/RodajaDeRoble.PNG'),(10,3,3,'Lave Escuadra Laton',35,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/LaveEscuadraLaton.PNG'),(11,6,3,'Palanca Valvula Laton',22,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/PalancaValvulaLaton.PNG'),(12,26,3,'Valvula Esfera Laton',18,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/ValvulaEsferaLaton.PNG'),(13,26,3,'Grifo Exterior Laton Plastic',18,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/GrifoExteriorLatonPlastic.PNG'),(14,3,4,'Juego 8 Destornilladores',35,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/Juego8Destornilladores.PNG'),(15,6,4,'Mini Sierra',22,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/MiniSerra.PNG'),(16,26,4,'Alicate Ajustable',18,'Lorem, ipsum dolor sit amet consectetur adipisicing elit.Lorem, ipsum dolor ','img/img_productes/AlicateAjustable.PNG'),(17,33,3,'asdasd',33,'asdasdasd','img/img_productes/asdasd.PNG'),(18,30,3,'piedra',33,'asdasdsad','img/img_productes/piedra.PNG');
/*!40000 ALTER TABLE `producte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveidor`
--

DROP TABLE IF EXISTS `proveidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveidor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_prov` text NOT NULL,
  `correu` text NOT NULL,
  `telefon` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveidor`
--

LOCK TABLES `proveidor` WRITE;
/*!40000 ALTER TABLE `proveidor` DISABLE KEYS */;
INSERT INTO `proveidor` VALUES (1,'matPolibutileno','matPolibutileno@gmail.com',642424242),(2,'matFusta','matFusta@gmail.com',642424242);
/*!40000 ALTER TABLE `proveidor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-10 13:54:56
