insert into users values (10, 'b@b.com', 'Sasha', 'Ogurov', '$2a$10$lBqCRZ5ctnfyP6zuRYRbMeaZGvmdDJ6D.9EW1fFL2i8LkbaP/x3ty', 'ROLE_USER');
-- insert into applications values (1, '2022-03-21', '2022-03-24', 'ECONOMY', 2);
-- insert into applications values (2, '2022-06-11', '2022-06-12', 'SUITE', 2);
insert into rooms values (1, 4, 100, 'ECONOMY', 'FREE');
insert into rooms values (2, 4, 100, 'ECONOMY', 'FREE');
insert into rooms values (3, 4, 200, 'STANDARD', 'FREE');
insert into rooms values (4, 4, 200, 'STANDARD', 'FREE');
insert into rooms values (5, 4, 300, 'JUNIOR_SUITE', 'FREE');
insert into rooms values (6, 4, 300, 'JUNIOR_SUITE', 'FREE');
insert into rooms values (7, 4, 400, 'SUITE', 'FREE');
insert into rooms values (8, 4, 400, 'SUITE', 'FREE');
insert into images values (1, 'room_economy_1_balcony.jpg', 'room_economy_1.jpg', 'room_economy_1_toilet.jpg', 1);
insert into images values (2, 'room_economy_2_balcony.jpg', 'room_economy_2.jpg', 'room_economy_2_toilet.jpg', 2);
insert into images values (3, 'room_standard_1_balcony.jpg', 'room_standard_1.jpg', 'room_standard_1_toilet.jpg', 3);
insert into images values (4, 'room_standard_2_balcony.jpg', 'room_standard_2.jpg', 'room_standard_2_toilet.jpg', 4);
insert into images values (5, 'room_junior_suite_1_balcony.jpg', 'room_junior_suite_1.jpg', 'room_junior_suite_1_toilet.jpg', 5);
insert into images values (6, 'room_junior_suite_2_balcony.jpg', 'room_junior_suite_2.jpg', 'room_junior_suite_2_toilet.jpg', 6);
insert into images values (7, 'room_suite_1_balcony.jpg', 'room_suite_1.jpg', 'room_suite_1_toilet.jpg', 7);
insert into images values (8, 'room_suite_2_balcony.jpg', 'room_suite_2.jpg', 'room_suite_2_toilet.jpg', 8);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (9, '2011-12-12', '2022-12-12', '2022-12-12', 300, 'NOT_PAID', 10, 1);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (10, '2020-12-12', '2022-11-12', '2022-12-12', 600, 'NOT_PAID', 10, 1);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (11, '2012-12-12', '2022-09-12', '2022-10-12', 400, 'NOT_PAID', 10, 2);
insert into orders (id, creation_date, first_date, last_date, price, status, client_id, room_id) values (12, '2012-01-01', '2022-01-02', '2022-01-01', 400, 'PAID', 10, 2);
-- insert into invoices values (1, 1, 9), (2, 1, 10), (3, 1, 11);
