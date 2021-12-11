package com.example.appgimnasio;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appgimnasio.entity.Reserva;
import com.example.appgimnasio.entity.ResponseReserva;
import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.ReservaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarReservaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarReservaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListarReservaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarReservaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarReservaFragment newInstance(String param1, String param2) {
        ListarReservaFragment fragment = new ListarReservaFragment();
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

    private ListView listViewPorcinos;
    SessionManagement sessionManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listar_reserva, container, false);
        listViewPorcinos = v.findViewById(R.id.listPorcinos);
        sessionManagement = new SessionManagement(getActivity());
        Button btnNuevaReserva = v.findViewById(R.id.btnNuevaReserva);

        String cliente = sessionManagement.getNameUseSession();

        btnNuevaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listarPorcino_to_registrarPorcinoFragment);
            }

        });
        List<Reserva> listReserva = new ArrayList<Reserva>();
        final AdapterListViewReserva adapter = new AdapterListViewReserva(getContext(),getActivity(),listReserva);

        ReservaService jsonPlaceHolderApi = ServiceFactory.retrofit.create(ReservaService.class);
        Call<ResponseReserva> call = jsonPlaceHolderApi.listar(cliente);
        call.enqueue(new Callback<ResponseReserva>() {
            public void onResponse(Call<ResponseReserva> call, Response<ResponseReserva> response) {


                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"OCURRIO UN ERROR EN EL SERVIDOR",Toast.LENGTH_LONG).show();
                }
                else{
                    ResponseReserva responseReserva = response.body();
                    for(Reserva reserva: responseReserva.getReservas()) {
                        listReserva.add(reserva);
                    }
                }

                listViewPorcinos.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseReserva> call, Throwable t) {
                Toast.makeText(getActivity(),"ERROR: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}