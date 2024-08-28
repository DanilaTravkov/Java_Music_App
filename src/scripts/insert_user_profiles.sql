truncate table profile;
truncate table "User";

insert into "User"(username) values('johndoe') returning id; --возвращаем id созданной записи

--id юзера используем чтобы создать запись профиля (у них связь one-to-one)
insert into profile(id, name, surname, date_of_birth, phone, email, gender, password)
values(1, 'john', 'doe', '01-01-0001', '+78805553535', 'johndoe@gmail.com', 'submarine', 'passw0rd');