package be.hogent.hackthefuture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

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

            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();

        }

    }
}
