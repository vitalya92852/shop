create table categories
(
    id   serial8,
    name varchar not null,
    primary key (id)
);

create table products
(
    id          serial8,
    category_id int8    not null,
    name        varchar not null,
    price       int4    not null,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table options
(
    id          serial8,
    category_id int8 not null,
    name        varchar,
    primary key (id),
    foreign key (category_id) references categories (id)
);

create table values
(
    id         serial8,
    product_id int8 not null,
    option_id  int8 not null,
    name       varchar,
    primary key (id),
    foreign key (product_id) references products (id),
    foreign key (option_id) references options (id)
);

insert into categories (name)
values ('Процессоры'),
       ('Мониторы');

insert into products (category_id, name, price)
values (1, 'Intel Core I9 9900', 250000),
       (1, 'AMD Ryzen R7 7700', 270000),
       (2, 'Samsung SU556270', 150000),
       (2, 'AOC Z215S659', 180000);

insert into options (category_id, name)
values (1, 'Производитель'),
       (1, 'Количество ядер'),
       (1, 'Сокет'),
       (2, 'Производитель'),
       (2, 'Диагональ'),
       (2, 'Матрица'),
       (2, 'Разрешение');

insert into values (option_id, product_id, name)
values (1, 1, 'Intel'),
       (1, 2, 'AMD'),
       (2, 1, '8'),
       (2, 2, '12'),
       (3, 1, '1250'),
       (3, 2, 'AM4'),
       (4, 3, 'Samsung'),
       (4, 4, 'AOC'),
       (5, 3, '27'),
       (5, 4, '21.5'),
       (6, 3, 'TN'),
       (6, 4, 'AH-IPS'),
       (7, 3, '2560*1440'),
       (7, 4, '1920*1080');

create table users(
                      id serial8                not null ,
                      role_id int8              not null ,
                      login varchar             not null ,
                      password varchar          not null ,
                      name varchar              not null ,
                      lastname varchar          not null ,
                      date_registration date    not null ,
                      primary key (id)
);

insert into users (role_id, login, password, name, lastname, date_registration)
values (1,'admin123','admin123','Alex','Smith','14-04-2002');

insert into users (role_id, login, password, name, lastname, date_registration)
values (2,'user123','user123','Bob','Kolobok','22-01-2022');

create table status(
                       id serial8      not null ,
                       name varchar    not null ,
                       primary key (id)
);

insert into status(name)
values ('Доставляется');

create table orders(
                       id serial8      not null ,
                       user_id int8    not null ,
                       status_id int8  not null ,
                       address varchar not null ,
                       date timestamp not null ,
                       primary key (id),

                       foreign key (user_id) references users (id),
                       foreign key (status_id) references status (id)

);

-- drop table if exists orders cascade;

create table order_product(
    id serial8      not null ,
    product_id int8 not null ,
    order_id int8   not null ,
    count int8      not null ,

    primary key (id),

    foreign key (product_id) references products(id),
    foreign key (order_id) references  orders(id)
);

insert into orders (user_id,  status_id, address, date)
values (2,1,'Пушкина 8/1','10-04-2023 21:15:45');

create table review(
                       id serial8          not null ,
                       user_id int8        not null ,
                       product_id int8     not null ,
                       rating int4         not null ,
                       text varchar        not null ,
                       date date      not null ,
                       publication bool    not null ,
                       primary key (id),
                       foreign key (user_id) references users (id),
                       foreign key (product_id) references products (id)
);

-- drop table if exists review cascade ;

insert into review(user_id, product_id, rating, text, date, publication)
values (2,3,3,'Хороший товар','11-04-2022',true);

create table cart(
    id serial8 not null,
    user_id int8 not null,
    product_id int8 not null,
    count int8 not null ,
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (product_id) references products(id)
)