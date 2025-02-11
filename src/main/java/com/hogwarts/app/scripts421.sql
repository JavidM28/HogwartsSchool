ALTER TABLE Student
    ADD CONSTRAINT chk_student_age CHECK (age >= 16),
ADD CONSTRAINT uq_student_name UNIQUE (name),
ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE Faculty
    ADD CONSTRAINT uq_faculty_name_color UNIQUE (name, color);
