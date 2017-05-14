package prototipo.udea.edu.co.homeworks.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import prototipo.udea.edu.co.homeworks.R;

public class HeaderDrawer extends AppCompatActivity {

    private TextView tvNombreHeader;
    private TextView tvEmailHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_drawer);

        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Roboto.ttf");

        tvNombreHeader=(TextView) findViewById(R.id.tvNameHeader);
        tvNombreHeader.setTypeface(face);
        tvNombreHeader.setText("nilton");
        face=Typeface.createFromAsset(getAssets(),"fonts/forista.ttf");
        tvEmailHeader=(TextView) findViewById(R.id.tvEmailHeader);
        tvEmailHeader.setTypeface(face);
    }
}
