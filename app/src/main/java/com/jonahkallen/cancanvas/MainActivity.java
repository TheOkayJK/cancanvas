 package com.jonahkallen.cancanvas;

 import android.app.Activity;
 import android.app.AlertDialog;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.net.Uri;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.CheckBox;

 public class MainActivity extends Activity {

    public static final String PREFS_NAME = "Preferences";
    public SharedPreferences settings;
    public CheckBox checkbox;
    public boolean isDialogChecked;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(PREFS_NAME, 0);
        boolean dialogShown = settings.getBoolean("dialogShown", false);

        if (!dialogShown) {
            showStartupMessage();
        } else {
            openCanvas();
            finish();
        }
    }

     private void openCanvas() {
         startActivity(new Intent(this, NoteKicker.class));
     }

     public void showStartupMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setCancelable(false);
        View startupCheckbox = getLayoutInflater().inflate(R.layout.startup_dialog, null);
        checkbox = startupCheckbox.findViewById(R.id.checkbox);
        checkbox.setChecked(false);
        builder.setView(startupCheckbox);

        builder.setPositiveButton(
                R.string.dialog_button_continue,
                (dialog, id) -> {
                        dialog.cancel();
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("dialogShown", true);
                        editor.apply();
                        openCanvas();
                        finish();
                });

        builder.setNegativeButton(
                R.string.dialog_button_help,
                (dialog, id) -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TheOkayJK/cancanvas/blob/main/HELP.md"));
                    startActivity(browserIntent);
                    dialog.cancel();
                    finish();
                });

        AlertDialog alert = builder.create();
        alert.show();
        ((AlertDialog) alert).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        checkbox.setOnCheckedChangeListener((CheckBox.OnCheckedChangeListener) (buttonView, isChecked) -> {
            isDialogChecked = checkbox.isChecked();
            ((AlertDialog) alert).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(isDialogChecked);
        });


     }
 }