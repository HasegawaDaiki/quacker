package models.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean address_duplicate_check_flag, Boolean email_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String address_error = _validateAddress(u.getAddress(), address_duplicate_check_flag);
        if(!address_error.equals("")) {
            errors.add(address_error);
        }

        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String email_error = _validateEmail(u.getEmail(), email_duplicate_check_flag);
        if(!email_error.equals("")) {
            errors.add(email_error);
        }

        String password_error = _validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // ユーザアドレス
    private static String _validateAddress(String address, Boolean address_duplicate_check_flag) {
        // 必須入力チェック
        if(address == null || address.equals("")) {
            return "ユーザアドレスを入力してください。";
        }

        // 既に登録されているユーザアドレスとの重複チェック
        if(address_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredAddress", Long.class)
                                           .setParameter("address", address)
                                             .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザアドレスは既に使用されています";
            }
        }

        return "";
    }

    // ユーザ名の必須入力チェック
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "ユーザ名を入力してください";
        }
        return "";
    }

    // メールアドレス
    private static String _validateEmail(String email, Boolean email_duplicate_check_flag) {
        // 正しいメールアドレスかどうかのチェック
        boolean result;
        String aText = "[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]";
        String dotAtom = aText + "+" + "(\\." + aText + "+)*";
        String regularExpression = "^" + dotAtom + "@" + dotAtom + "$";
        result = _checkMailAddress(email, regularExpression);
        if (result) {
            return "メールアドレスが正しくありません";
        }

        // 重複するメールアドレスがないかのチェック
        if(email_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredEmail", Long.class)
                                           .setParameter("email", email)
                                             .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザアドレスは既に使用されています";
            }
        }

        return "";
    }

    private static boolean _checkMailAddress(String email, String regularExpression) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください";
        }
        return "";
    }
}