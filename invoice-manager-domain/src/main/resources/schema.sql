create table invoice
(
   id integer not null,
   client_name varchar(255),
   vat_rate number(10,2),
   invoice_date date,
   primary key(id)
);