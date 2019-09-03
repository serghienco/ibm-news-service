insert into employees(id, address, city, state, zip, first_name, last_name, job_role, department)
values (hibernate_sequence.nextval, 'address', 'city', 'CA', 95000, 'first', 'last', 'role', 'department');


insert into news(id, author, content, description, published_at, source, title, url, url_to_image)
values (hibernate_sequence.nextval, 'Slavic', 'some content','some description', SYSDATE, 'source of news', 'title of news', 'http://www.x.com', 'http://www.image.com');

