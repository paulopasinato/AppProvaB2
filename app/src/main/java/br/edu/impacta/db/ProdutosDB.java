package br.edu.impacta.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.edu.impacta.entities.Produto;

/**
 * Created by Administrador on 06/11/2017.
 */

public class ProdutosDB extends SQLiteOpenHelper {

    // Nome do BD
    public static final String NOME_BANCO = "provab2.sqlite";
    // Versao do BD
    public static final int VERSAO_BANCO = 1;

    public ProdutosDB(Context context){
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists produto (" +
                "_id integer primary key autoincrement, " +
                "nome text, categoria text, " +
                "valor real);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        if (versaoAntiga == 1 && versaoNova == 2) {
            db.execSQL("alter table produto add column nome text;");
            db.execSQL("alter table produto add column categoria text;");
        }
    }

    public long save(Produto produto){
        long id = produto.getId();
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nome", produto.getNome());
            values.put("categoria", produto.getCategoria());
            values.put("valor", produto.getValor());
            if (id != 0) {
                String _id = String.valueOf(id);
                String [] argsFiltro = new String[]{_id};
                int count = db.update("produto", values, "_id=?", argsFiltro);
                return count;
            } else {
                id = db.insert("produto","", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    public int delete(Produto produto){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("produto", "_id=?", new String[]{String.valueOf(produto.getId())});
            return count;
        } finally {
            db.close();
        }
    }

    public ArrayList<Produto> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor c = db.query("produto", null, null, null,
                    null, null, null, null);
            return toList(c);
        } finally {
            db.close();
        }
    }

    private ArrayList<Produto> toList(Cursor c) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        if (c.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produto.setId(c.getInt(c.getColumnIndex("_id")));
                produto.setNome(c.getString(c.getColumnIndex("nome")));
                produto.setCategoria(c.getString(c.getColumnIndex("categoria")));
                produto.setValor(c.getDouble(c.getColumnIndex("valor")));
                produtos.add(produto);
            } while (c.moveToNext());
        }
        return produtos;
    }
}
