-- 创建 用户角色表--
create table T_USER_ROLE
(
  ID   NUMBER not null,
  R_ID NUMBER,
  U_ID NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_USER_ROLE
  is '用户角色表';
-- Add comments to the columns 
comment on column T_USER_ROLE.ID
  is '主键';
comment on column T_USER_ROLE.R_ID
  is '角色ID';
comment on column T_USER_ROLE.U_ID
  is '用户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_USER_ROLE
  add constraint K_RU primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table T_USER_ROLE
  add constraint FK_RU foreign key (R_ID)
  references T_ROLE (R_ID);
alter table T_USER_ROLE
  add constraint FK_U foreign key (U_ID)
  references T_USER (ID);
----------------------------------------------------------
--创建用户表--
create table T_USER
(
  ID            NUMBER not null,
  T_NAME        VARCHAR2(50),
  T_ADDRESS     VARCHAR2(50),
  T_EMAIL       VARCHAR2(50),
  T_PASSWORD    VARCHAR2(50),
  T_LOGIN_NAME  VARCHAR2(50),
  T_CREATE_DATE DATE default SYSDATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_USER
  is '用户表';
-- Add comments to the columns 
comment on column T_USER.ID
  is '主键';
comment on column T_USER.T_NAME
  is '用户名';
comment on column T_USER.T_ADDRESS
  is '地址';
comment on column T_USER.T_EMAIL
  is '邮件';
comment on column T_USER.T_PASSWORD
  is '密码';
comment on column T_USER.T_LOGIN_NAME
  is '登录名';
comment on column T_USER.T_CREATE_DATE
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_USER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-----------------------------------------------------------
-- 创建角色模块表--
create table T_ROLE_MODULE
(
  ID   NUMBER not null,
  R_ID NUMBER,
  M_ID NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_ROLE_MODULE
  is '角色权限表';
-- Add comments to the columns 
comment on column T_ROLE_MODULE.ID
  is '主键';
comment on column T_ROLE_MODULE.R_ID
  is '角色ID';
comment on column T_ROLE_MODULE.M_ID
  is '模块ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_ROLE_MODULE
  add constraint P_RM primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table T_ROLE_MODULE
  add constraint FK_MODULE foreign key (M_ID)
  references T_MODULE (T_ID);
alter table T_ROLE_MODULE
  add constraint FK_ROLE foreign key (R_ID)
  references T_ROLE (R_ID);
------------------------------------------------------------
-- 创建角色表--
create table T_ROLE
(
  R_ID        NUMBER not null,
  R_NAME      VARCHAR2(50),
  CREATE_DATA DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_ROLE
  is '角色表';
-- Add comments to the columns 
comment on column T_ROLE.R_ID
  is '主键';
comment on column T_ROLE.R_NAME
  is '角色名';
comment on column T_ROLE.CREATE_DATA
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_ROLE
  add primary key (R_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
---------------------------------------------------------------------
--创建模块表--
create table T_MODULE
(
  T_ID          NUMBER not null,
  T_MODULE_NAME VARCHAR2(50),
  CREATE_DATE   DATE,
  PARENT_ID     NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_MODULE
  is '模块表';
-- Add comments to the columns 
comment on column T_MODULE.T_MODULE_NAME
  is '模块名';
comment on column T_MODULE.CREATE_DATE
  is '创建时间';
comment on column T_MODULE.PARENT_ID
  is '父节点ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_MODULE
  add primary key (T_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
