package ir.mimrahe.lib.jalalidate;

public class JalaliDate{

    int mYear;
    int mMonth;
    int mDay;

    public JalaliDate(int year, int month, int day){
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    public int getYear(){
        return mYear;
    }

    public void setYear(int year){
        mYear = year;
    }

    public int getMonth(){
        return mMonth;
    }

    public void setMonth(int month){
        mMonth = month;
    }

    public int getDay(){
        return mDay;
    }

    public void setDay(int day){
        mDay = day;
    }

    public String toString()
    {
        return getYear() + "/" + getMonth() + "/" + getDay();
    }

    public JalaliDate clone(){
        return new JalaliDate(getYear(), getMonth(), getDay());
    }

    public boolean equals(JalaliDate date){
        if (date == null)
            return false;

        if (date.getYear() != getYear())
            return false;

        if (date.getMonth() != getMonth())
            return false;

        if (date.getDay() != getDay())
            return false;

        return true;
    }

    public static Month getMonth(int order){
        for (Month month: Month.values()){
            if (month.getOrder() == order){
                return month;
            }
        }
        return null;
    }

    public static Month getPrevMonth(Month month){
        if (month.getOrder() == 1){
            return Month.ESFAND;
        }
        return getMonth(month.getOrder() - 1);
    }

    public static Month getNextMonth(Month month){
        if (month.getOrder() == 12){
            return Month.FARVARDIN;
        }
        return getMonth(month.getOrder() + 1);
    }

    public enum Month{
        FARVARDIN(1, 31),
        ORDIBEHESHT(2, 31),
        KHORDAD(3, 31),
        TIR(4, 31),
        MORDAD(5, 31),
        SHAHRIVAR(6, 31),
        MEHR(7, 30),
        ABAN(8, 30),
        AZAR(9, 30),
        DEY(10, 30),
        BAHMAN(11, 30),
        ESFAND(12, 29);

        public int order, daysCount;

        Month(int order, int daysCount){
            this.order = order;
            this.daysCount = daysCount;
        }

        public int getOrder() {
            return order;
        }

        public int getDaysCount(){
            return daysCount;
        }
    }

    public enum WeekDay{
        SHANBEH(1),
        YEK_SHANBEH(2),
        DO_SHANBEH(3),
        SE_SHANBEH(4),
        CHAR_SHANBEH(5),
        PANJ_SHANBEH(6),
        JOMEH(7);

        int weekDay;

        WeekDay(int weekDay){
            this.weekDay = weekDay;
        }

        public int getWeekDay() {
            return weekDay;
        }
    }
}
