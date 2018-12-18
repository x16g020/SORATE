package jp.ac.fjbseisa;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment implements View.OnClickListener {


    public SelectFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.b1).setOnClickListener(this);

        view.findViewById(R.id.b2).setOnClickListener(this);

        view.findViewById(R.id.b3).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {


        if(v.getId()==R.id.b1){
            ((UnityPlayerActivity)getActivity()).changeFragment(UnityFragment.class);
        }else if(v.getId()==R.id.b2){
            ((UnityPlayerActivity)getActivity()).changeFragment(MoonFragment.class);
        }else if(v.getId()==R.id.b3){
            ((UnityPlayerActivity)getActivity()).changeFragment(WeatherFragment.class);
        }

    }

}
