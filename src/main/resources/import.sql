insert into users values (1, 'Sasha', 'Ogurov', '123', 'a@a.com', 1);
insert into rooms values (1,'room_economy_1.jpg', 4, 100,  'ECONOMY', 'FREE');
insert into rooms values (2, 'room_junior_suite_1.jpg', 4, 300, 'JUNIOR_SUITE', 'FREE');
insert into rooms values (3, 'room_standard_1.jpg', 4, 200, 'STANDARD', 'FREE');
insert into rooms values (4, 'room_economy_2.jpg', 4, 100, 'ECONOMY', 'FREE');
insert into rooms values (5, 'room_suite_1.jpg', 4, 400, 'SUITE', 'FREE');
insert into rooms values (6, 'room_junior_suite_2.jpg', 4, 300, 'JUNIOR_SUITE', 'FREE');
insert into rooms values (7, 'room_suite_2.jpg', 4, 400, 'SUITE', 'FREE');
insert into rooms values (8, 'room_standard_2.jpg', 4, 200, 'STANDARD', 'FREE');
insert into applications values (default, '2022-12-12', 13, 4, 1, 1, 1, null);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (1, '2011-12-12', '2022-12-12', '2022-12-12', 300, 'NOT_PAID', 1, 1);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (2, '2020-12-12', '2022-11-12', '2022-12-12', 600, 'NOT_PAID', 1, 1);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (3, '2012-12-12', '2022-09-12', '2022-10-12', 400, 'NOT_PAID', 1, 2);
insert into invoices values (1, 1, 1), (2, 1, 2), (3, 1, 3)