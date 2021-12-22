drop table product cascade;
drop table recipe cascade;
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
    id   integer generated always as identity primary key,
    name varchar not null
);

create table item
(
    id   integer generated always as identity primary key,
    name varchar not null,
    constraint item_unique UNIQUE (name)
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
     id         integer generated always as identity primary key,
    order_id  integer not null references product_order,
    module_id integer not null references module,
    item_id   integer not null references item,
    constraint config_pk UNIQUE (order_id, module_id)
);

create table option
(
    id        integer generated always as identity primary key,
    module_id integer not null references module,
    item_id   integer not null references item,
    price     numeric not null,
    count     integer not null,
    constraint option_fk UNIQUE (module_id, item_id)
);

create table recipe
(
    product_id integer not null references product,
    module_id  integer not null references module,
    constraint recipe_pk PRIMARY KEY (product_id, module_id)
);

INSERT INTO product
values (default, 'Iphone 7 Plus', 999);
INSERT INTO product
values (default, 'Iphone 10 Plus', 1100);
INSERT INTO product
values (default, 'Iphone 10 MAX PRO', 1300);
INSERT INTO product
values (default, 'Iphone 6S', 599);

INSERT INTO module
values (default, 'Storage');
INSERT INTO module
values (default, 'Camera');

INSERT INTO item
values (default, 'Storage 512 GB');
INSERT INTO item
values (default, 'Storage 256 GB');
INSERT INTO item
values (default, 'Front Camera 16 Mpx');
INSERT INTO item
values (default, 'Back Camera 32 Mpx');

select *
from product;
select *
from module;
select *
from item;

INSERT INTO option
values (default, 1, 1, 0, 1);
INSERT INTO option
values (default, 1, 2, 299, 1);

INSERT INTO option
values (default, 2, 3, 0, 1);
INSERT INTO option
values (default, 2, 4, 0, 1);

INSERT INTO recipe
values (1, 1);
INSERT INTO recipe
values (1, 2);

INSERT INTO product_order
values (default, 1, 1000, default, default);
INSERT INTO product_order
values (default, 1, 1000, 3, default);

select *
from product_order;

INSERT INTO config
values (default, 1, 1, 2);
INSERT INTO config
values (default, 1, 2, 4);

SELECT *
from config;

SELECT i.*
FROM ITEM i
         JOIN CONFIG c ON i.id = c.item_id
WHERE c.order_id = 1
  AND c.module_id = 1;

select * from product_order;

delete from product_order where id = 13

