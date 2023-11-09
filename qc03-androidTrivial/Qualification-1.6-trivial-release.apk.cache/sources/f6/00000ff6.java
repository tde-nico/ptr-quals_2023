package org.esa.ptr23.qualification;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText txPassword = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(org.esa.ptr23.qualification.trivial.R.layout.activity_main);
        installActionBarLogo();
        Log.i(TAG, String.format("Challenge Difficulty: %s", BuildConfig.FLAVOR));
    }

    private void installActionBarLogo() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(org.esa.ptr23.qualification.trivial.R.string.app_title);
        supportActionBar.setDisplayOptions(supportActionBar.getDisplayOptions() | 16);
        ImageView imageView = new ImageView(supportActionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setImageResource(org.esa.ptr23.qualification.trivial.R.drawable.logo);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(dpValue(150), -1, 8388629);
        layoutParams.rightMargin = 0;
        layoutParams.topMargin = dpValue(12);
        layoutParams.bottomMargin = dpValue(12);
        imageView.setLayoutParams(layoutParams);
        supportActionBar.setCustomView(imageView);
    }

    private int dpValue(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.txPassword = (EditText) findViewById(org.esa.ptr23.qualification.trivial.R.id.txPassword);
    }

    public void verifyPasswordClick(View view) {
        if (!Verifier.verifyPassword(this, this.txPassword.getText().toString())) {
            Toast.makeText(this, (int) org.esa.ptr23.qualification.trivial.R.string.dialog_failure, 1).show();
        } else {
            showSuccessDialog();
        }
    }

    private void showSuccessDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setTitle(org.esa.ptr23.qualification.trivial.R.string.dialog_title);
        dialog.setContentView(org.esa.ptr23.qualification.trivial.R.layout.dialog_success);
        dialog.show();
    }
}