package com.example.appgimnasio;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgimnasio.entity.ResponseReserva;
import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.ReservaService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarReservaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarReservaFragment extends Fragment implements  AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrarReservaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarReservaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarReservaFragment newInstance(String param1, String param2) {
        RegistrarReservaFragment fragment = new RegistrarReservaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ProgressDialog progressDialog;
    String cliente = "";
    String turno = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrar_reserva, container, false);

        SessionManagement sessionManagement = new SessionManagement(getActivity());
        cliente = sessionManagement.getNameUseSession();

        Spinner spnServicio = v.findViewById(R.id.spnServicio);
        TextView txtFechaReserva = v.findViewById(R.id.txtFechaReserva);

        Spinner spnHora = v.findViewById(R.id.spnHora);
        final List<String> list = new ArrayList<String>();
        ArrayAdapter<String> adapterSpnHora = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        adapterSpnHora.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHora.setAdapter(adapterSpnHora);

        Spinner spnTurno = v.findViewById(R.id.spnTurno);
        ArrayAdapter<CharSequence> adapterSpnTurno = ArrayAdapter.createFromResource(getContext(), R.array.spnArrTurno, android.R.layout.simple_spinner_item);
        adapterSpnTurno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTurno.setAdapter(adapterSpnTurno);
        spnTurno.setOnItemSelectedListener(RegistrarReservaFragment.this);

        spnTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                turno = parentView.getItemAtPosition(position).toString();

                list.clear();
                if( turno.trim().equals("Mañana")){
                    list.add("5 a.m");
                    list.add("6 a.m");
                    list.add("7 a.m");
                    list.add("8 a.m");
                    list.add("9 a.m");
                    list.add("10 a.m");
                    list.add("11 a.m");
                    list.add("12 a.m");
                }else if(turno.trim().equals("Tarde")){
                    list.add("1 p.m");
                    list.add("2 p.m");
                    list.add("3 p.m");
                    list.add("4 p.m");
                    list.add("5 p.m");
                    list.add("6 p.m");
                }else if(turno.trim().equals("Noche")){
                    list.add("7 p.m");
                    list.add("8 p.m");
                    list.add("9 p.m");
                    list.add("10 p.m");
                    list.add("11 p.m");
                }
                adapterSpnHora.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        Button btnRegistrarReserva = v.findViewById(R.id.btnRegistrarReserva);

        txtFechaReserva.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "-" + month + "-" +year ;
                txtFechaReserva.setText(date);
            }
        };

        btnRegistrarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtFechaReserva.getText().toString().equals("")){
                    Toast.makeText(getContext(),"El campo fecha está vacio.",Toast.LENGTH_LONG).show();
                }else{
                    showProgressDialog();

                    String cliente = sessionManagement.getNameUseSession();
                    String servicio = spnServicio.getSelectedItem().toString();
                    String fechaNacimiento =  txtFechaReserva.getText().toString();
                    String hora = spnHora.getSelectedItem().toString();

                    ReservaService jsonPlaceHolderApi = ServiceFactory.retrofit.create(ReservaService.class);
                    Call<ResponseReserva> call = jsonPlaceHolderApi.registrar(cliente,servicio,fechaNacimiento,turno,hora);

                    call.enqueue(new Callback<ResponseReserva>() {
                        @Override
                        public void onResponse(Call<ResponseReserva> call, Response<ResponseReserva> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getContext(),"NO SE PUDO GUARDAR LA RESERVA",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getContext(),"SE GUARDO CORRECTAMENTE LA RESERVA!",Toast.LENGTH_LONG).show();
                                Navigation.findNavController(v).navigate(R.id.action_registrarPorcinoFragment_to_listarPorcino);
                            }
                            hideProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<ResponseReserva> call, Throwable t) {
                            hideProgressDialog();
                            Toast.makeText(getContext(),"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        return v;
    }



    private void showProgressDialog(){
        progressDialog = new ProgressDialog(getContext());

        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(
                R.color.transparent
        );
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void hideProgressDialog(){
        progressDialog.dismiss();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}