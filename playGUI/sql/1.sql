use app;
create table schedule(
                         id int auto_increment primary key,
                         day_num char(8),
                         day_content varchar(100)
);

insert into schedule values(null, '20230504', 'test');

select * from schedule