package jp.ac.fjbseisa;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnityFragment extends Fragment implements View.OnClickListener {


    public UnityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unity, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UnityPlayer unityPlayer = ((UnityPlayerActivity)getActivity()).getUnityPlayer();
        ViewGroup parent = (ViewGroup) unityPlayer.getParent();
        if(parent != null)
            parent.removeView(unityPlayer);
        ((FrameLayout)view.findViewById(R.id.unity)).addView(unityPlayer);

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
