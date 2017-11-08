package br.com.crudcomclasse.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.crudcomclasse.Controller.ContatoController;
import br.com.crudcomclasse.Model.Contato;
import br.com.crudcomclasse.R;
import br.com.crudcomclasse.View.MainActivity;

/**
 * Created by andrei.vupt on 07/11/2017.
 */

public class CreateContato implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.contato_form, null);

        final EditText etContatoNome = (EditText) formElementsView.findViewById(R.id.etNome);
        final EditText etContatoEmail = (EditText) formElementsView.findViewById(R.id.etEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar contato")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                String contatoNome = etContatoNome.getText().toString();
                                String contatoEmail = etContatoEmail.getText().toString();

                                Contato contato = new Contato();
                                contato.setNome(contatoNome);
                                contato.setEmail(contatoEmail);

                                boolean sucess = new ContatoController(context).create(contato);

                                if(sucess){
                                    Toast.makeText(context, "Contato criado com sucesso", Toast.LENGTH_LONG).show();

                                    ((MainActivity) context).contadorDeRegistros();
                                } else {
                                    Toast.makeText(context, "Erro!", Toast.LENGTH_LONG).show();
                                }
                                dialog.cancel();
                            }
                        }).show();
    }
}
