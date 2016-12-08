package be.hogent.hackthefuture.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.activities.LoginActivity;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.Service;
import be.hogent.hackthefuture.domein.Researcher;
import be.hogent.hackthefuture.domein.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Callback<Token>{

    private final static String TAG = "RegisterActivity";

    private EditText username, password;
    private Button btnLogin;

    private TextView lblLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText) findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);

        lblLogin = (TextView)findViewById(R.id.lblLogin);
        lblLogin.setOnClickListener(this);

        btnLogin=(Button)findViewById(R.id.email_sign_in_button);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_sign_in_button:
                register();
                break;
            case R.id.lblLogin:
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    private void register(){

        String u = username.getText().toString();
        String p = password.getText().toString();

        if (!u.isEmpty() && !p.isEmpty()){

            Service service = Connectie.createService(Service.class);

            Researcher researcher = new Researcher(u, p);

            Call<Token> req = service.newResarcher(researcher);
            req.enqueue(this);


            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onResponse(Call<Token> call, Response<Token> response) {
        Log.i(TAG, "code: " + response.code());


        switch (response.code()){
            case Connectie.CONTENT_CREATED:
                String token = response.body().token;
                Log.i(TAG, "token: " + token);
                Connectie.token = token;
                break;
            case Connectie.ALREADY_ON_MISSION:
                Toast.makeText(this, "gebruiker bestaat al", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Token> call, Throwable t) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
