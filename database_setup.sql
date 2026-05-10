
create table stations(
                         id serial primary key,
                         name varchar(100) not null unique
);

create table routes(
                       id serial primary key,
                       name varchar(100) not null
);

create table route_stations(
                               route_id integer references routes(id) on delete cascade,
                               station_id integer references stations(id) on delete cascade,
                               stop_order integer not null,
                               arrival_time time,
                               departure_time time,
                               primary key (route_id, station_id),
                               constraint check_stop_order check (stop_order > 0)
);

create table trains(
                       id serial primary key,
                       name varchar(50) not null unique,
                       route_id integer references routes(id) on delete set null,
                       total_seats integer not null check (total_seats > 0),
                       delay_minutes integer default 0 check (delay_minutes >= 0)
);

create table bookings(
                         id serial primary key,
                         train_id integer references trains(id) on delete cascade,
                         customer_email varchar(150) not null,
                         seats_booked integer not null check (seats_booked > 0),
                         booking_date timestamp default current_timestamp
);

create table admins(
                       id serial primary key,
                       username varchar(50) not null unique,
                       password varchar(255) not null
);

insert into stations (id, name) values (1, 'Baia Mare'), (2, 'Cluj-Napoca'), (3, 'Bucuresti Nord'), (4, 'Brasov');

insert into routes (id, name) values (1, 'Baia Mare - Cluj'), (2, 'Cluj - Bucuresti'), (3, 'Bucuresti - Brasov');

insert into trains (id, name, route_id, total_seats, delay_minutes) values
                                                                        (101, 'IR 1544', 1, 10, 0),
                                                                        (202, 'IR 1745', 2, 50, 0),
                                                                        (303, 'R 3001', 3, 100, 0);

insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (1, 1, 1, '00:00:00', '08:10:00');
insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (1, 2, 2, '12:00:00', '12:10:00');

insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (2, 2, 1, '13:00:00', '13:15:00');
insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (2, 3, 2, '19:00:00', '19:10:00');

insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (3, 3, 1, '20:00:00', '20:05:00');
insert into route_stations (route_id, station_id, stop_order, arrival_time, departure_time) values (3, 4, 2, '22:30:00', '22:40:00');

insert into admins (username, password) values ('admin', 'admin'), ('client', '1234');

select setval('routes_id_seq', (select MAX(id) from routes));

select setval('stations_id_seq', (select MAX(id) from stations));