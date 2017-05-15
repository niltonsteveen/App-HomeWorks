package prototipo.udea.edu.co.homeworks.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prototipo.udea.edu.co.homeworks.R;

/**
 * Created by nilto on 15/05/2017.
 */

public class FragmentVacio extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paginado, container, false);
        return view;
    }
}
