-- 添加字符串分区
create or replace PROCEDURE     "PRO_PARTITION_STRING" (I_STR VARCHAR2,I_TABLE VARCHAR2,IS_DEL VARCHAR2) Authid Current_User
AS
    TMP_NUM NUMBER(1);
    P_AREACODE VARCHAR2(10);
    P_PARTITION  VARCHAR2(20);
BEGIN
    --I_ID  项目ID
--I_TABLE  表名
--IS_DEL   如果存在该表分区，是否删除   1为删除，0不删除
    P_PARTITION:='P'||I_STR;
    SELECT COUNT(1) INTO TMP_NUM FROM USER_TAB_PARTITIONS WHERE TABLE_NAME=I_TABLE AND PARTITION_NAME= P_PARTITION;
    IF(TMP_NUM=0) THEN
        --创建表分区
        EXECUTE IMMEDIATE 'ALTER TABLE '||I_TABLE||' ADD PARTITION  '||P_PARTITION||' VALUES ('''||I_STR||''') ';
    ELSE
        IF IS_DEL='1' THEN
            EXECUTE IMMEDIATE 'ALTER TABLE '||I_TABLE||' TRUNCATE  PARTITION  '||P_PARTITION;
        END IF;
    END IF;
END PRO_PARTITION_STRING;


-- p530100_20190101
create or replace PROCEDURE "PRO_D_COMM_RESULT_CONTAINS_DEL" (I_DATE VARCHAR2,I_CITY VARCHAR2,I_TABLE VARCHAR2) Authid Current_User
AS
    P_MONTH VARCHAR2(10);
    TMP_MONTH VARCHAR2(10);
    P_CITY VARCHAR2(10);
    TMP_TABLE VARCHAR2(50);
    TMP_NUM NUMBER;
    TMP_SUB_NUM NUMBER;
    TMP_SQL VARCHAR2(1000);
BEGIN
    P_MONTH:=SUBSTR(I_DATE,0,10);--2014-07-07
    TMP_MONTH:='_'||REPLACE(P_MONTH,'-','');--20140707
    P_CITY:=fn_get_partition_name(I_CITY);--P110000
    --TMP_CITY:=REPLACE(P_CITY,'P_','');--SZ
    TMP_TABLE:=I_TABLE;
    TMP_NUM:=0;
    TMP_SUB_NUM:=0;
    --dbms_output.put_line('SELECT COUNT(1)  FROM USER_TAB_SUBPARTITIONS WHERE TABLE_NAME= '''||TMP_TABLE||''' AND PARTITION_NAME= '''||TMP_I_CITY||'''');
    SELECT COUNT(1) INTO TMP_NUM FROM USER_TAB_SUBPARTITIONS WHERE TABLE_NAME=TMP_TABLE AND PARTITION_NAME='P'||P_CITY;
    IF(TMP_NUM=0) THEN
        --创建表分区
        TMP_SQL:='ALTER TABLE '||TMP_TABLE||' ADD PARTITION P'||P_CITY||' VALUES ('||P_CITY||')
    (SUBPARTITION P'||P_CITY||TMP_MONTH||' values (to_date('''||P_MONTH||''',''yyyy-mm-dd'')) )';
        dbms_output.put_line(TMP_SQL);
        EXECUTE IMMEDIATE TMP_SQL;
    ELSE
        SELECT COUNT(1) INTO TMP_SUB_NUM FROM USER_TAB_SUBPARTITIONS WHERE TABLE_NAME=TMP_TABLE AND PARTITION_NAME='P'||P_CITY AND SUBPARTITION_NAME='P'||P_CITY||TMP_MONTH;
        IF(TMP_SUB_NUM=0) THEN
            --创建子分区
            TMP_SQL:='ALTER TABLE '||TMP_TABLE||' MODIFY PARTITION P'|| P_CITY||' ADD SUBPARTITION P'||P_CITY||TMP_MONTH||' values (to_date('''||P_MONTH||''',''yyyy-mm-dd'')) ';
            dbms_output.put_line(TMP_SQL);
            EXECUTE IMMEDIATE TMP_SQL;
            --END IF;
        ELSE
            TMP_SQL:= 'ALTER TABLE '||TMP_TABLE||' TRUNCATE  SUBPARTITION P'||P_CITY||TMP_MONTH;
            dbms_output.put_line(TMP_SQL);
            EXECUTE IMMEDIATE TMP_SQL;
        END IF;
    END IF;
    commit;
END PRO_D_COMM_RESULT_CONTAINS_DEL;

