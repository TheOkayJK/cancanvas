package com.jonahkallen.cancanvas;

import android.net.Uri;
import android.os.Bundle;

public class NoteKicker extends com.google.androidbrowserhelper.trusted.LauncherActivity {

    @Override
    protected Uri getLaunchingUrl() {
        return Uri.parse("https://canvas.apps.chrome/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}