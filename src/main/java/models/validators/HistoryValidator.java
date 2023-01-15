package models.validators;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.History;

public class HistoryValidator {
    public static List<String> validate(History h) {
        List<String> errors = new ArrayList<String>();

        String title_error = _validateTitle(h.getTitle());
        if (!title_error.equals("")) {
            errors.add(title_error);
        }

        String learning_time_error = _validateLearningTime(h.getLearning_time());
        if (!learning_time_error.equals("")) {
            errors.add(learning_time_error);
        }

        String started_at_error = _validateStartedAt(h.getStarted_at());
        if (!started_at_error.equals("")) {
            errors.add(started_at_error);
        }

        String finished_at_error = _validateFinishedAt(h.getFinished_at());
        if (!finished_at_error.equals("")) {
            errors.add(finished_at_error);
        }

        String content_error = _validateContent(h.getContent());
        if (!content_error.equals("")) {
            errors.add(content_error);
        }

        return errors;
    }

    private static String _validateTitle(String title) {
        if (title == null || title.equals("")) {
            return "タイトルを入力してください。";
        }

        return "";
    }

    private static String _validateLearningTime(Integer integer) {
        if (integer == null || integer.equals("")) {
            return "学習時間を入力してください。";
        }

        return "";
    }

    private static String _validateStartedAt(Timestamp started_at) {
        if (started_at == null || started_at.equals("")) {
            return "開始時刻を入力してください。";
        }

        return "";
    }

    private static String _validateFinishedAt(Timestamp finished_at) {
        if (finished_at == null || finished_at.equals("")) {
            return "終了時刻を入力してください。";
        }

        return "";
    }

    private static String _validateContent(String content) {
        if (content == null || content.equals("")) {
            return "内容を入力してください。";
        }

        return "";
    }
}