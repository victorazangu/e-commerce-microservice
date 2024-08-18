-- Create the categories table if it doesn't exist
CREATE TABLE IF NOT EXISTS categories (
                                          id SERIAL PRIMARY KEY,
                                          description VARCHAR(255),
    name VARCHAR(255)
    );

-- Create the products table if it doesn't exist
CREATE TABLE IF NOT EXISTS products (
                                        id SERIAL PRIMARY KEY,
                                        description VARCHAR(255),
    name VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price NUMERIC(38, 2),
    category_id INTEGER REFERENCES categories(id)
    );

-- Create sequences if they don't exist
CREATE SEQUENCE IF NOT EXISTS categories_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS products_seq INCREMENT BY 50;
