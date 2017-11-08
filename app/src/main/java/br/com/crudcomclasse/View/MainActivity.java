package br.com.crudcomclasse.View;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;
import java.util.List;

import br.com.crudcomclasse.Controller.ContatoController;
import br.com.crudcomclasse.Model.Contato;
import br.com.crudcomclasse.R;

public class MainActivity extends AppCompatActivity {

    Button btnCriarContato;
    TextView tvNumContatos;

    String nome="";
    String cidade="";

    URL url = new URL("https://api.github.com/users/andreivupt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriarContato = (Button) findViewById(R.id.btnCriarContato);
        btnCriarContato.setOnClickListener(new CreateContato());

        tvNumContatos = (TextView) findViewById(R.id.tvContContatos);

        contadorDeRegistros();
        atualizarContatos();
    }

    private void AsynPutData() {
        try {
            new SalvarDados(nome, cidade).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private class SalvarDados
            extends AsyncTask {

        String nome = null;
        String cidade = null;
        Uri.Builder builder;

        public SalvarDados(String nome, String cidade) {

            this.nome = nome;
                this.cidade = cidade;

                builder = new Uri.Builder();

                builder.appendQueryParameter("key", "conteudo");
                builder.appendQueryParameter("nome", nome);
                builder.appendQueryParameter("cidade", cidade);
            }



        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }


    private void atualizarContatos() {

        LinearLayout llListContatos = (LinearLayout) findViewById(R.id.llContatos);
        llListContatos.removeAllViews();

        List<Contato> students = new ContatoController(this).contatoList();

        if (students.size() > 0) {
            for (Contato obj : students) {
                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String tvContents = nome + " - " + email;

                TextView textView = new TextView(this);
                textView.setPadding(0, 10,0,10);
                textView.setText(tvContents);
                textView.setTag(Integer.toString(id));

                llListContatos.addView(textView);
                textView.setOnLongClickListener(new Retrieve());
            }
        }
    }

    public void contadorDeRegistros(){
        String strCont = "";

        int contador = new ContatoController(this).totalDeContatos();

        if (contador == 0){
            strCont = "Nenhum contato cadastrado";
        } else if( contador == 1) {
            strCont = "Contato cadastrado";
        } else {
            strCont = contador + " contatos cadastrados";
        }

        tvNumContatos.setText(strCont);
    }


}
