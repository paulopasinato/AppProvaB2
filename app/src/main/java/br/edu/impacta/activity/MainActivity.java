package br.edu.impacta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.impacta.entities.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button botao = (Button)findViewById(R.id.botaoLogin);

        botao.setOnClickListener(onClickLogin());
    }

    private View.OnClickListener onClickLogin() {
        return new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListaProdutoActivity.class);

                TextView campoUsuario = (TextView) findViewById(R.id.campoUsuario);
                Toast.makeText(MainActivity.this, "Usuário: " + campoUsuario.getText().toString() + " logado com sucesso!", Toast.LENGTH_LONG).show();

                // sem resultado
                startActivity(intent);
            }
        };
    }
}
