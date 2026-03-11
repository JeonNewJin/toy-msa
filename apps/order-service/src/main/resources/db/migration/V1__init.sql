CREATE TABLE orders
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    order_id     VARCHAR(255) NOT NULL,
    product_id   VARCHAR(255) NOT NULL,
    quantity     INT          NOT NULL,
    unit_price   INT          NOT NULL,
    total_amount INT          NOT NULL,
    user_id      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_orders_order_id (order_id)
);

CREATE TABLE outbox
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    event_id   VARCHAR(255) NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    payload    TEXT         NOT NULL,
    status     VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_outbox_event_id (event_id)
);
