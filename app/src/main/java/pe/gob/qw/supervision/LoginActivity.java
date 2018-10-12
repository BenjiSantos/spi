package pe.gob.qw.supervision;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.gob.qw.supervision.api.QWService;
import pe.gob.qw.supervision.api.RetrofitClient;
import pe.gob.qw.supervision.api.model.LoginBody;
import pe.gob.qw.supervision.data.DaoSession;
import pe.gob.qw.supervision.data.Postor;
import pe.gob.qw.supervision.data.Usuario;
import pe.gob.qw.supervision.util.Constant;
import pe.gob.qw.supervision.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.FondoImageView)
    ImageView mFondoImageView;
    @BindView(R.id.VersionNumberTextView)
    TextView mVersionNumberTextView;
    @BindView(R.id.LogoImageView)
    ImageView mLogoImageView;
    @BindView(R.id.user)
    AutoCompleteTextView mUser;
    @BindView(R.id.password)
    TextInputEditText mPassword;
    @BindView(R.id.passwordLayout)
    TextInputLayout mPasswordLayout;
    @BindView(R.id.LoginButton)
    Button mLoginButton;
    @BindView(R.id.LoginBoxRelativeLayout)
    RelativeLayout mLoginBoxRelativeLayout;
    @BindView(R.id.LoginAVL)
    AVLoadingIndicatorView mLoginAVL;
    @BindView(R.id.LoginTextView)
    TextView mLoginTextView;
    @BindView(R.id.ProgressView)
    LinearLayout mProgressView;

    private QWService mQWService;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById(R.id.LoginButton).setOnClickListener(this);

        ButterKnife.bind(this);

        mQWService = RetrofitClient.getRetrofit().create(QWService.class);

        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //i == R.id.login ||
                if (i == EditorInfo.IME_NULL) {
                    if (!Util.isOnline(LoginActivity.this)) {
                        Toast.makeText(LoginActivity.this, R.string.error_network, Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    attemptLogin();
                    return true;
                }

                return false;
            }
        });

        //loadVersionNumber();
    }

    private void attemptLogin() {

        mUser.setError(null);
        mPassword.setError(null);

        String user = mUser.getText().toString().toUpperCase();
        String password = mPassword.getText().toString().toUpperCase();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(user)) {
            mUser.setError(getString(R.string.error_user_required));
            focusView = mUser;
            cancel = true;
        } else if (!isUserIdValid(user)) {
            mUser.setError(getString(R.string.error_invalid_user));
            focusView = mUser;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.error_password_required));
            focusView = mPassword;
            cancel = true;
        } else if (!isPasswordValid(password)){
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showMessage(getString(R.string.sign_in_message));
            showProgress(true);
            Call<Usuario> loginCall = mQWService.login(new LoginBody(user, password));
            loginCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if (!response.isSuccessful()) {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos (1)", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body() == null) {
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos (2)", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body().getICodUsuario() <= 0){
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos (3)", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.errorBody() != null){
                        showProgress(false);
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos (4)", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        //daoSession.getUsuarioDao().deleteAll();
                        //daoSession.getUsuarioDao().saveInTx(response.body());
                        goToActivity(MenuActivity.class);
                        //SyncData();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    showProgress(false);
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos" + " (" + t.getMessage() + ")", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.LoginButton)
    public void onViewClicked() {
        if (!Util.isOnline(LoginActivity.this)) {
            Toast.makeText(LoginActivity.this, R.string.error_network, Toast.LENGTH_SHORT).show();
        } else {

            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.PERMISSION_WES);
                return;
            }

            attemptLogin();
        }
    }

    private void goToActivity(Class activity){
        Intent intent= new Intent(this,activity);
        startActivity(intent);
        finish();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginBoxRelativeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginBoxRelativeLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginBoxRelativeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginBoxRelativeLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private boolean isUserIdValid(String user) {
        return user.length() > 3;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 3;
    }

    private void showMessage(String msg) {
        mLoginTextView.setText(msg);
    }

    private void showAlert(String msg) {
        mLoginTextView.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void syncProviders() {
        showMessage(getString(R.string.sync_providers));
        showProgress(true);
        Call<Postor> postorCall = mQWService.getPostor(2); // TODO ICODUT IS HARDCODE CURRENTLY, PARAMETER SINCE DB HERE!
        postorCall.enqueue(new Callback<Postor>() {
            @Override
            public void onResponse(Call<Postor> call, Response<Postor> response) {

                if (!response.isSuccessful()) {
                    showProgress(false);
                    showAlert(getString(R.string.error_sync_providers) + " (1)");
                }
                else if (response.body() == null) {
                    showProgress(false);
                    showAlert(getString(R.string.error_sync_providers) + " (2)");
                }
                /*else if (response.body().getICodUsuario() <= 0){
                    showProgress(false);
                    showAlert(getString(R.string.error_sync_providers) + " (3)");
                }*/
                else if (response.errorBody() != null){
                    showProgress(false);
                    showAlert(getString(R.string.error_sync_providers) + " (4)");
                }
                else {

                    //daoSession.getUsuarioDao().deleteAll();
                    //daoSession.getUsuarioDao().saveInTx(response.body());
                    goToActivity(MenuActivity.class);
                    //SyncData();
                }
            }

            @Override
            public void onFailure(Call<Postor> call, Throwable t) {
                showProgress(false);
                Toast.makeText(LoginActivity.this, "Usuario o contraseña inválidos" + " (" + t.getMessage() + ")", Toast.LENGTH_SHORT).show();
            }
        });
    }

}