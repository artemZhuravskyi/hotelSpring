insert into users values (1, 'Sasha', 'Ogurov', '123', 'a@a.com', 1);
insert into rooms values (1, 4, 1, 1);
insert into rooms values (2, 4, 1, 1);
insert into applications values (default, '2022-12-12', 13, 4, 1, 1, 1, null);
insert into orders (id, creation_date, first_date, last_date, client_id, room_id) values (1, '2011-12-12', '2022-12-12', '2022-12-12', 1, 1);
insert into orders (id, creation_date, first_date, last_date, client_id, room_id) values (2, '2020-12-12', '2022-11-12', '2022-12-12', 1, 1);
insert into orders (id, creation_date, first_date, last_date, client_id, room_id) values (3, '2012-12-12', '2022-09-12', '2022-10-12', 1, 2);
insert into invoices values (1, 1, 1), (2, 1, 2), (3, 1, 3)