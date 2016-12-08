package be.hogent.hackthefuture.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.RegisterActivity;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener{


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

            Toast.makeText(this, "Loging", Toast.LENGTH_SHORT).show();

        }

    }
}

