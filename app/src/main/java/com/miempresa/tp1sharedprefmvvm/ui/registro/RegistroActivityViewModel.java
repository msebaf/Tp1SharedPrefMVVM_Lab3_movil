package com.miempresa.tp1sharedprefmvvm.ui.registro;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.miempresa.tp1sharedprefmvvm.Model.Usuario;
import com.miempresa.tp1sharedprefmvvm.request.ApiClient;
import com.miempresa.tp1sharedprefmvvm.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuario;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);



    }

    public LiveData<Usuario> getUsuario(){;
        if(usuario==null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }
    public void usuarioSiONo(boolean bool) {
        if(bool==true){
            usuario.setValue(ApiClient.leer(getApplication().getApplicationContext()));
        }

    }

    public void guardarDatos(String dni, String nombre, String apellido, String mail, String password){
        long DNI=Long.parseLong(dni);
        SharedPreferences prefencias=getApplication().getApplicationContext().getSharedPreferences("datos.xml",0);
        SharedPreferences.Editor editor=prefencias.edit();
        editor.putLong("dni", DNI);
        editor.putString("nombre",nombre);
        editor.putString("apellido",apellido);
        editor.putString("mail",mail);
        editor.putString("password",password);

        editor.commit();
        Intent intent=new Intent(getApplication().getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().getApplicationContext().startActivity(intent);

    }


}
