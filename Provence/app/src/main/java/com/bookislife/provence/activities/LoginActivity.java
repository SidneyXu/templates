package com.bookislife.provence.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bookislife.provence.AppException;
import com.bookislife.provence.Config;
import com.bookislife.provence.MaterialActivity;
import com.bookislife.provence.R;
import com.bookislife.provence.managers.UserManager;
import com.bookislife.provence.models.User;
import com.bookislife.provence.utils.ToastHelper;

public class LoginActivity extends BaseActivity {

    private UserManager userManager;

    private EditText emailEditText;
    private EditText passwordEditText;
    private TextInputLayout emailTextInputLayout;
    private TextInputLayout passwordTextInputLayout;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userManager = new UserManager(this);
        User currentUser = userManager.getCurrentUser();
        if (currentUser != null) {
            startNoteListActivity();
        }

        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.email_input_layout);
        emailTextInputLayout.setErrorEnabled(true);
        emailTextInputLayout.setHint(getString(R.string.hint_email));
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_input_layout);
        passwordTextInputLayout.setErrorEnabled(true);
        passwordTextInputLayout.setHint(getString(R.string.hint_password));
        signUpTextView = (TextView) findViewById(R.id.signup_text_view);

        if (Config.IS_TEST) {
            emailEditText.setText("foo");
            passwordEditText.setText("bar");
        }

        String label1 = getString(R.string.msg_signup1);
        Spanned label2 = Html.fromHtml("<a href='javascript:' style='text-color:white'>" + getString(R.string.msg_signup2) + "</a>");

        SpannableString label = new SpannableString(label1 + "\t" + label2.toString());
        label.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (!validate()) {
                    return;
                }
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                try {
                    userManager.register(email, password);
                    startNoteListActivity();
                } catch (AppException e) {
                    ToastHelper.with(context).show(e.getMessage());
                }
            }
        }, label1.length() + 1, label.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());
        signUpTextView.setText(label);

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    return;
                }
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                try {
                    userManager.login(email, password);
                    startNoteListActivity();
                } catch (AppException e) {
                    ToastHelper.with(context).show(e.getMessage());
                }
            }
        });
    }

    private void startNoteListActivity(){
        startActivity(NoteListActivity.class);
        finish();
    }

    private boolean validate() {
        boolean error = false;
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        emailTextInputLayout.setError("");
        passwordTextInputLayout.setError("");

        if (TextUtils.isEmpty(email)) {
            emailTextInputLayout.setError(getString(R.string.err_require_email));
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            passwordTextInputLayout.setError(getString(R.string.err_require_password));
            error = true;
        }
        return !error;
    }

}
