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

import com.example.appgimnasio.entity.Reserva;
import com.example.appgimnasio.entity.ResponseReserva;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.ReservaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterListViewReserva extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    Activity activity;
    List<Reserva> listReserva;

    public AdapterListViewReserva(Context context, Activity activity,List<Reserva> listReserva){
        this.context = context;
        this.activity = activity;
        this.listReserva = listReserva;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent){

        final View vista = inflater.inflate(R.layout.row_lv_reserva,null);

        TextView txtCliente = vista.findViewById(R.id.txtCliente);
        TextView txtServicio = vista.findViewById(R.id.txtServicio);
        TextView txtFecha = vista.findViewById(R.id.txtFecha);
        TextView txtTurno = vista.findViewById(R.id.txtTurno);
        TextView txtHora = vista.findViewById(R.id.txtHora);

        Button btnAnular = vista.findViewById(R.id.btnAnular);

        txtCliente.setText("Cliente: "+listReserva.get(i).getCliente());
        txtServicio.setText("Servicio: "+listReserva.get(i).getServicio());
        txtFecha.setText("Fecha: "+listReserva.get(i).getFecha());
        txtTurno.setText("Turno: "+listReserva.get(i).getTurno());
        txtHora.setText("Hora: "+listReserva.get(i).getHora());

        int idReserva = listReserva.get(i).getId();

        btnAnular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Anular Reserva");
                builder.setMessage("¿Esta seguro que desea anular la reserva?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ReservaService jsonPlaceHolderApi = ServiceFactory.retrofit.create(ReservaService.class);
                        Call<ResponseReserva> call = jsonPlaceHolderApi.anular(idReserva);
                        call.enqueue(new Callback<ResponseReserva>() {
                            public void onResponse(Call<ResponseReserva> call, Response<ResponseReserva> response) {

                                if(!response.isSuccessful()){
                                    Toast.makeText(activity,"OCURRIO UN ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    ResponseReserva responseReserva = response.body();

                                    if(responseReserva.getCode() == 1){
                                        listReserva.remove(i);
                                        notifyDataSetChanged();
                                        Toast.makeText(activity,"SE ANULO LA RESERVA CORRECTAMENTE",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(activity,"HUBO UN PROBLEMA AL ANULAR LA RESERVA, INTENTELO NUEVAMENTE POR FAVOR.",Toast.LENGTH_LONG).show();
                                    }

                                }


                            }

                            @Override
                            public void onFailure(Call<ResponseReserva> call, Throwable t) {
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
        return listReserva.size();
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
