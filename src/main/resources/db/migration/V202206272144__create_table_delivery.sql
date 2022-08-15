create table delivery(
	id bigint not null auto_increment primary key,
	customer_id bigint not null,
	taxa decimal(10,2) not null,
	status varchar(20) not null,
	data_pedido datetime not null,
	data_finalizacao datetime,

	destinatario_nome varchar(60) not null,
	destinatario_endereco varchar(255) not null,
	destinatario_numero varchar(30) not null,
	destinatario_complemento varchar(60) not null,
	destinatario_bairro varchar(30) not null,

	constraint fk_delivery_customer foreign key (customer_id) references customer(id)

);