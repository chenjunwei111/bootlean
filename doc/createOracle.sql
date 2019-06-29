select * from t_sys_datas;
select * from t_sys_file;
select * from t_sys_file_data;
select * from t_sys_oper_log;

select * from T_SYS_USER_2;
select * from t_sys_permission_role_2;
select * from t_sys_premission_2;
select * from t_sys_role_2;
select * from t_sys_role_user_2;
select * from t_sys_user_2;



CREATE TABLE t_sys_datas (
                             id varchar2(255) ,
                             file_path varchar2(255),
                             PRIMARY KEY (id)
) ;


CREATE TABLE t_sys_file (
                            id varchar2(255) ,
                            file_name varchar2(255) ,
                            create_user_id varchar2(255),
                            create_user_name varchar2(255) ,
                            create_time date ,
                            update_user_id varchar2(255) ,
                            update_user_name varchar2(255) ,
                            update_time date ,
                            PRIMARY KEY (id)
);

CREATE TABLE T_SYS_USER_2(
                             ID VARCHAR2(20 BYTE),
                             USERNAME VARCHAR2(64 BYTE),
                             PASSWORD VARCHAR2(64 BYTE),
                             EMAIL VARCHAR2(64 BYTE),
                             ISVALID VARCHAR2(64 BYTE),
                             DISCRIPTION VARCHAR2(64 BYTE),
                             LASTTIME DATE
);
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('1','admin','21232f297a57a5a743894a0e4a801fc3','@qq2.com','1','超级管理员',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('488294747442511872','fuce','e10adc3949ba59abbe56e057f20f883e','@qq.com','1','访客',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('583602394441449472','cjw','e10adc3949ba59abbe56e057f20f883e','@qq.com','1','访客',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));




CREATE TABLE t_sys_file_data (
                                 id varchar2(255) ,
                                 data_id varchar2(255) ,
                                 file_id varchar2(255),
                                 PRIMARY KEY (id)
) ;




INSERT INTO t_sys_datas VALUES ('493164859534344192', 'D:/profile/1.jpg');
INSERT INTO t_sys_datas VALUES ('493191568597975040', 'D:/profile/2.jpg');
INSERT INTO t_sys_datas VALUES ('493194407776878592', 'D:/profile/3.jpg');
INSERT INTO t_sys_datas VALUES ('493195419333951488', 'D:/profile/4.jpg');
INSERT INTO t_sys_datas VALUES ('493195646874943488', 'D:/profile/5.jpg');

INSERT INTO t_sys_file VALUES ('493105775934177280', '水电费2', '1', 'admin', '2018-09-22 17:03:25', '1', 'admin', '2018-09-22 21:01:09');
INSERT INTO t_sys_file VALUES ('493191574256091136', '阿达达', '1', 'admin', '2018-09-22 22:47:14', null, null, null);

INSERT INTO t_sys_file_data VALUES ('493105048578949120', '493105005788659712', '493105048578949120');
INSERT INTO t_sys_file_data VALUES ('493164878349991936', '493164859534344192', '493105775934177280');
INSERT INTO t_sys_file_data VALUES ('493191574256091136', '493191568597975040', '493191574256091136');
INSERT INTO t_sys_file_data VALUES ('493191574256091137', '493164859534344192', '493191574256091136');
INSERT INTO t_sys_file_data VALUES ('493195660292521984', '493195646874943488', '493195660292521984');


CREATE TABLE t_sys_oper_log (
                                id varchar2(255) ,
                                action varchar2(255) ,
                                title varchar2(255) ,
                                method varchar2(255) ,
                                oper_name varchar2(255) ,
                                oper_url varchar2(255) ,
                                oper_param varchar2(255) ,
                                error_msg varchar2(255) ,
                                oper_time date ,
                                PRIMARY KEY (id)
) ;


CREATE TABLE t_sys_permission_role_2 (
                                         id varchar2(255) ,
                                         role_id varchar2(255),
                                         permission_id varchar2(255),
                                         PRIMARY KEY (id)
) ;

Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105840','002','A1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105845','002','A1-B3');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105851','002','A1-B3-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105855','002','A1-B1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105860','002','A1-B1-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105865','002','A1-B2');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560415105869','002','A1-B2-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114427','001','A1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114434','001','A1-B1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114438','001','A1-B1-del');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114442','001','A1-B1-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114446','001','A1-B1-edit');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114450','001','A1-B1-add');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114454','001','A1-B3');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114458','001','A1-B3-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114461','001','A1-B3-editUser');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114465','001','A1-B3-add');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114470','001','A1-B3-editPre');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114474','001','A1-B3-edit');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114478','001','A1-B3-del');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114481','001','A1-B2');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114486','001','A1-B2-editUR');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114492','001','A1-B2-edit');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114496','001','A1-B2-del');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114500','001','A1-B2-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114504','001','A1-B2-add');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114508','001','A1-B4');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114512','001','A1-B4-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114516','001','A2');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560423114520','001','A2-B1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019847','1560340019842','A1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019852','1560340019842','A1-B3');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019856','1560340019842','A1-B1');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019860','1560340019842','A1-B1-del');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019864','1560340019842','A1-B1-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019869','1560340019842','A1-B1-edit');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019873','1560340019842','A1-B1-add');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019877','1560340019842','A1-B2');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019882','1560340019842','A1-B2-editUR');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019886','1560340019842','A1-B2-edit');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019891','1560340019842','A1-B2-del');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019895','1560340019842','A1-B2-list');
Insert into T_SYS_PERMISSION_ROLE_2 (ID,ROLE_CODE,PERMISSION_CODE) values ('1560340019899','1560340019842','A1-B2-add');




