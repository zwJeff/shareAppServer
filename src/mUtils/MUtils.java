package mUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MUtils {

    /**
     * 是否为电话
     *
     * @param telephone
     * @return
     */

    public static boolean isTelephone(String telephone) {

        String regExp = "^(13|15|17|18)[0-9]{9}$";

        return telephone.matches(regExp);

    }

    /**
     * 是否为数字
     *
     * @param telephone
     * @return
     */

    public static boolean isNumber(String telephone) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(telephone);
        if (!isNum.matches()) {
            return false;
        }
        return true;

    }


    /**
     * 是否为邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {

        String regExp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return email.matches(regExp);

    }

    /**
     * 获取当前sql时间
     *
     * @return
     */
    public static java.sql.Date getCurrentSqlDate() {
        java.sql.Date currentTime = new java.sql.Date(System.currentTimeMillis());
        return currentTime;
    }


    /**
     * 产生token
     *
     * @return
     */
    public static  String creatToken() {
        UUID uid = UUID.randomUUID();
        return uid.toString();
    }


}
