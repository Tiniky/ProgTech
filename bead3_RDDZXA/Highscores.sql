create database Highscores;
create table Highscores.highscores (
timestamp timestamp,
name varchar(255),
difficulty varchar(255),
score int,
time_spent_ingame int
);