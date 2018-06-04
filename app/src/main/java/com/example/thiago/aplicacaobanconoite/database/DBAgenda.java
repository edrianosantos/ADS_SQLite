package com.example.thiago.aplicacaobanconoite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAgenda extends SQLiteOpenHelper {

    public static final String DB_NAME = "DBAgenda.db";
    public static final int DB_VERSION = 2;

    public DBAgenda(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table contatos(" +
                "_id integer primary key autoincrement, " +
                "nome text, " +
                "cpf text, " +
                "email text)";
        try {
            db.execSQL(sql);
        }catch (SQLiteException e){
            Log.e("DBAgenda", "Error ao criar a tabela contatos: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
