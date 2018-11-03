create table PRODUCTS (
	P_BASEPRODUCT BIGINT(20) PRIMARY KEY,
    P_VENUE VARCHAR(100) NOT NULL,
    P_DATE DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

GRANT ALL PRIVILEGES ON mystore.products  TO 'user'@'localhost';

INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('TMV',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('Ocr Global',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris Munich, Germany',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris Montreal, Canada',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris Boulder, USA',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris London, UK',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris Gliwice, Poland',now());
INSERT INTO PRODUCTS (P_VENUE,P_DATE) values ('hybris Boston, USA',now());
