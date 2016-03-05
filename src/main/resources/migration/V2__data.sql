

/* USERS */

INSERT INTO T_USER(C_USER_ID, C_USER_NAME)
VALUES
(1, 'Admin'),
(2, 'Stephen Curry'),
(3, 'Lionel Messi'),
(4, 'Reggie White'),
(5, 'Sidney Crosby')
;

INSERT INTO T_USER_SECURITY(T_USER_SECURITY_ID, C_USER_ID, C_USER_LOGIN, C_USER_PASSWORD, C_USER_ROLE)
VALUES
(1, 1, 'admin',  '$2a$10$oNXFlhGI0LekTBIedkILTeBYcDCT11Mb4NmIO.p5WH.6isYLLs9uC', 'ADMIN'), /*C_USER_PASSWORD = adminpss*/
(2, 2, 'curry',  '$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC', 'USER'),  /*C_USER_PASSWORD = userpss*/
(3, 3, 'messi',  '$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC', 'USER'),  /*C_USER_PASSWORD = userpss*/
(4, 4, 'white',  '$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC', 'USER'),  /*C_USER_PASSWORD = userpss*/
(5, 5, 'crosby', '$2a$10$Km5QAjX0JsCE0DSpPNroLucN1/wlfc4V4PCDJRbb0/yH0jnso0dcC', 'USER')   /*C_USER_PASSWORD = userpss*/
;

/* SCHEDULER TASKS */

INSERT INTO T_SCH_TASK(C_SCH_TASK_ID, C_USER_ID, C_SCH_TASK_TYPE, C_SCH_TASK_NAME, C_SCH_TASK_DESCR, C_SCH_TASK_PARAM_JSON)
VALUES
(1, 1, 'DAILY',   'NBA regular',       'NBA regular import',       '{nba:regular}'),
(2, 1, 'DAILY',   'NBA playoff',       'NBA playoff import',       '{nba:playoff}'),
(3, 2, 'DAILY',   'NBA',               'NBA import',               '{nba:general}'),
(4, 3, 'WEEKLY',  'Soccer',            'Soccer import',            '{Soccer:general}'),
(5, 4, 'WEEKLY',  'American football', 'American football import', '{}'),
(6, 5, 'MONTHLY', 'NHL',               'NHL import',               '{}')
;


INSERT INTO T_REMOTE_JOB(C_REMOTE_JOB_ID, C_SCH_TASK_ID, C_REQUEST_URL, C_REQUEST_METHOD, C_USER_AUTH_STRING, C_POST_JSON)
VALUES
(1, 1, 'http://nba.com/',        'GET',    'ABC000N',  '{}'),
(2, 2, 'http://nba.com',         'GET',    'BCD000W',  '{value:1}'),
(3, 3, 'www.nba.com',            'GET',    'sfdffdf',  'null'),
(4, 4, 'http://www.soccer.com/', 'POST',   '3453255',  '{}'),
(5, 5, 'amerfoo.com',            'DELETE', 'fdbfd54',  '{}'),
(6, 6, 'nhl.com',                'PUT',    '_^&%$*(',  '{}')
;





