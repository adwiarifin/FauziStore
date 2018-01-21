-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.18 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table tokoanu.barang
DROP TABLE IF EXISTS `barang`;
CREATE TABLE IF NOT EXISTS `barang` (
  `idbarang` varchar(10) NOT NULL,
  `namabarang` varchar(200) DEFAULT NULL,
  `kategori` int(2) DEFAULT NULL,
  `hargabarang` int(11) DEFAULT '0',
  `jumlah` mediumint(9) DEFAULT '0',
  PRIMARY KEY (`idbarang`),
  KEY `kategori` (`kategori`),
  CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.barang: ~2 rows (approximately)
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` (`idbarang`, `namabarang`, `kategori`, `hargabarang`, `jumlah`) VALUES
	('a10201', 'lampu taman', 4, 1265000, 2),
	('a10202', 'lampu hias', 5, 1100000, 1),
	('b10201', 'sonika', 3, 4250000, 1);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;

-- Dumping structure for table tokoanu.detailbarang
DROP TABLE IF EXISTS `detailbarang`;
CREATE TABLE IF NOT EXISTS `detailbarang` (
  `idbarang` varchar(10) NOT NULL,
  `keterangan` text,
  `bahan` text,
  `satuan` varchar(10) NOT NULL,
  `ukuran` int(11) NOT NULL,
  PRIMARY KEY (`idbarang`),
  CONSTRAINT `detailbarang_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.detailbarang: ~2 rows (approximately)
/*!40000 ALTER TABLE `detailbarang` DISABLE KEYS */;
INSERT INTO `detailbarang` (`idbarang`, `keterangan`, `bahan`, `satuan`, `ukuran`) VALUES
	('a10201', '', 'iron composite metal with alumunium top', 'unit', 3),
	('a10202', 'bismillah', 'kertas', 'unit', 2),
	('b10201', 'alat musik', 'brass', 'unit', 0);
/*!40000 ALTER TABLE `detailbarang` ENABLE KEYS */;

-- Dumping structure for table tokoanu.detailtransaksi
DROP TABLE IF EXISTS `detailtransaksi`;
CREATE TABLE IF NOT EXISTS `detailtransaksi` (
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

-- Dumping data for table tokoanu.detailtransaksi: ~0 rows (approximately)
/*!40000 ALTER TABLE `detailtransaksi` DISABLE KEYS */;
INSERT INTO `detailtransaksi` (`nojual`, `idbarang`, `jmlbarang`, `hrgsatuan`, `discount`, `totalharga`) VALUES
	(1, 'b10201', 1, 4250000, 0, 4250000),
	(2, 'a10201', 1, 1265000, 0, 1265000),
	(2, 'a10202', 2, 1100000, 100000, 2100000);
/*!40000 ALTER TABLE `detailtransaksi` ENABLE KEYS */;

-- Dumping structure for table tokoanu.diskon
DROP TABLE IF EXISTS `diskon`;
CREATE TABLE IF NOT EXISTS `diskon` (
  `iddiskon` mediumint(9) NOT NULL AUTO_INCREMENT,
  `idbarang` varchar(10) NOT NULL,
  `tglmulai` date NOT NULL,
  `tglselesai` date NOT NULL,
  `discount` int(11) NOT NULL,
  PRIMARY KEY (`iddiskon`),
  KEY `FK_diskon_barang` (`idbarang`),
  CONSTRAINT `FK_diskon_barang` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.diskon: ~3 rows (approximately)
/*!40000 ALTER TABLE `diskon` DISABLE KEYS */;
INSERT INTO `diskon` (`iddiskon`, `idbarang`, `tglmulai`, `tglselesai`, `discount`) VALUES
	(1, 'a10202', '2018-01-20', '2018-01-30', 100000),
	(2, 'a10201', '2018-02-01', '2018-02-07', 65000),
	(3, 'a10202', '2018-02-01', '2018-02-07', 50000);
/*!40000 ALTER TABLE `diskon` ENABLE KEYS */;

-- Dumping structure for table tokoanu.kategori
DROP TABLE IF EXISTS `kategori`;
CREATE TABLE IF NOT EXISTS `kategori` (
  `idkategori` int(2) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(50) DEFAULT NULL,
  `jenis` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.kategori: ~5 rows (approximately)
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`idkategori`, `kategori`, `jenis`) VALUES
	(1, 'elektrikal', 'koleksi'),
	(2, 'elektrikal', 'perkakas'),
	(3, 'mekanikal', 'alat musik'),
	(4, 'dekorasi', 'outdoor'),
	(5, 'dekorasi', 'indoor');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;

-- Dumping structure for table tokoanu.pegawai
DROP TABLE IF EXISTS `pegawai`;
CREATE TABLE IF NOT EXISTS `pegawai` (
  `idpegawai` varchar(5) NOT NULL,
  `namapegawai` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`idpegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.pegawai: ~2 rows (approximately)
/*!40000 ALTER TABLE `pegawai` DISABLE KEYS */;
INSERT INTO `pegawai` (`idpegawai`, `namapegawai`, `password`) VALUES
	('K01', 'Adwi Arifin', '5d41402abc4b2a76b9719d911017c592'),
	('O01', 'Afnan Fauzi', '5d41402abc4b2a76b9719d911017c592');
/*!40000 ALTER TABLE `pegawai` ENABLE KEYS */;

-- Dumping structure for table tokoanu.pembayaran
DROP TABLE IF EXISTS `pembayaran`;
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `idbayar` smallint(1) NOT NULL AUTO_INCREMENT,
  `jenisbayar` varchar(12) NOT NULL,
  PRIMARY KEY (`idbayar`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.pembayaran: ~3 rows (approximately)
/*!40000 ALTER TABLE `pembayaran` DISABLE KEYS */;
INSERT INTO `pembayaran` (`idbayar`, `jenisbayar`) VALUES
	(1, 'Cash'),
	(2, 'Transfer'),
	(3, 'Debit');
/*!40000 ALTER TABLE `pembayaran` ENABLE KEYS */;

-- Dumping structure for table tokoanu.transaksi
DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE IF NOT EXISTS `transaksi` (
  `nojual` int(11) NOT NULL AUTO_INCREMENT,
  `nota` varchar(10) NOT NULL,
  `tanggaljual` date NOT NULL,
  `totaljual` int(11) unsigned NOT NULL,
  `idpegawai` varchar(5) NOT NULL,
  `idbayar` smallint(1) NOT NULL,
  PRIMARY KEY (`nojual`),
  KEY `idpegawai` (`idpegawai`),
  KEY `idbayar` (`idbayar`),
  CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`idpegawai`) REFERENCES `pegawai` (`idpegawai`),
  CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`idbayar`) REFERENCES `pembayaran` (`idbayar`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table tokoanu.transaksi: ~0 rows (approximately)
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` (`nojual`, `nota`, `tanggaljual`, `totaljual`, `idpegawai`, `idbayar`) VALUES
	(1, '18010001', '2018-01-21', 4250000, 'O01', 2),
	(2, '18010002', '2018-01-21', 3365000, 'O01', 1);
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
