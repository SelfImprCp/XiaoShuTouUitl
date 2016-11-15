package com.cp.mylibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jerry on 2016/6/28.
 */
public class IDCardUitl {

    /*********************************** 身份证验证开始 ****************************************/
    /**
     * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
     * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 4、顺序码（第十五位至十七位）
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数）
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     */




    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    protected String codeAndCity[][] = { { "11", "北京" }, { "12", "天津" },
            { "13", "河北" }, { "14", "山西" }, { "15", "内蒙古" }, { "21", "辽宁" },
            { "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },
            { "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" },
            { "37", "山东" }, { "41", "河南" }, { "42", "湖北" }, { "43", "湖南" },
            { "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },
            { "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" },
            { "61", "陕西" }, { "62", "甘肃" }, { "63", "青海" }, { "64", "宁夏" },
            { "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },
            { "91", "国外" } };

    private String cityCode[] = { "11", "12", "13", "14", "15", "21", "22",
            "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82", "91" };

    // 每位加权因子
    private int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    // 第18位校检码
    private String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5",
            "4", "3", "2" };






    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard
     * @return
     */
    public boolean isValidatedAllIdcard(String idcard) {
        if (idcard.length() == 15) {
            return this.isValidate15Idcard(idcard);
        }
        return this.isValidate18Idcard(idcard);
    }
    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
     * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
     * 2 1 6 3 7 9 10 5 8 4 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
     * 2。
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard
     * @return
     */
    public boolean isValidate18Idcard(String idcard) {
        // 非18位为假
        if (idcard.length() != 18) {
            return false;
        }

        // 获取前17位
        String idcard17 = idcard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);
        char c[] = null;
        String checkCode = "";
        // 是否都为数字
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        if (null != c) {
            int bit[] = new int[idcard17.length()];

            bit = converCharToInt(c);


            int sum17 = 0;

            sum17 = getPowerSum(bit);

            // 将和值与11取模得到余数进行校验码判断
            checkCode = getCheckCodeBySum(sum17);


            if (null == checkCode) {
                return false;
            }
            // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
            if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
     *
     * @param idcard
     * @return
     */
    public boolean isValidate15Idcard(String idcard) {
        // 非15位为假
        if (idcard.length() != 15) {
            return false;
        }

        // 是否全都为数字
        if (isDigital(idcard)) {
            String provinceid = idcard.substring(0, 2);
            String birthday = idcard.substring(6, 12);
            int year = Integer.parseInt(idcard.substring(6, 8));
            int month = Integer.parseInt(idcard.substring(8, 10));
            int day = Integer.parseInt(idcard.substring(10, 12));

            // 判断是否为合法的省份
            boolean flag = false;
            for (String id : cityCode) {
                if (id.equals(provinceid)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
            // 该身份证生出日期在当前日期之后时为假
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (birthdate == null || new Date().before(birthdate)) {
                return false;
            }

            // 判断是否为合法的年份
            GregorianCalendar curDay = new GregorianCalendar();
            int curYear = curDay.get(Calendar.YEAR);
            int year2bit = Integer.parseInt(String.valueOf(curYear)
                    .substring(2));

            // 判断该年份的两位表示法，小于50的和大于当前年份的，为假
            if ((year < 50 && year > year2bit)) {
                return false;
            }

            // 判断是否为合法的月份
            if (month < 1 || month > 12) {
                return false;
            }

            // 判断是否为合法的日期
            boolean mflag = false;
            curDay.setTime(birthdate); // 将该身份证的出生日期赋于对象curDay
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    mflag = (day >= 1 && day <= 31);
                    break;
                case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                    if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {
                        mflag = (day >= 1 && day <= 29);
                    } else {
                        mflag = (day >= 1 && day <= 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    mflag = (day >= 1 && day <= 30);
                    break;
            }
            if (!mflag) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param
     * @param sum17
     * @return 校验位
     */
    public String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }
    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    public int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }
    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    public boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }


//    /**
//     * 功能：身份证的有效验证
//     *
//     * @param IDStr 身份证号
//     * @return 有效：返回"" 无效：返回String信息
//     * @throws ParseException
//     */
//    @SuppressWarnings("unchecked")
//    public static String IDCardValidate(String IDStr) throws ParseException {
//        String errorInfo = "";// 记录错误信息
//        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
//                "3", "2"};
//        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
//                "9", "10", "5", "8", "4", "2"};
//        String Ai = "";
//        // ================ 号码的长度 15位或18位 ================
//        if (IDStr.length() != 15 && IDStr.length() != 18) {
//            errorInfo = "身份证号码长度应该为15位或18位。";
//            return errorInfo;
//        }
//        // =======================(end)========================
//
//        // ================ 数字 除最后以为都为数字 ================
//        if (IDStr.length() == 18) {
//            Ai = IDStr.substring(0, 17);
//        } else if (IDStr.length() == 15) {
//            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
//        }
//        if (isNumeric(Ai) == false) {
//            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
//            return errorInfo;
//        }
//        // =======================(end)========================
//
//        // ================ 出生年月是否有效 ================
//        String strYear = Ai.substring(6, 10);// 年份
//        String strMonth = Ai.substring(10, 12);// 月份
//        String strDay = Ai.substring(12, 14);// 月份
//        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
//            errorInfo = "身份证生日无效。";
//            return errorInfo;
//        }
//        GregorianCalendar gc = new GregorianCalendar();
//        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
//                    || (gc.getTime().getTime() - s.parse(
//                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
//                errorInfo = "身份证生日不在有效范围。";
//                return errorInfo;
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
//            errorInfo = "身份证月份无效";
//            return errorInfo;
//        }
//        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
//            errorInfo = "身份证日期无效";
//            return errorInfo;
//        }
//        // =====================(end)=====================
//
//        // ================ 地区码时候有效 ================
//        Hashtable h = GetAreaCode();
//        if (h.get(Ai.substring(0, 2)) == null) {
//            errorInfo = "身份证地区编码错误。";
//            return errorInfo;
//        }
//        // ==============================================
//
//        // ================ 判断最后一位的值 ================
//        int TotalmulAiWi = 0;
//        for (int i = 0; i < 17; i++) {
//            TotalmulAiWi = TotalmulAiWi
//                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
//                    * Integer.parseInt(Wi[i]);
//        }
//        int modValue = TotalmulAiWi % 11;
//        String strVerifyCode = ValCodeArr[modValue];
//        Ai = Ai + strVerifyCode;
//
//        if (IDStr.length() == 18) {
//            if (Ai.equals(IDStr) == false) {
//                errorInfo = "身份证无效，不是合法的身份证号码";
//                return errorInfo;
//            }
//        } else {
//            return "";
//        }
//        // =====================(end)=====================
//        return "";
//    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param args
     * @throws ParseException
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws ParseException {
        // String IDCardNum="210102820826411";
        // String IDCardNum="210102198208264114";
        String IDCardNum = "210181198807193116";
        IDCardUitl cc = new IDCardUitl();

     //   System.out.println(cc.IDCardValidate(IDCardNum));
        // System.out.println(cc.isDate("1996-02-29"));
    }


    public String getSexForID(String value) {
        value = value.trim();
        if (value == null || (value.length() != 15 && value.length() != 18)) {
            return "";
        }
        if (value.length() == 15 || value.length() == 18) {
            String lastValue = value.substring(value.length() - 1, value.length());
            int sex;
            if (lastValue.trim().toLowerCase().equals("x") || lastValue.trim().toLowerCase().equals("e")) {
                return "男";
            } else {
                sex = Integer.parseInt(lastValue) % 2;
                return sex == 0 ? "女" : "男";
            }
        } else {
            return "";
        }
    }

    /**
     *
     * @param idcard
     * @return
     * @throws ParseException
     */
    public String getBirthdayForID(String idcard) throws ParseException {

        // 获取出生日期
        String birthday = idcard.substring(6, 14);
        Date birthdate = new SimpleDateFormat("yyyyMMdd")
                .parse(birthday);

        GregorianCalendar currentDay = new GregorianCalendar();
        currentDay.setTime(birthdate);
        int year = currentDay.get(Calendar.YEAR);
        int month = currentDay.get(Calendar.MONTH) + 1;
        int day = currentDay.get(Calendar.DAY_OF_MONTH);

        return year + month + day + "";

    }


    /*********************************** 身份证验证结束 ****************************************/

    /**
     * 隐藏部分身份证号
     *
     * @param
     * @throws ParseException
     */

    public static String hideId(String ID) {


        String regex = "(\\w{6})(\\w+)(\\w{4})";
        return ID.replaceAll(regex, "$1****$3");

    }


}
