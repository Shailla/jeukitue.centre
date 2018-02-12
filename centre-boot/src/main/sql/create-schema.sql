/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  05/09/2008 20:32:42                      */
/*==============================================================*/


drop table if exists DOWNLOAD;
drop table if exists ROLE_OF_PROFILE;
drop table if exists PROFILE_OF_USER;
drop table if exists NEWS;
drop table if exists role;
drop table if exists PROFILE;
drop table if exists USER;


/*==============================================================*/
/* Table : DOWNLOAD                                             */
/*==============================================================*/
create table DOWNLOAD
(
   DWL_ID               bigint not null auto_increment,
   DWL_NAME             varchar(50) not null,
   DWL_FILE             varchar(50) not null,
   DWL_DESCRIPTION      varchar(1000) not null,
   DWL_CATEGORY         varchar(50) not null,
   DWL_SIZE             bigint not null,
   DWL_VERSION          varchar(10) not null,
   DWL_COMPATIBILES     varchar(100) not null,
   DWL_TYPE_MIME        varchar(50) not null,
   DWL_CONTENT_FILE     blob not null,
   primary key (DWL_ID)
);


/*==============================================================*/
/* Table : NEWS                                                 */
/*==============================================================*/
create table NEWS
(
   NEW_ID               bigint not null auto_increment,
   NEW_HORODATAGE       datetime not null,
   NEW_TEXT             text not null,
   primary key (NEW_ID)
);


/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE
(
   ROL_NAME              varchar(20) not null,
   ROL_DESCRIPTION       varchar(100) not null,
   primary key (ROL_NAME)
);


/*==============================================================*/
/* Table : PROFILE                                              */
/*==============================================================*/
create table PROFILE
(
   PRF_NAME              varchar(20) not null,
   PRF_DESCRIPTION       varchar(100) not null,
   primary key (PRF_NAME)
);

create table ROLE_OF_PROFILE
(
	PRF_NAME             varchar(20) not null,   
	ROL_NAME             varchar(20) not null,
   primary key (PRF_NAME, ROL_NAME)
);

alter table ROLE_OF_PROFILE add constraint FK_ROLE_OF_PROFILE__PROFILE foreign key (PRF_NAME)
      references PROFILE (PRF_NAME) on delete restrict on update restrict;
      
alter table ROLE_OF_PROFILE add constraint FK_ROLE_OF_PROFILE__ROLE foreign key (ROL_NAME)
      references ROLE (ROL_NAME) on delete restrict on update restrict;

      
/*==============================================================*/
/* Table : USER                                                 */
/*==============================================================*/
create table USER
(
   USR_ID               bigint not null auto_increment,   
   USR_LOGIN            varchar(20) not null,
   USR_PASSWORD_HASH    varchar(300) not null,
   USR_MAIL             varchar(50) not null,
   USR_ENABLED          tinyint(1) not null,
   USR_UUID_REGISTRATION varchar(38),
   primary key (USR_ID)
);

create table PROFILE_OF_USER
(
	PRF_NAME             varchar(20) not null,   
	USR_ID               bigint not null,
   primary key (PRF_NAME, USR_ID)
);

alter table PROFILE_OF_USER add constraint FK_PROFILE_OF_USER__PROFILE foreign key (PRF_NAME)
      references PROFILE (PRF_NAME) on delete restrict on update restrict;
      
alter table PROFILE_OF_USER add constraint FK_PROFILE_OF_USER__USER foreign key (USR_ID)
      references USER (USR_ID) on delete restrict on update restrict;

