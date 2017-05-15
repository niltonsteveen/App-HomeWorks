package prototipo.udea.edu.co.homeworks.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.UsuarioWS;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnGmail;
    private Button btnIngresar;
    private boolean estado=false;
    private TextInputEditText etEmail;
    private TextInputEditText etPwd;
    private LoginButton btnFacebook;
    private GoogleApiClient mGoogleApiClient;
    public static  final int RC_SIGN_IN=1;
    public static  final String TAG="LOGIN_ACTIVITY";
    private FirebaseAuth mAuth;
    private TextView tvLogin;
    private CallbackManager callbackManager;
    private String url= "https://rest-homeworks.herokuapp.com/api";

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* FacebookSdk.setApplicationId("326764977741775");
        FacebookSdk.sdkInitialize(this.getApplicationContext());*/
        setContentView(R.layout.activity_login);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();

        final UsuarioWS usuarioWS=restAdapter.create(UsuarioWS.class);

        tvLogin=(TextView) findViewById(R.id.tvLogin);
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/forista.ttf");
        tvLogin.setTypeface(face);

        etEmail=(TextInputEditText)findViewById(R.id.etEmail);
        etPwd=(TextInputEditText) findViewById(R.id.etPwd);

        btnIngresar=(Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuarioWS.getUserById(etEmail.getText().toString(), new Callback<Usuario>() {
                    @Override
                    public void success(Usuario usuario, Response response) {
                        if(!usuario.getPassword().equals(etPwd.getText().toString())){
                            Toast.makeText(getBaseContext(), "Contraseña incorrecta",Toast.LENGTH_LONG).show();
                        }else{

                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("Usuario",usuario);
                            startActivity(intent);
                        }

                    }
                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                        Toast.makeText(getBaseContext(), "Usuario no registrado",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

/*
        callbackManager = CallbackManager.Factory.create();
        btnFacebook=(LoginButton) findViewById(R.id.loginButton);
        btnFacebook.setReadPermissions("email", "public_profile");

        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });

        mAuth=FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null&&estado==true) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };

        btnGmail=(Button) findViewById(R.id.btnIngresarGmail);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient= new GoogleApiClient.Builder(getApplicationContext()).enableAutoManage
                (this, new GoogleApiClient.OnConnectionFailedListener(){

                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){
                        Toast.makeText(LoginActivity.this,"Error", Toast.LENGTH_LONG).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();


        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });*/
    }
/*
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        btnFacebook.setVisibility(View.GONE);

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), R.string.firebase_error_login, Toast.LENGTH_LONG).show();
                }

                btnFacebook.setVisibility(View.VISIBLE);
            }
        });
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }*/
}
