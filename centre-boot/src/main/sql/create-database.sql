create database centre;
create user centre@localhost identified by 'centre';
GRANT ALL ON centre.* TO centre@localhost;