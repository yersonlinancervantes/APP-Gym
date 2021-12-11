package com.example.appgimnasio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appgimnasio.entity.Disciplina;
import com.example.appgimnasio.entity.ResponseDisciplina;
import com.example.appgimnasio.repositories.local.ControlPorcino.ControlPorcinoSQLiteManager;
import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.DisciplinaSevice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarDisciplinaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarDisciplinaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListarDisciplinaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarDisciplinaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarDisciplinaFragment newInstance(String param1, String param2) {
        ListarDisciplinaFragment fragment = new ListarDisciplinaFragment();
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
            //Toast.makeText(getActivity(),"ID =>"+id,Toast.LENGTH_SHORT).show();
        }
    }

    private ListView listViewDisciplina;
    private ControlPorcinoSQLiteManager controlPorcinoSQLiteManager;
    SessionManagement sessionManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_listar_disciplina, container, false);
        sessionManagement = new SessionManagement(getActivity());
        listViewDisciplina = v.findViewById(R.id.listViewDisciplina);

        Button btnRegistrarDisciplina = v.findViewById(R.id.btnNuevaDisciplina);
        List<Disciplina> listDisciplina = new ArrayList<Disciplina>();
        String cliente = sessionManagement.getNameUseSession();
        final AdapterListViewDisciplina adapter = new AdapterListViewDisciplina(getContext(),getActivity(),listDisciplina);

        DisciplinaSevice jsonPlaceHolderApi = ServiceFactory.retrofit.create(DisciplinaSevice.class);
        Call<ResponseDisciplina> call = jsonPlaceHolderApi.listar(cliente);
        call.enqueue(new Callback<ResponseDisciplina>() {
            public void onResponse(Call<ResponseDisciplina> call, Response<ResponseDisciplina> response) {


                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"OCURRIO UN ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show();
                }
                else{
                    ResponseDisciplina responseDisciplina = response.body();
                    for(Disciplina disciplina: responseDisciplina.getDisciplinas()) {
                        listDisciplina.add(disciplina);
                    }
                }

                listViewDisciplina.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseDisciplina> call, Throwable t) {
                Toast.makeText(getActivity(),"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        listViewDisciplina.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnRegistrarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listarDisciplina_to_registrarDisciplina);
            }
        });

        return v;
    }
}