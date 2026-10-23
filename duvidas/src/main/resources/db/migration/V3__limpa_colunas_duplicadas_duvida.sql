-- aproveita o horário correto (Brasília) que está em data_hora
update duvida
   set dataHora = data_hora
 where data_hora is not null;

alter table duvida drop column data_hora;
alter table duvida drop column `datahora,nullable=false`;