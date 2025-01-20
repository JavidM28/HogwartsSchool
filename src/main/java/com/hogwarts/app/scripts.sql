-- Students with age between 10 and 20
SELECT * FROM hogwarts WHERE age BETWEEN 10 AND 20;

-- Display names of all students
SELECT name FROM hogwarts;

-- Students with 'O' in their name
SELECT * FROM hogwarts WHERE name LIKE '%O%';

-- Students with age less than their ID
SELECT * FROM hogwarts WHERE age < id;

-- Students ordered by age
SELECT * FROM hogwarts ORDER BY age;
