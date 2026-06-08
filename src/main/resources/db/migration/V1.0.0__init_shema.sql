CREATE TABLE IF NOT EXISTS users (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    email varchar(256) unique NOT NULL ,
    password_hash varchar(255) NOT NULL ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE INDEX email_index_on_users ON users(email);

CREATE TABLE IF NOT EXISTS profiles (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    user_id bigint REFERENCES users(id) ON DELETE CASCADE NOT NULL UNIQUE ,
    username varchar(63) NOT NULL UNIQUE,
    first_name varchar(63) NOT NULL DEFAULT 'No Name' ,
    second_name varchar(63),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE INDEX user_id_index_on_profiles ON profiles(user_id);

CREATE TABLE roles (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(63) UNIQUE NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE permissions (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar(63) UNIQUE NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version INT NOT NULL DEFAULT 0 ,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE roles_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0 ,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name varchar(63) UNIQUE NOT NULL ,
    description text ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS marketplaces (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name varchar(63) NOT NULL UNIQUE ,
    url varchar(256) NOT NULL UNIQUE ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS products (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    category_id bigint REFERENCES categories(id) NOT NULL ,
    name varchar(63) UNIQUE NOT NULL ,
    status varchar(63) NOT NULL DEFAULT 'DRAFT' CHECK ( status in ('DRAFT', 'ACTIVE', 'OUT_OF_STOCK', 'HIDDEN', 'DISCOUNTED', 'ARCHIVED', 'ON_MARKETPLACE' )),
    price decimal(12, 2) NOT NULL DEFAULT 0 CHECK ( price >= 0 ) ,
    quantity integer NOT NULL DEFAULT 0 check ( quantity >= 0 ) ,
    marketplace_id bigint REFERENCES marketplaces(id) ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE INDEX status_index_on_products ON products(status);
CREATE INDEX quantity_index_on_products ON products(quantity);

CREATE TABLE IF NOT EXISTS contacts (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    type varchar(63) NOT NULL ,
    value varchar(255) NOT NULL ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS suppliers (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    contact_id bigint REFERENCES contacts(id) NOT NULL UNIQUE ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS purchases (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    product_id bigint REFERENCES products(id) NOT NULL ,
    supplier_id bigint REFERENCES suppliers(id) NOT NULL  ,
    quantity integer NOT NULL DEFAULT 1 check ( quantity >= 1 ) ,
    buy_price decimal(12, 2) NOT NULL DEFAULT 0 check ( buy_price >= 0 ) ,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version INT NOT NULL DEFAULT 0
);

CREATE INDEX product_id_index_on_purchases ON purchases(product_id);
CREATE INDEX supplier_id_index_on_purchases ON purchases(supplier_id);
CREATE INDEX created_at_on_purchases ON purchases(created_at);

CREATE TABLE IF NOT EXISTS sales (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    user_id bigint REFERENCES users(id) NOT NULL ,
    product_id bigint REFERENCES products(id) NOT NULL ,
    quantity integer NOT NULL DEFAULT 1 CHECK ( quantity >=1 ) ,
    sell_price decimal(12, 2) NOT NULL DEFAULT 0 CHECK ( sell_price >= 0 ),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    version INT NOT NULL DEFAULT 0
);

CREATE INDEX user_id_index_on_sales ON sales(user_id);
CREATE INDEX product_id_index_on_sales ON sales(product_id);
CREATE INDEX created_at_on_sales ON sales(created_at);

