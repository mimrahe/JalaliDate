package ir.mimrahe.lib.jalalidate;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarConverter {

    public static int g2Jalali(int gregWeekNo){
        if (gregWeekNo == 7){
            return 1;
        }
        return ++gregWeekNo;
    }

    public static JalaliDate prevDate(JalaliDate jalaliDate){
        JalaliDate.Month prevMonth =
                JalaliDate.getPrevMonth(JalaliDate.getMonth(jalaliDate.getMonth()));
        int year = jalaliDate.getYear();
        if (prevMonth.getOrder() == 12){
            year -= 1;
        }
        return new JalaliDate(year, prevMonth.getOrder(), jalaliDate.getDay());
    }

    public static JalaliDate nextDate(JalaliDate jalaliDate){
        JalaliDate.Month nextMonth =
                JalaliDate.getNextMonth(JalaliDate.getMonth(jalaliDate.getMonth()));
        int year = jalaliDate.getYear();
        if (nextMonth.getOrder() == 1){
            year += 1;
        }
        return new JalaliDate(year, nextMonth.getOrder(), jalaliDate.getDay());
    }

    public static Calendar jalali2G(JalaliDate jalaliDate){
        int jalaliYear = jalaliDate.getYear();
        int jalaliMonth = jalaliDate.getMonth();
        int jalaliDay = jalaliDate.getDay();

        int gregYear = jalaliYear <= 979 ? 621 : 1600;
        jalaliYear -= jalaliYear <= 979 ? 0 : 979;

        int days = (365*jalaliYear) +(((jalaliYear/33))*8) +((((jalaliYear%33)+3)/4))
                +78 +jalaliDay +((jalaliMonth<7)?(jalaliMonth-1)*31:((jalaliMonth-7)*30)+186);
        gregYear += 400 * (days / 146097);
        days %= 146097;
        if (days > 36524){
            gregYear += 100 * (--days / 36524);
            days %= 36524;
            if (days >= 365) days++;
        }
        gregYear += 4 * (days / 1461);
        days %= 1461;
        gregYear += ((days - 1) / 365);
        if (days > 365){
            days = (days - 1) % 365;
        }
        int gregDay = days + 1;
        int[] array = {
                0, 31,
                ((gregYear % 4 == 0 && gregYear % 100 != 0) || gregYear % 400 == 0) ? 29 : 28,
                31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };
        int arrayLength = array.length;
        int gregMonth;
        for (gregMonth = 0; gregMonth < arrayLength; gregMonth++){
            if (gregDay <= array[gregMonth]){
                break;
            }
            gregDay -= array[gregMonth];
        }

        return new GregorianCalendar(gregYear, --gregMonth, gregDay);
    }

    public static JalaliDate g2Jalali(Calendar gCalendar){
        int gregYear = gCalendar.get(Calendar.YEAR);
        int gregMonth = gCalendar.get(Calendar.MONTH) + 1;
        int gregDay = gCalendar.get(Calendar.DAY_OF_MONTH);

        int jalaliYear, jalaliMonth, jalaliDay, gregYear2;

        int[] array = {0,31,59,90,120,151,181,212,243,273,304,334};

        jalaliYear = gregYear <= 1600 ? 0 : 979;
        gregYear -= gregYear <= 1600 ? 621 : 1600;
        gregYear2 = gregMonth > 2 ? gregYear + 1 : gregYear;
        int days = (365 * gregYear) +
                ((gregYear2 + 3) / 4) -
                ((gregYear2 + 99) / 100) +
                ((gregYear2 + 399) / 400) - 80 +
                gregDay + array[gregMonth - 1];
        jalaliYear += 33 * (days / 12053);
        days %= 12053;
        jalaliYear += 4 * (days / 1461);
        days %= 1461;
        jalaliYear += (days - 1) / 365;
        if (days > 365){
            days = (days - 1) % 365;
        }
        jalaliMonth = days < 186 ? 1 + (days / 31) : 7 + ((days - 186) / 30);
        jalaliDay = 1 + (days < 186 ? days % 31 : (days - 186) % 30);

        return new JalaliDate(jalaliYear, jalaliMonth, jalaliDay);
    }
}
