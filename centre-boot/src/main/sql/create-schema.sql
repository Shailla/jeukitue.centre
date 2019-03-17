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
drop table if exists EVENT_PARAMETER;
drop table if exists EVENT;


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
/* Table : EVENT                                              */
/*==============================================================*/
create table EVENT
(
   	EVT_ID					bigint not null auto_increment,
	EVT_HORODATAGE       	timestamp not null,
	EVT_TYPE             	varchar(50) not null,
	primary key (EVT_ID)
);

create table EVENT_PARAMETER
(
	EVT_ID					bigint not null,
	EVP_TYPE             	varchar(50) not null,
	EVP_VALUE             	varchar(50),
	primary key (EVT_ID, EVP_TYPE)
);

alter table EVENT_PARAMETER add constraint FK_PARAMETER_OF_EVENT foreign key (EVT_ID)
      references EVENT (EVT_ID) on delete restrict on update restrict;

/*==============================================================*/
/* Table : PROFILE                                              */
/*==============================================================*/
create table PROFILE
(
   PRF_NAME              varchar(20) not null,
   PRF_DESCRIPTION       varchar(100) not null,
   PRF_READONLY			 bool,
   primary key (PRF_NAME)
);

create table ROLE
(
	PRF_NAME             varchar(20) not null,   
	ROL_NAME             varchar(20) not null,
   primary key (PRF_NAME, ROL_NAME)
);

alter table ROLE add constraint FK_ROLE_OF_PROFILE foreign key (PRF_NAME)
      references PROFILE (PRF_NAME) on delete restrict on update restrict;

      
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

