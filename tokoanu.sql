/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.1.38-MariaDB : Database - tokoanu
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
  `kategori` int(2) DEFAULT NULL,
  `merk` varchar(80) DEFAULT NULL,
  `namabarang` varchar(80) DEFAULT NULL,
  `hargabarang` int(11) DEFAULT '0',
  PRIMARY KEY (`idbarang`),
  KEY `kategori` (`kategori`),
  CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`idkategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `barang` */

insert  into `barang`(`idbarang`,`kategori`,`merk`,`namabarang`,`hargabarang`) values 
('a10201',4,'Converse','chuck taylor',250000),
('a10202',5,'Nike','airmax',200000),
('b10201',3,'Adidas','cloudfoam',520000),
('b10202',3,'Nike','tanjun',230000);

/*Table structure for table `detailbarang` */

DROP TABLE IF EXISTS `detailbarang`;

CREATE TABLE `detailbarang` (
  `idbarang` varchar(10) NOT NULL,
  `keterangan` text,
  `ukuran` int(3) NOT NULL DEFAULT '0',
  `jumlah` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idbarang`),
  CONSTRAINT `detailbarang_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `detailbarang` */

insert  into `detailbarang`(`idbarang`,`keterangan`,`ukuran`,`jumlah`) values 
('a10201','Grade Ori',37,7),
('a10202','Grade Ori',41,17),
('b10201','Grade Ori',40,10),
('b10202',NULL,40,12);

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

insert  into `detailtransaksi`(`nojual`,`idbarang`,`jmlbarang`,`hrgsatuan`,`discount`,`totalharga`) values 
(1,'b10201',1,4250000,0,4250000),
(2,'a10201',1,1265000,0,1265000),
(2,'a10202',2,1100000,100000,2100000),
(3,'b10201',1,4250000,0,4250000),
(8,'a10201',1,250000,0,250000),
(9,'a10201',2,250000,0,500000),
(10,'a10202',3,200000,0,600000);

/*Table structure for table `diskon` */

DROP TABLE IF EXISTS `diskon`;

CREATE TABLE `diskon` (
  `iddiskon` mediumint(9) NOT NULL AUTO_INCREMENT,
  `idbarang` varchar(10) NOT NULL,
  `tglmulai` date NOT NULL,
  `tglselesai` date NOT NULL,
  `discount` int(11) NOT NULL,
  PRIMARY KEY (`iddiskon`),
  KEY `FK_diskon_barang` (`idbarang`),
  CONSTRAINT `FK_diskon_barang` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `diskon` */

insert  into `diskon`(`iddiskon`,`idbarang`,`tglmulai`,`tglselesai`,`discount`) values 
(1,'a10202','2018-01-20','2018-01-30',100000),
(2,'a10201','2018-02-01','2018-02-07',65000),
(3,'a10202','2018-02-01','2018-02-07',50000);

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `idkategori` int(2) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(50) NOT NULL,
  `jenis` varchar(10) NOT NULL,
  PRIMARY KEY (`idkategori`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `kategori` */

insert  into `kategori`(`idkategori`,`kategori`,`jenis`) values 
(1,'Man','Casual'),
(2,'Man','Running'),
(3,'Man','Special'),
(4,'Woman','Casual'),
(5,'Woman','Running'),
(6,'Woman','Special');

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `idpegawai` varchar(5) NOT NULL,
  `namapegawai` varchar(100) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`idpegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pegawai` */

insert  into `pegawai`(`idpegawai`,`namapegawai`,`password`) values 
('K01','Adwi Arifin','5d41402abc4b2a76b9719d911017c592'),
('O01','Afnan Fauzi','5d41402abc4b2a76b9719d911017c592');

/*Table structure for table `pembayaran` */

DROP TABLE IF EXISTS `pembayaran`;

CREATE TABLE `pembayaran` (
  `idbayar` smallint(1) NOT NULL AUTO_INCREMENT,
  `jenisbayar` varchar(12) NOT NULL,
  PRIMARY KEY (`idbayar`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `pembayaran` */

insert  into `pembayaran`(`idbayar`,`jenisbayar`) values 
(1,'Cash'),
(2,'Transfer'),
(3,'Debit');

/*Table structure for table `restock` */

DROP TABLE IF EXISTS `restock`;

CREATE TABLE `restock` (
  `tanggal_masuk` date NOT NULL,
  `idbarang` varchar(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `ukuran` int(3) NOT NULL,
  KEY `idbarang` (`idbarang`),
  CONSTRAINT `restock_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `restock` */

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

insert  into `transaksi`(`nojual`,`nota`,`tanggaljual`,`totaljual`,`idpegawai`,`idbayar`) values 
(1,'18010001','2019-05-23',4250000,'O01',2),
(2,'18010002','2019-05-01',3365000,'O01',1),
(3,'18010003','2018-01-21',4250000,'O01',1),
(8,'19060004','2019-06-11',250000,'O01',1),
(9,'19070005','2019-07-19',500000,'O01',1),
(10,'19070006','2019-07-19',600000,'O01',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
