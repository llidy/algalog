create table occurrence(
	id bigint not null auto_increment primary key,
	delivery_id bigint not null,
	descricao text not null,
	data_registro datetime not null,


	constraint fk_occurrence foreign key (delivery_id) references delivery(id)

);