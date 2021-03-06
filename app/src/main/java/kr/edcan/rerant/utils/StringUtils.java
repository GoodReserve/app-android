package kr.edcan.rerant.utils;

import android.widget.EditText;

import java.util.ArrayList;

import kr.edcan.rerant.model.Menu;

/**
 * Created by Junseok on 2016-10-04.
 */

public class StringUtils {
    public static boolean validEmail(EditText editText) {
        return editText.getText().toString().trim().contains("@");
    }

    public static boolean checkPassword(EditText password, EditText repassword) {
        return password.getText().toString().trim().equals(repassword.getText().toString().trim());
    }

    public static boolean fullFilled(EditText... e) {
        for (EditText editText : e) {
            if (editText.getText().toString().trim().equals("")) return false;
        }
        return true;
    }

    public static String getFullImageUrl(String thumbnailUrl) {
        return "http://kafuuchino.one:3000" + thumbnailUrl;
    }

    public static String convertArraytoString(ArrayList<String> arrayList) {
        String result = "";
        for (int i = 0; i < arrayList.size(); i++) {
            result += (arrayList.get(i) + ((i == arrayList.size() - 1) ? "" : ","));
        }
        return result;
    }

    public static String getTotalMoney(ArrayList<Menu> menus) {
        int total = 0;
        for (Menu m : menus) {
            total += Integer.parseInt(m.getPrice());
        }
        return total + " 원";
    }
    public static int getTotalMoneyint(ArrayList<Menu> menus) {
        int total = 0;
        for (Menu m : menus) {
            total += Integer.parseInt(m.getPrice());
        }
        return total;
    }
}
