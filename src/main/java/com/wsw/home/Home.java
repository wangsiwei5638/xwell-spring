package com.wsw.home;


import com.wsw.annotation.XwellComponent;

import java.util.regex.Pattern;

@XwellComponent
public class Home {

    public static void main(String[] args) {
        String str = "aa文字1bb哈636f7079e79fa5e9819331333365656633哈cc测试dx，测试字符串aa1234bb";

        // 替换aa、bb之间的字符串为 "成功"
        String str1 = str.replaceAll("aa.*?bb", "aa成功bb");
        System.out.println(str1);

        // 替换aa、bb之间的字符串为 "成功"
        String str2 = str.replaceAll("(aa).*?(bb)", "$1成功$2");
        System.out.println(str2);

        // 替换小写字母之间的字符串为 "成功"
        String str3 = str.replaceAll("([a-z]+).*?([a-z]+)", "$1成功$2");
        System.out.println(str3);
    }

}
