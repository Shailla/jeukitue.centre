
insert into USER (USR_ID, USR_LOGIN, USR_PASSWORD_HASH, USR_MAIL, USR_ENABLED) VALUES (1, "admin", "EdrfC3UsdUrCcGsptBSwA+NO4hkorUa8XwRfaYXzd44=", "ahuut@yahoo.fr", 1);		-- MDP "admin'
insert into USER (USR_ID, USR_LOGIN, USR_PASSWORD_HASH, USR_MAIL, USR_ENABLED) VALUES (2, "user", "Y9pXS4RuvhhzKj6L/iorXPRX2KVXLErIXdFSzAVmcWw=", "jc_vogel@yahoo.fr", 1);	-- MDP "user'

insert into ROLE (ROL_NAME, ROL_DESCRIPTION) VALUES ("ROLE_ADMIN", "Droit d'administrer");
insert into ROLE (ROL_NAME, ROL_DESCRIPTION) VALUES ("ROLE_USER", "Droit d'utiliser comme un utilisateur classique");

insert into PROFILE (PRF_NAME, PRF_DESCRIPTION) VALUES ("Administrateur", "Ensemble des droits d'un administrateur");
insert into PROFILE (PRF_NAME, PRF_DESCRIPTION) VALUES ("Utilisateur", "Ensemble des droits d'un utilisateur");

insert into ROLE_OF_PROFILE (ROL_NAME, PRF_NAME) VALUES ("ROLE_ADMIN", "Administrateur");
insert into ROLE_OF_PROFILE (ROL_NAME, PRF_NAME) VALUES ("ROLE_ADMIN", "Utilisateur");

insert into PROFILE_OF_USER (USR_ID, PRF_NAME) VALUES (1, "Administrateur");