package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Quack;

public class QuackValidator {
    public static List<String> validate(Quack q) {
        List<String> errors = new ArrayList<String>();

        String content_error = _validateContent(q.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    public static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
        }

        return "";
    }
}