CREATE TABLE t_sys_premission_2 (
                                    FUNCTION_CODE VARCHAR2(255 BYTE),
                                    FUNCTION_NAME VARCHAR2(255 BYTE),
                                    FUNCTION_PARENT_CODE VARCHAR2(255 BYTE),
                                    FUNCTION_ICON VARCHAR2(255 BYTE),
                                    FUNCTION_HREF VARCHAR2(100 BYTE),
                                    ISVALID NUMBER,
                                    SEQUENCE NUMBER,
                                    DESCRIPTION VARCHAR2(64 BYTE),
                                    PERMS VARCHAR2(100 BYTE),
                                    PERMS_TYPE VARCHAR2(20 BYTE)
) ;

Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B1','权限管理','A1','icon-yonghuganzhi','PremissionController/view',1,1100,'二级目录','system:premission:view','view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1','后台管理',null,'icon-ganzhijiankong',null,1,1000,'一级目录',null,'view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2','用户管理','A1','icon-yonghuganzhi','UserController/view',1,1200,'权限视图','system:user:view','view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-list','角色列表','A1-B3',null,null,1,1301,null,'system:role:list','list');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-add','角色添加','A1-B3',null,null,1,1302,null,'system:role:add','add');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-del','角色删除','A1-B3',null,null,1,1303,null,'system:role:del','del');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-edit','角色更新','A1-B3',null,null,1,1304,null,'system:role:edit','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-editPre','角色权限修改','A1-B3',null,null,1,1305,null,'system:role:editPre','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3-editUser','角色用户修改','A1-B3',null,null,1,1306,null,'system:role:editUser','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B1-edit','权限修改','A1-B1',null,null,1,1101,'权限修改','system:premission:edit','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B1-del','权限删除','A1-B1',null,null,1,1102,'删除权限','system:premission:del','del');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B1-add','权限增加','A1-B1',null,null,1,1104,'增加新的权限','system:premission:add','add');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B1-list','权限列表','A1-B1',null,null,1,1103,'权限功能表格','system:premission:list','list');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A2','模版',null,'icon-ganzhijiankong',null,1,2000,'一级目录',null,'view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A2-B1','常用模版','A2','icon-yonghuganzhi','CommonController/usuallyDemo',1,2100,'二级工具',null,'view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2-list','用户列表','A1-B2',null,null,1,1201,'用户列表','system:user:list','list');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2-add','用户添加','A1-B2',null,null,1,1202,null,'system:user:add','add');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2-edit','用户修改','A1-B2',null,null,1,1203,null,'system:user:edit','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2-del','用户删除','A1-B2',null,null,1,1204,null,'system:user:del','del');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B2-editUR','用户修改权限分配','A1-B2',null,null,1,1205,null,'system:user:editUR','edit');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B3','角色管理','A1','icon-ganzhijiankong','RoleController/view',1,1300,'二级目录','system:role:view','view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B4','日志管理','A1','icon-yonghuganzhi','LogController/view',1,1400,'二级目录','system:log:view','view');
Insert into T_SYS_PREMISSION_2 (FUNCTION_CODE,FUNCTION_NAME,FUNCTION_PARENT_CODE,FUNCTION_ICON,FUNCTION_HREF,ISVALID,SEQUENCE,DESCRIPTION,PERMS,PERMS_TYPE) values ('A1-B4-list','日志列表','A1-B4',null,null,1,1401,'日志列表','system:log:list','list');


