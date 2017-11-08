package br.com.crudcomclasse.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.crudcomclasse.Adapter.DataBaseAdapter;
import br.com.crudcomclasse.Model.Contato;

/**
 * Created by andrei.vupt on 07/11/2017.
 */

public class ContatoController extends DataBaseAdapter {

    public ContatoController(Context context) {
        super(context);
    }

    public boolean create(Contato contato){
        ContentValues values = new ContentValues();

        values.put("nome", contato.getNome());
        values.put("email", contato.getEmail());

        SQLiteDatabase db = this.getReadableDatabase();

        boolean isCreate = db.insert("contato",null,values) > 0;
        db.close();

        return isCreate;
    }

    public int totalDeContatos(){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM contato";

        int cont = db.rawQuery(sql, null).getCount();

        return cont;
    }

    public List<Contato> contatoList() {

        List<Contato> contatos = new ArrayList<>();

        String sql = "SELECT * FROM contato ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                int id  = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String email = cursor.getString(cursor.getColumnIndex("id"));

                Contato contato = new Contato();

                contato.setEmail(email);
                contato.setId(id);
                contato.setNome(nome);

                contatos.add(contato);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return contatos;
    }

    public Contato buscarPeloID(int contatoID) {
        return null;
    }

    public boolean update(Contato contato) {
        return true;
    }

    public boolean delete(int contatoID) {
        return true;
    }
}
