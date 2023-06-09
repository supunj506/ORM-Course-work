/*
 * @author : Supun Jaysinghe
 * Date    : 19 April 2023
 * Time    : 12:47 PM
 * Project : d24_hotel_manage_system
 * Created by IntelliJ IDEA.
 */
package lk.ccns.d24.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static Object validate(LinkedHashMap<JFXTextField, Pattern> map, JFXButton btn) {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()) {
                //if the input is not matching
                addError(key, btn);
                return key;
            }
            removeError(key, btn);
        }
        return true;
    }

    private static void removeError(TextField txtField, JFXButton btn) {
        txtField.setStyle("-fx-text-fill: black");
        btn.setDisable(false);
    }

    private static void addError(TextField txtField, JFXButton btn) {
        if (txtField.getText().length() > 0) {
            txtField.setStyle("-fx-text-fill: red");
        }
        btn.setDisable(true);
    }

    public static boolean singleValidate(Pattern compile, JFXTextField txt) {
        if (!compile.matcher(txt.getText()).matches()) {
            //if the input is not matching
            if (txt.getText().length() > 0) {
                txt.setStyle("-fx-text-fill: red");
                return true;
            }
            return true;

        } else {
            txt.setStyle("-fx-text-fill: black");
            return false;
        }

    }

}
