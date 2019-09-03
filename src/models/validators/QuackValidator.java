package models.validators;

import models.Quack;

public class QuackValidator {
    public static String validate(Quack q) {
        String error = new String();

        if(q.getContent() == null || q.getContent().equals("")) {    // 内容が空かどうかのチェック
            error = "内容を入力してください。";
        } else if(q.getContent().length() > 200) {                  // 200文字の字数制限以内であるかのチェック
            error = "200文字以内で入力してください";
        } else {
            error = "";
        }

         return error;
    }
}
