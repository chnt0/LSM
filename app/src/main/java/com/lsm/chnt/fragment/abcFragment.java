package com.lsm.chnt.fragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsm.chnt.AdapterDatos;
import com.lsm.chnt.R;

import java.util.Arrays;
import java.util.List;

@SuppressLint("ValidFragment")
public class abcFragment extends Fragment {

    private int idDatos;
    private int idVideos;
    List<String> listDatos;
    List<String> listvideos;
    RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_abc,null);
        recycler=vista.findViewById(R.id.recycler_id);
        recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext(),LinearLayoutManager.VERTICAL,false));



        Resources res = getResources();
        listDatos= Arrays.asList(res.getStringArray(idDatos));
        listvideos=Arrays.asList(res.getStringArray(idVideos));
        AdapterDatos adapter= new AdapterDatos(listDatos,listvideos);
        recycler.setAdapter(adapter);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   view.findViewById(R.id.textView);
      //  Toast.makeText(getActivity(),"text values",Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ValidFragment")
    public abcFragment(int idDatos, int idVideos) {
        this.idDatos = idDatos;
        this.idVideos = idVideos;
    }
}
