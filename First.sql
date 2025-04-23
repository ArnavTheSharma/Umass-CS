use sakila;

select f.title, a.film_id
from film f, film_actor a
where f.film_id = a.film_id
and a.film_id > 50 and a.film_id < 60
;

select f.title, a.film_id
from film f
join film_actor a on f.film
where a.film_id > 50 and a.film_id < 60
;



select f.title, f.film_id
from film f join film_actor a
on (a.film_id = f.film_id) and a.film_id > 50 and a.film_id < 60
;


select Count(*) from film f
where f.film_id > 500;


select f.release_year from film f
where f.film_id > 500;


select *
from film_category c join film_actor a join film f
where f.film_id = a.film_id and f.film_id = c.film_id and c.category_id > 15;


select *
from film_category c join film_actor a join film f
where f.film_id = a.film_id and f.film_id = c.film_id and c.category_id > 15;


# Cartesian Product AKA Cross Join (is basically set multiplication)
select * from film_category, film_actor