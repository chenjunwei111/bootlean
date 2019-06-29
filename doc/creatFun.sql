create or replace FUNCTION FN_GET_DISTANCE(jd1 number,  wd1 number , jd2 number , wd2 number )
    RETURN number is
    Distance NUMBER(8,3);
    r number := 6378.137;  --//地球半径,单位为千米
    CP number:= 0.01745329251994329576923690768489;  --   3.14159265 /180  = 0.01745329251994329576923690768489
    x number;
    y number;
BEGIN
    x := (jd2 - jd1) * CP * r * Cos( (wd1 + wd2) * CP / 2  ) ;
    y := (wd2 - wd1) * CP * r ;
    Distance :=  POWER((x * x + y * y) , 0.5) ;
    RETURN Distance;
END;

