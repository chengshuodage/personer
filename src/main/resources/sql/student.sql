create table student
(
  id    int auto_increment
  comment '学生流水号',
  name  varchar(10)                         null
  comment '学生姓名',
  cTime timestamp default CURRENT_TIMESTAMP not null
  comment '创建时间',
  constraint student_id_uindex
  unique (id)
);

alter table student
  add primary key (id);