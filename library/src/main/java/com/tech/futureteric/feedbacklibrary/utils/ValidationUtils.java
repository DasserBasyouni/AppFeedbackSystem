package com.tech.futureteric.feedbacklibrary.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

import com.tech.futureteric.feedbacklibrary.constants.LibEnums;

public class ValidationUtils {

    public static boolean isEditTextInputValid(EditText editText, @LibEnums.InputType int inputType) {
        boolean validText = true;
        Editable editable = editText.getText();

        if (TextUtils.isEmpty(editable)){
            editText.setError("This section is required");
            validText = false;
        } else
            editText.setError(null);

        if (inputType == LibEnums.TEXT)
            return validText;
        else if (validText && inputType == LibEnums.EMAIL) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(editable.toString()).matches()) {
                editText.setError(null);
                return true;
            }else {
                editText.setError("Invalid Email Address");
                return false;
            }
        }
        else
            throw new IllegalArgumentException("An Error happened while validating input with type: " + inputType);
    }
}
