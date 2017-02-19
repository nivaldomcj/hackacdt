package example.hackathoncdt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Solução programática
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mni_sair:
                // TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startRankingGeral(View view) {
        startActivity(new Intent(MainMenuActivity.this, RankingGeralActivity.class));
    }

    public void startMinhasEstatisticas(View view) {
        startActivity(new Intent(MainMenuActivity.this, EstatisticasUserActivity.class));
    }

    public void startVerOngs(View view) {
        startActivity(new Intent(MainMenuActivity.this, VerOngsActivity.class));
    }

    public void startEmpresasParceiras(View view) {
        startActivity(new Intent(MainMenuActivity.this, VerEmpresasActivity.class));
    }
}
