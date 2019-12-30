package com.cjw.boot.test.algor.testenum;

/**
* Description
* @Author junwei
* @Date 10:32 2019/10/11
**/
public class EnumTest {

    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println(day.getName()+day.getValue());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                break;

            case FRIDAY:
                System.out.println(day.getName()+day.getValue());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                break;

            case SATURDAY: case SUNDAY:
                System.out.println(day.getName()+day.getValue());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                break;

            default:
                System.out.println(day.getName()+day.getValue());
                System.out.println(day.getName()+"的下一天是"+day.getNext().getName());
                break;
        }
    }

    public static void main(String[] args) {
        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        EnumTest seventhDay = new EnumTest(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }
}
