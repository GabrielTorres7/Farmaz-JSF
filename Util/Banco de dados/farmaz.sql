-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 23-Jul-2018 às 01:44
-- Versão do servidor: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `farmaz`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

DROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `cod_cidade` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cod_uf` varchar(2) COLLATE utf8_bin NOT NULL,
  `nome` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cod_cidade`),
  UNIQUE KEY `cod_cidade` (`cod_cidade`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `seq_cliente` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` varchar(40) COLLATE utf8_bin NOT NULL,
  `nome` varchar(20) COLLATE utf8_bin NOT NULL,
  `documento_identificacao` varchar(20) COLLATE utf8_bin NOT NULL,
  `telefone` varchar(11) COLLATE utf8_bin NOT NULL,
  `senha` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`seq_cliente`),
  UNIQUE KEY `seq_cliente` (`seq_cliente`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `disponibilidade`
--

DROP TABLE IF EXISTS `disponibilidade`;
CREATE TABLE IF NOT EXISTS `disponibilidade` (
  `seq_disponibilidade` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seq_produto` int(11) NOT NULL,
  `cadastro_prefeitura` varchar(20) COLLATE utf8_bin NOT NULL,
  `estoque` double NOT NULL,
  `preco` decimal(10,0) NOT NULL,
  `avaliacao` int(11) NOT NULL,
  PRIMARY KEY (`seq_disponibilidade`),
  UNIQUE KEY `seq_disponibilidade` (`seq_disponibilidade`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

DROP TABLE IF EXISTS `endereco`;
CREATE TABLE IF NOT EXISTS `endereco` (
  `seq_endereco` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seq_cliente` int(20) NOT NULL,
  `cod_cidade` int(11) NOT NULL,
  `cod_uf` varchar(2) COLLATE utf8_bin NOT NULL,
  `cep` varchar(8) COLLATE utf8_bin NOT NULL,
  `bairro` varchar(20) COLLATE utf8_bin NOT NULL,
  `rua` varchar(20) COLLATE utf8_bin NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`seq_endereco`),
  UNIQUE KEY `seq_endereco` (`seq_endereco`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `farmacia`
--

DROP TABLE IF EXISTS `farmacia`;
CREATE TABLE IF NOT EXISTS `farmacia` (
  `cadastro_prefeitura` varchar(20) COLLATE utf8_bin NOT NULL,
  `cod_cidade` int(11) NOT NULL,
  `cod_uf` varchar(2) COLLATE utf8_bin NOT NULL,
  `cnpj` varchar(14) COLLATE utf8_bin NOT NULL,
  `nome` varchar(20) COLLATE utf8_bin NOT NULL,
  `cep` varchar(8) COLLATE utf8_bin NOT NULL,
  `bairro` varchar(20) COLLATE utf8_bin NOT NULL,
  `rua` varchar(20) COLLATE utf8_bin NOT NULL,
  `numero` int(11) NOT NULL,
  PRIMARY KEY (`cadastro_prefeitura`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

DROP TABLE IF EXISTS `item_pedido`;
CREATE TABLE IF NOT EXISTS `item_pedido` (
  `seq_item_pedido` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seq_pedido` int(20) NOT NULL,
  `seq_produto` int(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`seq_item_pedido`),
  UNIQUE KEY `seq_item_pedido` (`seq_item_pedido`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `seq_pedido` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `seq_cliente` int(20) NOT NULL,
  `cadastro_prefeitura` varchar(20) COLLATE utf8_bin NOT NULL,
  `data` datetime NOT NULL,
  `status` char(1) COLLATE utf8_bin NOT NULL,
  `token_pagseguro` int(11) NOT NULL,
  PRIMARY KEY (`seq_pedido`),
  UNIQUE KEY `seq_pedido` (`seq_pedido`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE IF NOT EXISTS `produto` (
  `seq_produto` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) COLLATE utf8_bin NOT NULL,
  `receita` tinyint(1) NOT NULL,
  `descricao` varchar(50) COLLATE utf8_bin NOT NULL,
  `laboratorio` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `cadastro_anvisa` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`seq_produto`),
  UNIQUE KEY `seq_produto` (`seq_produto`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Estrutura da tabela `uf`
--

DROP TABLE IF EXISTS `uf`;
CREATE TABLE IF NOT EXISTS `uf` (
  `cod_uf` varchar(2) COLLATE utf8_bin NOT NULL,
  `nome` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cod_uf`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
