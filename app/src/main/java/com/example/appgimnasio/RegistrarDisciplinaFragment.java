package com.example.appgimnasio;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appgimnasio.entity.ResponseDisciplina;
import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.DisciplinaSevice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarDisciplinaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarDisciplinaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrarDisciplinaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarDisciplinaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarDisciplinaFragment newInstance(String param1, String param2) {
        RegistrarDisciplinaFragment fragment = new RegistrarDisciplinaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    int porcinoId = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            porcinoId = Integer.parseInt(getArguments().getString("porcinoId"));
        }
    }

    ProgressDialog progressDialog;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Spinner spnDisciplina;
    Spinner spnPeriodo;
    Spinner spnProfesor;
    Spinner spnHorario;
    SessionManagement sessionManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrar_disciplina, container, false);

        spnDisciplina = v.findViewById(R.id.spnDisciplina);
        spnPeriodo = v.findViewById(R.id.spnPeriodo);
        spnProfesor = v.findViewById(R.id.spnProfesor);
        spnHorario = v.findViewById(R.id.spnHorario);
        sessionManagement = new SessionManagement(getActivity());

        Button btnRegistrarDisciplina = v.findViewById(R.id.btnRegistrarDisciplina);

        btnRegistrarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();

                String cliente = sessionManagement.getNameUseSession();
                String servicio = spnDisciplina.getSelectedItem().toString();
                String periodo = spnPeriodo.getSelectedItem().toString();
                String profesor = spnProfesor.getSelectedItem().toString();
                String horario = spnHorario.getSelectedItem().toString();

                DisciplinaSevice jsonPlaceHolderApi = ServiceFactory.retrofit.create(DisciplinaSevice.class);
                Call<ResponseDisciplina> call = jsonPlaceHolderApi.registrar(cliente,servicio,periodo,profesor,horario);

                call.enqueue(new Callback<ResponseDisciplina>() {
                    @Override
                    public void onResponse(Call<ResponseDisciplina> call, Response<ResponseDisciplina> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(),"NO SE PUDO GUARDAR LA DISCIPLINA",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(),"SE GUARDO CORRECTAMENTE LA DISCIPLINA!",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(v).navigate(R.id.action_registrarDisciplina_to_listarDisciplina);
                        }
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(Call<ResponseDisciplina> call, Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(getContext(),"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
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

}