package com.example.thiago.aplicacaobanconoite.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.thiago.aplicacaobanconoite.model.Contato;
import com.example.thiago.aplicacaobanconoite.dao.ContatoDAO;
import com.example.thiago.aplicacaobanconoite.R;

public class ListarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readDataBase();
    }

    private void readDataBase(){

        ContatoDAO dao = new ContatoDAO(this);

        Cursor cursor = dao.listCursor();

        String[] from = {"_id", "nome", "cpf", "email"};
        int[] to = {R.id.txtId, R.id.txtNome, R.id.txtCpf, R.id.txtEmail};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_contatos_layout, cursor, from, to);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inserir_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuItemInserir:
                Intent intent = new Intent(this, InserirActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ContatoDAO dao = new ContatoDAO(this);
        Contato contato = dao.getContato(l);
        Intent intent = new Intent(this, InserirActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("contato", contato);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
