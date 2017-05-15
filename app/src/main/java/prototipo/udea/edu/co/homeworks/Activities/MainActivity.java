package prototipo.udea.edu.co.homeworks.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import prototipo.udea.edu.co.homeworks.Fragments.FragmentVacio;
import prototipo.udea.edu.co.homeworks.Fragments.FragmentoActividadesMain;
import prototipo.udea.edu.co.homeworks.Fragments.PreFragConf;
import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.ConfigParent;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.UsuarioWS;
import prototipo.udea.edu.co.homeworks.WebServices.builders.RestClientBuilder;
import prototipo.udea.edu.co.homeworks.utils.NotificationSender;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnLogOut;
    private FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;
    Usuario usuario;
    private TextView tvEmailHeader;
    private TextView tvNombreHeader;
    private CircleImageView imgRol;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras=getIntent().getExtras();
        usuario=extras.getParcelable("Usuario");


        setToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //asignaciones del header segun el usuario y rol
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Roboto.ttf");
        tvNombreHeader=(TextView)navigationView.getHeaderView(0).findViewById(R.id.tvNameHeader);
        tvNombreHeader.setTypeface(face);
        tvNombreHeader.setText(usuario.getNombres()+" "+usuario.getApellidos());
        face=Typeface.createFromAsset(getAssets(),"fonts/Roboto_Regular.ttf");
        tvEmailHeader=(TextView) navigationView.getHeaderView(0).findViewById(R.id.tvEmailHeader);
        tvEmailHeader.setTypeface(face);
        tvEmailHeader.setText(usuario.getEmail());
        imgRol=(CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imgRol);
        if(usuario.getRol().equals("PROFESOR")){
            imgRol.setImageResource(R.drawable.docente);
        }else{
            imgRol.setImageResource(R.drawable.padres);
        }


        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }

        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("token", "Refreshed token: " + refreshedToken);
        usuario.setToken(refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(usuario);
/*
        mAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null && usuario==null){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };

       /* btnLogOut=(Button) findViewById(R.id.button2);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });*/
    }

    private void sendRegistrationToServer(Usuario  user) {
       final UsuarioWS userWs= RestClientBuilder.restUsuario();

        userWs.updateUser(user,  new Callback<Usuario>() {
            @Override
            public void success(Usuario usuario, Response response) {
                    Log.e("tokenupdated", usuario.getToken());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("fallo1", error.getMessage());
            }
        });
        //final String token = user.getToken();
        //final String message ="mensaje de prueba app to app";
       // NotificationSender.sendNotification(token, message);
    }


    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
      //  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentoActividadesMain actividadesMain=new FragmentoActividadesMain();;
        switch (itemDrawer.getItemId()) {
            case R.id.nav_activity:
                getIntent().putExtra("Usuario",usuario);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, actividadesMain).commit();
                break;
            /*case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new FragmentVacio()).commit();
                PreFragConf preFragConf=new PreFragConf();
                getFragmentManager().beginTransaction().replace(R.id.main_content, preFragConf).commit();
                break;*/
        }
        setTitle(itemDrawer.getTitle());

        /*FragmentoCuenta fragmentoCuenta = null;
        ListSolicitudesFragment fragmentoSolicitud = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {

            case R.id.item_cuenta:
                fragmentoCuenta = new FragmentoCuenta();
                break;
            case R.id.item_solicitudes:
                getIntent().putExtra("Usuario",usuario);
                fragmentoSolicitud = new ListSolicitudesFragment();
                break;
          /*  case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
        }
        if (fragmentoCuenta != null) {
            fragmentoCuenta.setUserLoged(usuario);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragmentoCuenta)
                    .commit();
        }
        if (fragmentoSolicitud != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_content, fragmentoSolicitud)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());*/
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }*/
}
