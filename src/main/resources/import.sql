--이런식으로 써두면 create 모드에서 자동으로 이 sql문들이 실행됨
--임시값들임.. 영문명(code) 정해지면 다시 작성할 것

insert into department(department_idx, name, code) values (1, '글로멀융합대학', 'global'),(2, '과학기술대학', 'science'),(3, 'Art&Design대학', 'art');

insert into major(major_idx, name, code, department_idx) values (1, '국어국문학전공', 'korean', 1), (2, '일어일문학전공', 'japanese', 1), (3, '중어중문학전공', 'chinese', 1), (4, '영어영문학전공', 'english', 1), (5, '불어불문학전공', 'french', 1), (6, '독어독문학전공', 'germany', 1), (7, '스페인어전공', 'spanish', 1), (8, '사학전공', 'history', 1), (9, '철학전공', 'philosophy', 1), (10, '미술사학전공', 'arthistory', 1);

insert into major(major_idx, name, code, department_idx) values (11, '문화인류학전공', 'humanity', 1), (12, '경영학전공', 'management', 1), (13, '회계학전공', 'accounting', 1), (14, '국제통상학전공', 'trade', 1), (15, '법학전공', 'law', 1), (16, '문헌정보학전공', 'library', 1), (17, '사회학전공', 'sociology', 1), (18, '심리학전공', 'psychology', 1), (19, '아동가족학전공', 'childfamily', 1), (20, '사회복지학전공', 'socialwelfare', 1);

insert into major(major_idx, name, code, department_idx) values (21, '정치외교학전공', 'political', 1), (22, '의상디자인전공', 'clothesdesign', 1), (23, '유아교육과', 'childeducation', 1);

insert into major(major_idx, name, code, department_idx) values (24, '컴퓨터공학전공', 'computer', 2), (25, 'IT미디어공학전공', 'itmedia', 2), (26, '사이버보안전공', 'security', 2), (27, '소프트웨어전공', 'software', 2), (28, '바이오공학전공', 'bio', 2), (29, '수학전공', 'math', 2), (30, '정보통계학전공', 'statistics', 2), (31, '화학전공', 'chemistry', 2), (32, '식품영양학전공', 'foodnutrition', 2), (33, '생활체육학전공', 'physical', 2);

insert into major(major_idx, name, code, department_idx) values (34, '동양화전공', 'oriental', 3), (35, '서양화전공', 'western', 3), (36, '실내디자인전공', 'interior', 3), (37, '시각디자인전공', 'visual', 3), (38, '텍스타일디자인전공', 'textile', 3);
