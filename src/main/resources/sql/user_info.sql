create table user_info
(
  id       int auto_increment
  comment '用户流水号',
  username varchar(15)  not null,
  password varchar(20)  not null,
  age      int          null
  comment '年龄',
  remark   varchar(100) null,
  constraint user_id_uindex
  unique (id)
)
  comment '用户表';

alter table user_info
  add primary key (id);

