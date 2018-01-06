/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.1.10-MariaDB : Database - tokoanu
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tokoanu` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tokoanu`;

/*Table structure for table `barang` */

DROP TABLE IF EXISTS `barang`;

CREATE TABLE `barang` (
  `idbarang` varchar(10) NOT NULL,
  `namabarang` varchar(200) DEFAULT NULL,
  `kategori` int(1) DEFAULT NULL,
  `hargabarang` int(11) DEFAULT '0',
  `jumlah` mediumint(9) DEFAULT '0',
  PRIMARY KEY (`idbarang`),
  KEY `kategori` (`kategori`),
  CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `barang` */

/*Table structure for table `detail barang` */

DROP TABLE IF EXISTS `detail barang`;

CREATE TABLE `detail barang` (
  `idbarang` varchar(10) NOT NULL,
  `keterangan` text,
  `bahan` text,
  `satuan` varchar(10) NOT NULL,
  `ukuran` int(11) NOT NULL,
  PRIMARY KEY (`idbarang`),
  CONSTRAINT `detail barang_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detail barang` */

/*Table structure for table `detailtransaksi` */

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

/*Data for the table `detailtransaksi` */

/*Table structure for table `discount` */

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

/*Data for the table `discount` */

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `kategori` int(1) NOT NULL,
  `jenis` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kategori` */

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `idpegawai` varchar(5) NOT NULL,
  `namapegawai` varchar(100) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`idpegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pegawai` */

/*Table structure for table `pembayaran` */

DROP TABLE IF EXISTS `pembayaran`;

CREATE TABLE `pembayaran` (
  `idbayar` smallint(1) NOT NULL AUTO_INCREMENT,
  `jenisbayar` varchar(12) NOT NULL,
  PRIMARY KEY (`idbayar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pembayaran` */

/*Table structure for table `transaksi` */

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

/*Data for the table `transaksi` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
