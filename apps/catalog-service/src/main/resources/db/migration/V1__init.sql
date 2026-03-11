CREATE TABLE catalog
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    product_id     VARCHAR(255) NOT NULL,
    product_name   VARCHAR(255) NOT NULL,
    stock          INT          NOT NULL,
    reserved_stock INT          NOT NULL,
    unit_price     INT          NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_catalog_product_id (product_id)
);
