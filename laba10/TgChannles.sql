use Java_lab9_Users
create table TgChannels (
	id int primary key identity(1,1),
	channelName varchar(50),
	subscribersAmount bigint,
	userId int foreign key references InfoUsers(id)
	)


	insert into TgChannels values( 'telegram kanal valery', 69420, 2)

	select * from InfoUsers