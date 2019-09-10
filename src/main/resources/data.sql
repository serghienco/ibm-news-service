insert into employees(id, address, city, state, zip, first_name, last_name, job_role, department)
values (hibernate_sequence.nextval, 'Lily Way', 'San Jose', 'CA', 95000, 'Veaceslav', 'Serghienco', 'Senior Software Engineer', 'IT');

insert into employees(id, address, city, state, zip, first_name, last_name, job_role, department)
values (hibernate_sequence.nextval, '22nd', 'Seattle', 'WA', 95000, 'Nina', 'Cretu', 'Accounter', 'Financing');

insert into news(id, author, content, description, published_at, source, title, url, url_to_image)
values (hibernate_sequence.nextval, 'Fox News', 'Content of Fox news','Description of Fox news', parsedatetime('2019-09-09', 'yyyy-MM-dd'), 'source of news', 'title of fox news', 'http://www.x.com', 'http://www.image.com');

insert into news(id, author, content, description, published_at, source, title, url, url_to_image)
values (hibernate_sequence.nextval, 'BBS News', 'Content of BBS news','Description of BBS news', parsedatetime('2019-09-09', 'yyyy-MM-dd'), 'source of news', 'title of BBS news', 'http://www.x.com', 'http://www.image.com');

insert into employee_languages(id, language, employee_id)
values (hibernate_sequence.nextval, 'en', (select id from employees where last_name = 'Serghienco'));

insert into employee_languages(id, language, employee_id)
values (hibernate_sequence.nextval, 'ru', (select id from employees where last_name = 'Cretu'));

insert into recommended_news(id, news_id, employee_id, publish_date)
values (hibernate_sequence.nextval,
(select id from news where author = 'BBS News'),
(select id from employees where last_name = 'Serghienco'),
parsedatetime('2019-09-09', 'yyyy-MM-dd')
);

insert into recommended_news(id, news_id, employee_id, publish_date)
values (hibernate_sequence.nextval,
(select id from news where author = 'Fox News'),
(select id from employees where last_name = 'Serghienco'),
parsedatetime('2019-09-09', 'yyyy-MM-dd')
);

insert into recommended_news(id, news_id, employee_id, publish_date)
values (hibernate_sequence.nextval,
(select id from news where author = 'Fox News'),
(select id from employees where last_name = 'Cretu'),
parsedatetime('2019-09-09', 'yyyy-MM-dd')
);