insert into users (email, password, active, activation_code)
values ('abc@abc.abc', '123', true, null );

insert into roles (user_id, roles)
values (1,'USER');