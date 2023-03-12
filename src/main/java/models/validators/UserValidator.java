package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String user_id_error = validateCode(u.getUser_id(), codeDuplicateCheckFlag);
        if (!user_id_error.equals("")) {
            errors.add(user_id_error);
        }

        String name_error = validateName(u.getName());
        if (!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if (!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // ユーザID
    private static String validateCode(String userId, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if (userId == null || userId.equals("")) {
            return "ユーザIDを入力してください。";

        }

        // すでに登録されている社員番号との重複チェック
        if (codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long employees_count = (long) em.createNamedQuery("checkRegisteredUserId", Long.class)
                    .setParameter("user_id", userId).getSingleResult();
            em.close();
            if (employees_count > 0) {
                return "入力されたユーザIDの情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 氏名の必須入力チェック
    private static String validateName(String name) {
        if (name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}