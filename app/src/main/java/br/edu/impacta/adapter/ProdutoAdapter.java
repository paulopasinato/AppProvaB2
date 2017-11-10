package br.edu.impacta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.impacta.entities.Produto;

/**
 * Created by paulo on 03/10/2017.
 */

public class ProdutoAdapter extends BaseAdapter {

    Context context;

    public ArrayList<Produto> produtoList = new ArrayList<>();

    public ProdutoAdapter(Context context, ArrayList<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Object getItem(int i) {
        return produtoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Produto produto = produtoList.get(i);
        TextView t = new TextView(context);

        //calcular tamanho em tela de acordo com a densidade
        float dip = 50;
        float densidade = context.getResources().getDisplayMetrics().density;
        int px = (int)(dip * densidade + 0.5f);
        t.setHeight(px);
        t.setText("Produto: " + produto.getNome() +
                "\nCategoria: " + produto.getCategoria() +
                "\nPreço: " + produto.getValor().toString());

        return t;
    }

    public ArrayList<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(ArrayList<Produto> produtoList) {
        this.produtoList = produtoList;
    }
}
