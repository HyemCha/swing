insert into user values(null, 'chm6194@gmail.com', 'hyemin', 'hymin1234!');
insert into diary values(null, 1, 'first diary post', 'content', null, null, null);

insert into user values(null, 'aaa@gmail.com', 'yunjin', 'yunjin1234!');
insert into diary values(null, 2, '2nd diary post', 'content', null, null, null);

alter table guest_book add host_id int;
alter table guest_book ADD foreign key(host_id) references user (id) ON DELETE cascade;
alter table guest_book ADD foreign key(user_id) references user (id) ON DELETE cascade;

insert into guest_book values(null, 1, "2hi hyemin i'm jennifer!", current_timestamp, null, 2);

select * from guest_book