

/* USERS */

INSERT INTO T_USER(C_USER_ID, C_USER_NAME)
VALUES
(1, 'Admin'),
(2, 'User');

INSERT INTO T_USER_SECURITY(C_USER_ID, C_USER_LOGIN, C_USER_PASSWORD, C_USER_AUTH_STRING, C_USER_ROLE)
VALUES
(1, 'admin', '$2a$10$oNXFlhGI0LekTBIedkILTeBYcDCT11Mb4NmIO.p5WH.6isYLLs9uC', 'ABC1111111', 'ADMIN'), /*C_USER_PASSWORD = adminpss*/
(2, 'user',  '$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC' , '2222222XYX', 'USER'); /*C_USER_PASSWORD = userpss*/


/* SCHEDULER TASKS */

INSERT INTO T_SCH_TASK(C_SCH_TASK_ID, C_USER_ID, C_SCH_TASK_NAME, C_SCH_TASK_DESCR)
VALUES
(1, 1, 'NBA import', 'NBA import scheduler task'),
(2, 1, 'UEFA import', 'UEFA import scheduler task'),
(3, 2, 'NHL import', 'NHL import scheduler task');







