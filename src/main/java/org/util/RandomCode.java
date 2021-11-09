package org.util;

import java.util.Random;

public class RandomCode {
    public static String getCode()
    {
        Random random=new Random();
        StringBuffer captcha=new StringBuffer();
        for (int i=0;i<4;i++){
            captcha.append(random.nextInt(7)+"");
        }
        String emailCode =new String(captcha);
        return emailCode;
    }
}
