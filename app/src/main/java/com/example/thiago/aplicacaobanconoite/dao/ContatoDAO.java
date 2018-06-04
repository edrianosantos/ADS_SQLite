package com.example.thiago.aplicacaobanconoite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.thiago.aplicacaobanconoite.database.DBAgenda;
import com.example.thiago.aplicacaobanconoite.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private SQLiteDatabase db;
    private DBAgenda dbAgenda;
    private static final String tag = "ContatoDAO";

    public ContatoDAO(Context context) {
        dbAgenda = new DBAgenda(context);
    }

    public void save(Contato contato) {

        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("cpf", contato.getCpf());
        values.put("email", contato.getEmail());

        try {
            db = dbAgenda.getWritableDatabase();
            db.insert("contatos", null, values);
            db.close();
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao inserir na tabela contatos: " + e.getMessage());
        }
    }

    public Cursor listCursor() {

        Cursor cursor = null;

        try {
            db = dbAgenda.getReadableDatabase();
            cursor = db.query("contatos", null, null, null, null, null, null);
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao listar na tabela contatos: " + e.getMessage());
        }

        return cursor;

    }

    public Contato getContato(long id) {

        Contato contato = null;

        try {
            db = dbAgenda.getReadableDatabase();
            Cursor cursor = db.query("contatos",
                    null,
                    "_id = ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                contato = new Contato();
                contato.set_id(cursor.getLong(cursor.getColumnIndex("_id")));
                contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                contato.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                contato.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao listar na tabela contatos: " + e.getMessage());
        }
        return contato;
    }

    public List<Contato> list() {

        List<Contato> contatos = new ArrayList<Contato>();
        Cursor cursor = null;

        try {
            db = dbAgenda.getReadableDatabase();
            cursor = db.query("contatos", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Contato contato = new Contato();
                contato.set_id(cursor.getLong(cursor.getColumnIndex("_id")));
                contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                contato.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                contato.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                contatos.add(contato);
            }
            db.close();
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao listar na tabela contatos: " + e.getMessage());
        }
        return contatos;
    }

    public void update(Contato contato) {

        String[] whereArgs = {String.valueOf(contato.get_id())};

        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("cpf", contato.getCpf());
        values.put("email", contato.getEmail());

        try {
            db = dbAgenda.getWritableDatabase();
            db.update("contatos", values, "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao alterar na tabela contatos: " + e.getMessage());
        }
    }

    public void delete(Contato contato) {
        String[] whereArgs = {String.valueOf(contato.get_id())};

        try {
            db = dbAgenda.getWritableDatabase();
            db.delete("contatos", "_id=?", whereArgs);
            db.close();
        } catch (SQLiteException e) {
            Log.e(tag, "Erro ao deletar na tabela contatos: " + e.getMessage());
        }
    }
}
