package com.example.appgimnasio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgimnasio.entity.Disciplina;
import com.example.appgimnasio.entity.ResponseDisciplina;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.DisciplinaSevice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListViewDisciplina extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    Activity activity;
    List<Disciplina> listDisciplina;

    public AdapterListViewDisciplina(Context context, Activity activity, List<Disciplina> listDisciplina){
        this.context = context;
        this.activity = activity;
        this.listDisciplina = listDisciplina;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent){

        final View vista = inflater.inflate(R.layout.row_lv_disciplina,null);

        TextView txtCliente = vista.findViewById(R.id.txtClienteDisciplina);
        TextView txtDisciplina = vista.findViewById(R.id.txtDisciplina);
        TextView txtPeriodo = vista.findViewById(R.id.txtPeriodo);
        TextView txtProfesor = vista.findViewById(R.id.txtProfesor);
        TextView txtHorario = vista.findViewById(R.id.txtHorario);
        Button btnAnularDisciplina = vista.findViewById(R.id.btnAnularDisciplina);

        txtCliente.setText("Cliente: "+listDisciplina.get(i).getCliente());
        txtDisciplina.setText("Disciplina: "+listDisciplina.get(i).getDisciplina());
        txtPeriodo.setText("Periodo: "+listDisciplina.get(i).getPeriodo());
        txtProfesor.setText("Profesor: "+listDisciplina.get(i).getProfesor());
        txtHorario.setText("Horario: "+listDisciplina.get(i).getHorario());

        int idDisciplina = listDisciplina.get(i).getId();

        btnAnularDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Anular Disciplina");
                builder.setMessage("¿Esta seguro que desea anular la disciplina?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DisciplinaSevice jsonPlaceHolderApi = ServiceFactory.retrofit.create(DisciplinaSevice.class);
                        Call<ResponseDisciplina> call = jsonPlaceHolderApi.anular(idDisciplina);
                        call.enqueue(new Callback<ResponseDisciplina>() {
                            public void onResponse(Call<ResponseDisciplina> call, Response<ResponseDisciplina> response) {
                                if(!response.isSuccessful()){
                                    Toast.makeText(activity,"OCURRIO UN ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    ResponseDisciplina responseDisciplina = response.body();
                                    if(responseDisciplina.getCode() == 1){
                                        listDisciplina.remove(i);
                                        notifyDataSetChanged();
                                        Toast.makeText(activity,"SE ANULO LA RESERVA CORRECTAMENTE",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(activity,"HUBO UN PROBLEMA AL ANULAR LA RESERVA, INTENTELO NUEVAMENTE POR FAVOR.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseDisciplina> call, Throwable t) {
                                Toast.makeText(activity,"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return vista;
    }

    @Override
    public int getCount(){
        return listDisciplina.size();
    }

    @Override
    public Object getItem(int position){

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
