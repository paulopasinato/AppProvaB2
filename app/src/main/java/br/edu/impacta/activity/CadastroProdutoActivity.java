package br.edu.impacta.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.impacta.db.ProdutosDB;
import br.edu.impacta.entities.Produto;
import br.edu.impacta.entities.R;

/**
 * Created by Administrador on 02/10/2017.
 */

public class CadastroProdutoActivity extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produto);

        Button button = (Button)findViewById(R.id.botaoCadastro);
        button.setOnClickListener(onClickCadastrar());

        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                EditText txtNome = (EditText)findViewById(R.id.nomeProduto);
                EditText txtCategoria = (EditText)findViewById(R.id.categoria);
                EditText txtValor = (EditText)findViewById(R.id.valorProduto);

                txtNome.setText(params.get("nomeProduto").toString());
                txtCategoria.setText(params.get("categoria").toString());
                txtValor.setText(params.get("valor").toString());

                id = params.getInt("id");
            }
        }
    }

    private View.OnClickListener onClickCadastrar(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nome = (EditText)findViewById(R.id.nomeProduto);
                EditText categoria = (EditText)findViewById(R.id.categoria);
                EditText valor = (EditText)findViewById(R.id.valorProduto);

                Produto produto = new Produto(nome.getText().toString(), categoria.getText().toString(), Double.valueOf(valor.getText().toString()));
                ProdutosDB produtoDB = new ProdutosDB(CadastroProdutoActivity.this);

                if(id != 0 ){
                    produto.setId(id);
                }

                produtoDB.save(produto);

                Toast.makeText(CadastroProdutoActivity.this, "Cadastrado com sucesso.", Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        };
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_remove, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int idMenu = item.getItemId();
        if (idMenu == R.id.action_remover) {
            ProdutosDB produtoDB = new ProdutosDB(CadastroProdutoActivity.this);

            Produto produto = new Produto();
            produto.setId(this.id);
            produtoDB.delete(produto);

            Toast.makeText(CadastroProdutoActivity.this, "Removido com sucesso.", Toast.LENGTH_SHORT).show();

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
