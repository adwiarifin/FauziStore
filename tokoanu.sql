-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `idbarang` varchar(10) NOT NULL,
  `namabarang` varchar(200) DEFAULT NULL,
  `kategori` int(2) DEFAULT NULL,
  `hargabarang` int(11) DEFAULT '0',
  `jumlah` mediumint(9) DEFAULT '0',
  PRIMARY KEY (`idbarang`),
  KEY `kategori` (`kategori`),
  CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `barang` (`idbarang`, `namabarang`, `kategori`, `hargabarang`, `jumlah`) VALUES
('a10201',	'lampu taman',	4,	1265000,	3),
('a10202',	'lampu hias',	5,	1100000,	3),
('b10201',	'sonika',	3,	4250000,	2);

DROP TABLE IF EXISTS `detailbarang`;
CREATE TABLE `detailbarang` (
  `idbarang` varchar(10) NOT NULL,
  `keterangan` text,
  `bahan` text,
  `satuan` varchar(10) NOT NULL,
  `ukuran` int(11) NOT NULL,
  PRIMARY KEY (`idbarang`),
  CONSTRAINT `detailbarang_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `detailbarang` (`idbarang`, `keterangan`, `bahan`, `satuan`, `ukuran`) VALUES
('a10201',	'',	'iron composite metal with alumunium top',	'unit',	3),
('a10202',	'bismillah',	'kertas',	'unit',	2),
('b10201',	'alat musik',	'brass',	'unit',	0);

DROP TABLE IF EXISTS `detailtransaksi`;
CREATE TABLE `detailtransaksi` (
  `nojual` int(11) NOT NULL,
  `idbarang` varchar(10) NOT NULL,
  `jmlbarang` int(11) NOT NULL,
  `hrgsatuan` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `totalharga` int(11) DEFAULT NULL,
  PRIMARY KEY (`nojual`,`idbarang`),
  KEY `idbarang` (`idbarang`),
  CONSTRAINT `detailtransaksi_ibfk_1` FOREIGN KEY (`nojual`) REFERENCES `transaksi` (`nojual`),
  CONSTRAINT `detailtransaksi_ibfk_2` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `iddiskon` mediumint(9) NOT NULL AUTO_INCREMENT,
  `namapromo` varchar(50) DEFAULT NULL,
  `tglmulai` date DEFAULT NULL,
  `tglselesai` date DEFAULT NULL,
  `jnsdiskon` varchar(80) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddiskon`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `idkategori` int(2) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(50) DEFAULT NULL,
  `jenis` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `kategori` (`idkategori`, `kategori`, `jenis`) VALUES
(1,	'elektrikal',	'koleksi'),
(2,	'elektrikal',	'perkakas'),
(3,	'mekanikal',	'alat musik'),
(4,	'dekorasi',	'outdoor'),
(5,	'dekorasi',	'indoor');

DROP TABLE IF EXISTS `pegawai`;
CREATE TABLE `pegawai` (
  `idpegawai` varchar(5) NOT NULL,
  `namapegawai` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`idpegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `pegawai` (`idpegawai`, `namapegawai`, `password`) VALUES
('01234',	'Adwi Arifin',	'912ec803b2ce49e4a541068d495ab570');

DROP TABLE IF EXISTS `pembayaran`;
CREATE TABLE `pembayaran` (
  `idbayar` smallint(1) NOT NULL AUTO_INCREMENT,
  `jenisbayar` varchar(12) NOT NULL,
  PRIMARY KEY (`idbayar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `nojual` int(11) NOT NULL AUTO_INCREMENT,
  `tanggaljual` date DEFAULT NULL,
  `totaljual` int(11) DEFAULT NULL,
  `idpegawai` varchar(5) NOT NULL,
  `idbayar` smallint(1) NOT NULL,
  PRIMARY KEY (`nojual`),
  KEY `idpegawai` (`idpegawai`),
  KEY `idbayar` (`idbayar`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idpegawai`) REFERENCES `pegawai` (`idpegawai`),
  CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`idbayar`) REFERENCES `pembayaran` (`idbayar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- 2018-01-13 01:01:26
