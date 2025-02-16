CREATE TABLE Contact (
    id bigint PRIMARY KEY,
    address VARCHAR(255),
    email_address VARCHAR(255),
    image_url VARCHAR(255),
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    phone_num VARCHAR(255)
);

create sequence id_sequence start with 1 increment by 1;

--insert into Contact (id, address, first_name, last_name,email_address,image_url, phone_num ) VALUES (nextval('id_sequence'),'Cerdena 124','Jose Andres', 'Cisneros Chauviere','jose@gmail.com', 'http://image_url.com', '2222010844');