SELECT
    s.name AS student_name,
    s.age AS student_age,
    f.name AS faculty_name
FROM
    Student s
        LEFT JOIN
    Faculty f
    ON
        s.faculty_id = f.id;
SELECT
    s.name AS student_name,
    s.age AS student_age,
    a.url AS avatar_url
FROM
    Student s
        INNER JOIN
    Avatar a
    ON
        s.id = a.student_id;
