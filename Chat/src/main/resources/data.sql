
INSERT INTO chatters (login,password)
VALUES
    ('Curran Hunt','secret'),
    ('Maite Johns','secret'),
    ('Allegra Mccormick','secret'),
    ('Josiah Carrillo','secret'),
    ('Margaret Pitts','secret');

insert into chatrooms(name, owner_id)
values
    ('admin', 1),
    ('random', 1),
    ('margaret_chatroom', 5),
    ('awesome_chatters', 2),
    ('general', 1);


INSERT INTO messages (author,room,text,created_at)
VALUES
    (4,4,'montes, nascetur ridiculus mus. Aenean eget magna. Suspendisse tristique',now()),
    (3,3,'lacus pede sagittis', now()),
    (3,2,'molestie in, tempus eu, ligula. Aenean euismod mauris', now()),
    (3,2,'Duis dignissim tempor arcu. Vestibulum ut eros', now()),
    (5,3,'cursus non, egestas a, dui. Cras',now()),
    (1,3,'bibendum ullamcorper. Dui ', now()),
    (5,4,'natoque penatibus et magnis dis parturient montes' ,now()),
    (2,1,'amet diam eu dolor egestas rhoncus.', now()),
    (5,2,'ligula eu enim. Etiam imperdiet',now()),
    (1,2,'tellus sem mollis dui, in sodales elit erat', now());

INSERT INTO messages (author,room,text,created_at)
VALUES
    (2,2,'Pellentesque tincidunt tempus risus. Donec egestas. Duis ac arcu.', now()),
    (4,3,'pharetra ut, pharetra sed, hendrerit a,', now()),
    (3,1,'cursus a, enim.', now()),
    (5,4,'sociis natoque penatibus et magnis dis parturient montes, nascetur', now()),
    (4,2,'adipiscing, enim mi tempor lorem, eget', now());