CREATE TABLE t_sys_role_2 (
                              ID VARCHAR2(100 BYTE),
                              ROLE_NAME VARCHAR2(100 BYTE),
                              DESCRIPTION VARCHAR2(100 BYTE)
) ;

Insert into T_SYS_ROLE_2 (ID,ROLE_NAME,DESCRIPTION) values ('001','超级管理员','最大权限');
Insert into T_SYS_ROLE_2 (ID,ROLE_NAME,DESCRIPTION) values ('1560340019842','小角色','没有的');
Insert into T_SYS_ROLE_2 (ID,ROLE_NAME,DESCRIPTION) values ('002','游客','只能看个别');
Insert into T_SYS_ROLE_2 (ID,ROLE_NAME,DESCRIPTION) values ('004','二级角色','只能看个别');

CREATE TABLE t_sys_role_user_2 (
                                   id varchar2(255),
                                   sys_user_id varchar2(255) ,
                                   sys_role_id varchar2(255) ,
                                   PRIMARY KEY (id)
) ;
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560238853795','583602394441449472','002');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560242995113','1560242974631','001');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560242995122','1560242974631','002');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560243068999','1560243040073','001');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560243072108','1560243040073','002');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560243244418','1560243244412','001');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560243244424','1560243244412','002');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560331627296','001','001');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560331628009','001','002');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560331757734','1','001');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('1560340034393','488294747442511872','1560340019842');
Insert into T_SYS_ROLE_USER_2 (ID,SYS_USER_ID,SYS_ROLE_ID) values ('002','488294747442511872','002');


CREATE TABLE t_sys_user_2 (
                              ID VARCHAR2(20 BYTE),
                              USERNAME VARCHAR2(64 BYTE),
                              PASSWORD VARCHAR2(64 BYTE),
                              EMAIL VARCHAR2(64 BYTE),
                              ISVALID VARCHAR2(64 BYTE),
                              DISCRIPTION VARCHAR2(64 BYTE),
                              LASTTIME DATE
) ;
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('1','admin','21232f297a57a5a743894a0e4a801fc3','@qq2.com','1','超级管理员',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('488294747442511872','fuce','e10adc3949ba59abbe56e057f20f883e','@qq.com','1','访客',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));
Insert into T_SYS_USER_2 (ID,USERNAME,PASSWORD,EMAIL,ISVALID,DISCRIPTION,LASTTIME) values ('583602394441449472','cjw','e10adc3949ba59abbe56e057f20f883e','@qq.com','1','访客',to_date('2019-06-10 11:32:22','YYYY-MM-DD HH24:MI:SS'));
