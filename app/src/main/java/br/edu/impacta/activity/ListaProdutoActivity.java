package br.edu.impacta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.impacta.adapter.ProdutoAdapter;
import br.edu.impacta.db.ProdutosDB;
import br.edu.impacta.entities.Produto;
import br.edu.impacta.entities.R;

/**
 * Created by Administrador on 02/10/2017.
 */

public class ListaProdutoActivity extends AppCompatActivity {

    private ArrayList<Produto> produtos;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produtos);

        criaLista(getProdutos());

        lista.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaProdutoActivity.this, CadastroProdutoActivity.class);

                Bundle params = new Bundle();
                params.putString("nomeProduto", getProdutos().get(i).getNome());
                params.putString("categoria", getProdutos().get(i).getCategoria());
                params.putDouble("valor", getProdutos().get(i).getValor());
                params.putInt("id", getProdutos().get(i).getId());

                intent.putExtras(params);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_adicionar) {
            Intent intent = new Intent(ListaProdutoActivity.this, CadastroProdutoActivity.class);
            startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            criaLista(getProdutos());
        }
    }

    public void criaLista(List<Produto> produtoList){
        lista = (ListView) findViewById(R.id.listaProdutos);
        lista.setAdapter(new ProdutoAdapter(ListaProdutoActivity.this, getProdutos()));
    }

    public ArrayList<Produto> getProdutos() {
        ProdutosDB produtoDB = new ProdutosDB(ListaProdutoActivity.this);
        produtos = produtoDB.findAll();
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
}
