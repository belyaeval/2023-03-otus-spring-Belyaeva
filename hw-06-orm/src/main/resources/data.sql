insert into author (name) values ('Пушкин'),
                                 ('Лермонтов');

insert into genre (name) values ('повесть'),
                                ('роман'),
                                ('поэма');

insert into book (name, author_id, genre_id) values ('Евгений Онегин', 1, 2),
                                                    ('Метель', 1, 3),
                                                    ('Мцыри', 2, 1);

insert into book_comment (text, book_id) values ('отличная', 1),
                                                ('хорошая', 2),
                                                ('скучная', 2),
                                                ('интересная', 3);


