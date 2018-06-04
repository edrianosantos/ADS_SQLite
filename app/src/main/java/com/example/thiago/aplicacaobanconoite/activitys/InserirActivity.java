package com.example.thiago.aplicacaobanconoite.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thiago.aplicacaobanconoite.model.Contato;
import com.example.thiago.aplicacaobanconoite.dao.ContatoDAO;
import com.example.thiago.aplicacaobanconoite.R;
import com.example.thiago.aplicacaobanconoite.util.Util;

public class InserirActivity extends AppCompatActivity {

    private Button btnInserir;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtCpf;
    private Contato contatoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCpf = findViewById(R.id.edtCpf);

        btnInserir = findViewById(R.id.btnInserir);

        contatoBanco = (Contato)getIntent().getSerializableExtra("contato");

        if(contatoBanco != null){
            edtNome.setText(contatoBanco.getNome());
            edtEmail.setText(contatoBanco.getEmail());
            edtCpf.setText(contatoBanco.getCpf());
        }

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private Contato novoContato(){
        return new Contato(edtNome.getText().toString(), edtCpf.getText().toString(), edtEmail.getText().toString());
    }

    private void salvar() {
        Contato contato = novoContato();
        ContatoDAO dao = new ContatoDAO(this);
        dao.save(contato);
        Util.showToast(this, "Contato Inserido com Sucesso!");
        finish();
    }

    private void editar(){
        if(contatoBanco != null){
            Contato contato = novoContato();
            contato.set_id(contatoBanco.get_id());
            ContatoDAO dao = new ContatoDAO(this);
            dao.update(contato);
            Util.showToast(this, "Contato Editado com Sucesso!");
            finish();
        }
    }

    private void deletar(){
        if(contatoBanco != null){
            ContatoDAO dao = new ContatoDAO(this);
            dao.delete(contatoBanco);
            Util.showToast(this, "Contato Deletado com Sucesso!");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(contatoBanco != null){
            getMenuInflater().inflate(R.menu.menu_inserir_layout, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem inserir = menu.findItem(R.id.menuItemInserir);
        MenuItem editar = menu.findItem(R.id.menuItemEditar);
        MenuItem deletar = menu.findItem(R.id.menuItemDeletar);

        if(inserir != null && editar != null && deletar != null){
            inserir.setVisible(false);
            editar.setVisible(true);
            deletar.setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){

            case R.id.menuItemEditar:
                editar();
                break;

            case R.id.menuItemDeletar:
                deletar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
