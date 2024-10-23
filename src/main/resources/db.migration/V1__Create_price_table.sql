CREATE TABLE IF NOT EXISTS prices (
                                      id BIGINT PRIMARY KEY,
                                      brand_id BIGINT NOT NULL,
                                      start_date TIMESTAMP NOT NULL,
                                      end_date TIMESTAMP NOT NULL,
                                      price_list INTEGER NOT NULL,
                                      product_id BIGINT NOT NULL,
                                      priority INTEGER NOT NULL,
                                      price DECIMAL(10, 2) NOT NULL,
                                      curr VARCHAR(3) NOT NULL
);
