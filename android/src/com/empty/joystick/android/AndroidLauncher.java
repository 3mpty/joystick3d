package com.empty.joystick.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.empty.joystick.TouchPadTest;
import com.navdrawer.SimpleSideDrawer;

public class AndroidLauncher extends AndroidApplication {

    private TouchPadTest touchPadTest;
    private Button equipmentButton;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.touchPadTest = new TouchPadTest();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View view = initializeForView(touchPadTest, config);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.game_container);
        container.addView(view, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        final SimpleSideDrawer ssd = new SimpleSideDrawer(this);
        ssd.setLeftBehindContentView(R.layout.side_menu);

        equipmentButton = (Button) findViewById(R.id.btn_equipment);

        if(equipmentButton != null) {
            equipmentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ssd.toggleLeftDrawer();
                }
            });
        }

        final Button btn = (Button)ssd.findViewById(R.id.btn_some);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidLauncher.this, "Some some", Toast.LENGTH_SHORT).show();
                if(touchPadTest.keyUp(Input.Keys.I)) {
                    btn.setText("ON " + touchPadTest.getAmount() + " left");
                } else {
                    btn.setText("OFF");
                }
            }
        });

        Gdx.input.setCatchMenuKey(true);
	}
}
