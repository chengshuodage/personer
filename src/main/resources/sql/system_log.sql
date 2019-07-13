create table if not exists system_log
(
	id bigint auto_increment comment '日志流水号',
	app varchar(8) not null comment '如:"SYS001"',
	log_level char not null,
	client_ip char(16) not null,
	client_agent varchar(512) null,
	detail varchar(4096) not null,
	creator_id char(6) not null,
	c_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	constraint system_log_id_uindex
		unique (id)
)
comment '系统日志表'
;

alter table system_log
	add primary key (id)
;
