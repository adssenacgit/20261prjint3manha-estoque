CREATE DATABASE IF NOT EXISTS `20261_prjint3_manha_jarbasfilho` DEFAULT CHARACTER SET latin1;
USE `20261_prjint3_manha_jarbasfilho`;

CREATE TABLE IF NOT EXISTS `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_nome` varchar(100) NOT NULL,
  `empresa_nome` varchar(50) DEFAULT NULL,
  `usuario_email` varchar(100) NOT NULL,
  `usuario_cpf` varchar(14) NOT NULL,
  `usuario_senha` varchar(255) NOT NULL,
  `usuario_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `usuario_email` (`usuario_email`),
  UNIQUE KEY `usuario_cpf` (`usuario_cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `produto` (
  `produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `produto_nome` varchar(100) NOT NULL,
  `produto_quantidade` int(11) NOT NULL DEFAULT '0',
  `produto_preco` decimal(10,2) NOT NULL,
  `produto_codigo` int(11) NOT NULL,
  `produto_data_validade` date DEFAULT NULL,
  `produto_status` int(11) NOT NULL,
  PRIMARY KEY (`produto_id`),
  UNIQUE KEY `produto_codigo` (`produto_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `entrada` (
  `entrada_id` int(11) NOT NULL AUTO_INCREMENT,
  `entrada_data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `entrada_fornecedor` varchar(100) DEFAULT NULL,
  `entrada_valor_total` decimal(10,2) NOT NULL,
  `entrada_status` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`entrada_id`),
  KEY `fk_entrada_usuario` (`usuario_id`),
  CONSTRAINT `fk_entrada_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `entrada_produto` (
  `entrada_produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `entrada_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco_compra` decimal(10,2) NOT NULL,
  `entrada_produto_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`entrada_produto_id`),
  KEY `fk_entrada_produto_entrada` (`entrada_id`),
  KEY `fk_entrada_produto_produto` (`produto_id`),
  CONSTRAINT `fk_entrada_produto_entrada` FOREIGN KEY (`entrada_id`) REFERENCES `entrada` (`entrada_id`),
  CONSTRAINT `fk_entrada_produto_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `venda` (
  `venda_id` int(11) NOT NULL AUTO_INCREMENT,
  `venda_data` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `venda_valor_total` decimal(10,2) NOT NULL,
  `venda_status` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`venda_id`),
  KEY `fk_venda_usuario` (`usuario_id`),
  CONSTRAINT `fk_venda_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `venda_produto` (
  `venda_produto_id` int(11) NOT NULL AUTO_INCREMENT,
  `produto_id` int(11) NOT NULL,
  `venda_quantidade` int(11) NOT NULL,
  `venda_preco_unidade` decimal(10,2) NOT NULL,
  `venda_produto_status` int(11) DEFAULT NULL,
  `venda_id` int(11) NOT NULL,
  PRIMARY KEY (`venda_produto_id`),
  KEY `fk_venda_produto_venda` (`venda_id`),
  KEY `fk_venda_produto_produto` (`produto_id`),
  CONSTRAINT `fk_venda_produto_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`produto_id`),
  CONSTRAINT `fk_venda_produto_venda` FOREIGN KEY (`venda_id`) REFERENCES `venda` (`venda_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
