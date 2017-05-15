package prototipo.udea.edu.co.homeworks.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import prototipo.udea.edu.co.homeworks.R;

public class PreFragConf extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferent_fragment_configuracion);

    }
}