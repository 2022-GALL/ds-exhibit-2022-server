--이런식으로 써두면 create 모드에서 자동으로 이 sql문들이 실행됨
--임시값들임.. 영문명(code) 정해지면 다시 작성할 것

insert into department(department_idx, name, code) values (1, '글로멀융합대학', 'global'),(2, '과학기술대학', 'science'),(3, 'Art&Design대학', 'art');

insert into major(major_idx, name, code, department_idx) values (1, '의상디자인전공', 'fashion', 1), (2, '컴퓨터공학전공', 'computer', 2);

