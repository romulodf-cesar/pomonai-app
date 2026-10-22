alter table duvida add column dataHora datetime(6)
             not null default current_timestamp(6);