
insert into USER (USR_ID, USR_LOGIN, USR_PASSWORD_HASH, USR_MAIL, USR_ENABLED) VALUES (1, "admin", "EdrfC3UsdUrCcGsptBSwA+NO4hkorUa8XwRfaYXzd44=", "ahuut@yahoo.fr", 1);		-- MDP "admin'
insert into USER (USR_ID, USR_LOGIN, USR_PASSWORD_HASH, USR_MAIL, USR_ENABLED) VALUES (2, "user", "Y9pXS4RuvhhzKj6L/iorXPRX2KVXLErIXdFSzAVmcWw=", "jc_vogel@yahoo.fr", 1);	-- MDP "user'

insert into PROFILE (PRF_NAME, PRF_DESCRIPTION) VALUES ("Administrateur", 	"Ensemble des droits d'un administrateur", 0);
insert into PROFILE (PRF_NAME, PRF_DESCRIPTION) VALUES ("Utilisateur", 		"Ensemble des droits d'un utilisateur", 0);

insert into ROLE (PRF_NAME, ROL_NAME) VALUES ("Administrateur",		"ROLE_ADMIN");
insert into ROLE (PRF_NAME, ROL_NAME) VALUES ("Administrateur",		"ROLE_USER");
insert into ROLE (PRF_NAME, ROL_NAME) VALUES ("Utilisateur",		"ROLE_USER");

insert into PROFILE_OF_USER (USR_ID, PRF_NAME) VALUES (1, 	"Administrateur");