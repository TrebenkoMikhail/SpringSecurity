create table users (
    id                  bigserial,
    username            varchar(30) not null unique,
    password            varchar(80) not null,
    email               varchar(50) unique,
    primary key (id)
);

create table roles (
    id                  serial,
    name                varchar(50) not null,
    primary key (id)
);
CREATE TABLE users_roles (
    user_id             bigint not null,
    role_id             int not null,
    primary key (user_id, role_id),
    foreign key (user_id) reference users (id),
    foreign key (role_id) reference roles (id)
);
CREATE TABLE products (
    products-id         serial,
    products_title      int not null,
    products_price      int not null
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPER-ADMIN'), ("create-user");

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1);

insert into products (products_title, products_price)
values
('title', 'price');