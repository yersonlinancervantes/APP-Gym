package com.example.appgimnasio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appgimnasio.repositories.local.Usuario.SessionManagement;
import com.example.appgimnasio.repositories.remote.ServiceFactory;
import com.example.appgimnasio.repositories.remote.request.ImageService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CargarImagenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CargarImagenFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CargarImagenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CargarImagenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CargarImagenFragment newInstance(String param1, String param2) {
        CargarImagenFragment fragment = new CargarImagenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView imgUpload;
    String rutaImagen = "";
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    int idUsuario = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cargar_imagen, container, false);


        Button btnCargarImagen = v.findViewById(R.id.btnCargarImagen);
        Button btnGuardarImagen = v.findViewById(R.id.btnGuardarImagen);
        imgUpload = v.findViewById(R.id.imgUpload);

        SessionManagement  sessionManagement = new SessionManagement(getActivity());

        idUsuario = sessionManagement.getIdUserSession();

        btnCargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamara();
            }
        });

        btnGuardarImagen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(rutaImagen == ""){
                    Toast.makeText(getContext(),"Debe cargar una imagen",Toast.LENGTH_LONG).show();
                }else{
                    showProgressDialog();
                    File file = new File(rutaImagen);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                    RequestBody idUsuarioP = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(idUsuario));

                    ImageService jsonPlaceHolderApi = ServiceFactory.retrofit.create(ImageService.class);
                    Call<ResponseBody> call = jsonPlaceHolderApi.updateUserImage(image,idUsuarioP);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println("Response =>"+response);
                            if(!response.isSuccessful()){
                                Toast.makeText(getContext(),"NO SE PUDO GUARDAR LA IMAGEN",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getContext(),"SE GUARDO CORRECTAMENTE LA IMAGEN!",Toast.LENGTH_LONG).show();
                            }
                            hideProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
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

    private MultipartBody.Part prepareImagePart(String path, String partName){
        File file = new File(path);
        RequestBody requestBody =  RequestBody.create(MediaType.parse(getContext().getContentResolver().getType(Uri.fromFile(file))),file);
        return MultipartBody.Part.createFormData(partName,file.getName(),requestBody);
    }

    private void openCamara(){
        Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if(intent.resolveActivity(getPackageManager()) != null){
        File imagenArchivo = null;
        try{
            imagenArchivo = crearImagen();
        }catch (IOException ex){
            Log.e("Error",ex.toString());
        }

        if(imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(getContext(),"com.example.appgimnasio.fileprovider",imagenArchivo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intent,1);
        }
        //}
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1) {
            //Bundle extras = data.getExtras();
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
            imgUpload.setImageBitmap(imgBitmap);
        }else{
            rutaImagen = "";
        }
    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen,".jpg",directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}