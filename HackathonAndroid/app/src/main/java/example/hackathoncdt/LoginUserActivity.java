package example.hackathoncdt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.hackathoncdt.utils.EditTextUtils;

public class LoginUserActivity extends AppCompatActivity {

    @BindView(R.id.edt_login)
    EditText edtLogin;

    @BindView(R.id.edt_senha)
    EditText edtSenha;

    @BindView(R.id.btn_cadastrar)
    Button btnCadastrar;

    @BindView(R.id.btn_entrar)
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        ButterKnife.bind(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAplication();
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginUserActivity.this, CadastrarUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginAplication() {
        if (camposValidos()) {
            // TODO possível API call aqui...

            // Fecha a activity atual e vai pro menu
            startActivity(new Intent(LoginUserActivity.this, MainMenuActivity.class));
            finish();
        }
    }

    private boolean camposValidos() {
        if (EditTextUtils.estaVazio(edtLogin)) {
            EditTextUtils.definirCampoObrigatorioHint(edtLogin);
            return false;
        } else if (EditTextUtils.estaVazio(edtSenha)) {
            EditTextUtils.definirCampoObrigatorioHint(edtSenha);
            return false;
        }

        // Email válido?
        if (!EditTextUtils.ehEmailValido(edtLogin)) {
            edtLogin.setError("E-mail inválido ou digitado incorretamente!");
            return false;
        }

        return true;
    }

}
