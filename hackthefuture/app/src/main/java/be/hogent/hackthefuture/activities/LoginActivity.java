package be.hogent.hackthefuture.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.Service;
import be.hogent.hackthefuture.domein.Researcher;
import be.hogent.hackthefuture.domein.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener, Callback<Token>{

    private final static String TAG = "LoginActivity";

    private EditText username, password;
    private Button btnLogin;
    private TextView lblRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);

        lblRegister=(TextView)findViewById(R.id.lblRegister);
        lblRegister.setOnClickListener(this);

        btnLogin=(Button)findViewById(R.id.email_sign_in_button);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_sign_in_button:
                login();
                break;
            case R.id.lblRegister:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    private void login(){
        String u = username.getText().toString();
        String p = password.getText().toString();

        if (!u.isEmpty() && !p.isEmpty()){

            Researcher researcher = new Researcher(u,p);

            Service service = Connectie.createService(Service.class);
            Call<Token> res = service.startMission(researcher);

            res.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<Token> call, Response<Token> response) {

        Log.i(TAG, "code: " + response.code());
        Log.i(TAG, "msg: " + response.message());

        switch (response.code()){
            case Connectie.CONTENT_CREATED:
                String token = response.body().token;
                Log.i(TAG, "token: " + token);
                Connectie.token = token;
                break;
            default:
                Toast.makeText(this, "wrong code", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Token> call, Throwable t) {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }
}

