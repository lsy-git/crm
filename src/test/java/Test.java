import com.crm.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 17:20
 */
public class Test {

    public static void main(String[] args) {

        String md5 = MD5Util.getMD5("123");
        System.out.println(md5);


    }
}
