drop table product cascade;
drop table module cascade;
drop table item cascade;
drop table product_order cascade;
drop table config cascade;
drop table option cascade;

create table product
(
    id         integer generated always as identity primary key,
    name       varchar not null unique,
    base_price numeric not null
);

create table module
(
    id         integer generated always as identity primary key,
    product_id integer not null references product,
    name       varchar not null,
    constraint module_unique UNIQUE (product_id, name)
);

create table item
(
    id   integer generated always as identity primary key,
    name varchar not null unique
);

create table product_order
(
    id         integer generated always as identity primary key,
    product_id integer not null references product,
    value      decimal not null,
    quantity   integer not null default 1,
    order_date date    not null default current_date
);

create table config
(
    order_id  integer not null references product_order,
    module_id integer not null references module,
    item_id   integer not null references item,
    constraint config_pk PRIMARY KEY (order_id, module_id)
);

create table option
(
    module_id integer not null references module,
    item_id   integer not null references item,
    price     numeric not null,
    constraint option_pk PRIMARY KEY (module_id, item_id)
);

create function check_modules_configured() RETURNS TRIGGER AS
$limit_config$
DECLARE
    configs_total         int;
    product_modules_count int;
BEGIN
    configs_total := (SELECT COUNT(module_id) FROM config WHERE order_id = NEW.id);
    product_modules_count := (SELECT COUNT(m.id)
                              from product p
                                       left join module m on p.id = m.product_id
                              where p.id = NEW.product_id);
    if (configs_total <> product_modules_count) THEN
        RAISE EXCEPTION 'Invalid product order.';
    END IF;
    RETURN NEW;
END;
$limit_config$ LANGUAGE plpgsql;

CREATE CONSTRAINT TRIGGER limit_config
    AFTER INSERT OR UPDATE
    ON product_order
    DEFERRABLE
        INITIALLY DEFERRED
    FOR EACH ROW
EXECUTE PROCEDURE check_modules_configured();
