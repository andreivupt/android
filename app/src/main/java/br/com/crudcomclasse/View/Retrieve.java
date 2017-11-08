package br.com.crudcomclasse.View;

import android.view.View;
import android.widget.Toast;

/**
 * Created by andrei.vupt on 07/11/2017.
 */

public class Retrieve implements View.OnLongClickListener{

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(v.getContext(), "Um item da lista foi clicado: "+v.getId(), Toast.LENGTH_LONG).show();
        return false;
    }
}
