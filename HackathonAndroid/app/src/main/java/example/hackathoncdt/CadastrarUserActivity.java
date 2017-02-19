package example.hackathoncdt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.hackathoncdt.utils.EditTextUtils;

public class CadastrarUserActivity extends AppCompatActivity {

    @BindView(R.id.edt_apelido)
    EditText edtApelido;

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_senha)
    EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);
        ButterKnife.bind(this);

        // ActionBar disponível?
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Voltar
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registrar(View view) {
        // Valida campos
        if (EditTextUtils.estaVazio(edtApelido)) {
            EditTextUtils.definirCampoObrigatorioHint(edtApelido);
        }
        if (EditTextUtils.estaVazio(edtSenha)) {
            EditTextUtils.definirCampoObrigatorioHint(edtSenha);
        }
        if (EditTextUtils.estaVazio(edtEmail)) {
            EditTextUtils.definirCampoObrigatorioHint(edtEmail);
        }

        // Email inválido?
        if (!EditTextUtils.ehEmailValido(edtEmail)) {
            edtEmail.setError("Endereço de e-mail inválido!");
        }

    }

}
