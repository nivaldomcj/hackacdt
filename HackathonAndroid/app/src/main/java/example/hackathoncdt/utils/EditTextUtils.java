package example.hackathoncdt.utils;

import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by Nivaldo Carvalho on 19/02/2017.
 */

public class EditTextUtils {

    public static boolean estaVazio(EditText editText) {
        // Texto
        String texto = editText.getText().toString();

        // Tamanho
        int tamanho = texto.trim().length();

        return tamanho == 0;
    }

    public static boolean ehEmailValido(EditText editText) {
        return Patterns.EMAIL_ADDRESS.matcher(editText.getText()).matches();
    }

    public static void definirCampoObrigatorioHint(EditText editText) {
        editText.setError("Esse campo não pode ficar em branco!");
    }
}
