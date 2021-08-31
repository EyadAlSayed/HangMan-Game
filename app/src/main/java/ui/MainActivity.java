package ui;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import clickHandler.OnLevelClick;

public class MainActivity extends AppCompatActivity {

    private static Context context;
    private static RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Configuration config = getResources().getConfiguration();
        setContentView(R.layout.activity_main);





        context = this;

        radioGroup = findViewById(R.id.rdG);
        radioGroup.setOnCheckedChangeListener(radioChecker);

        radioButton = findViewById(R.id.ar_rdBtn);

        setOnClick(findViewById(R.id.bt_Easy));
        setOnClick(findViewById(R.id.bt_Normal));
        setOnClick(findViewById(R.id.bt_Hard));
    }
    private void setOnClick(Button bt)
    {
        bt.setOnClickListener(new OnLevelClick());
    }
    public static Context getContext(){
        return context;
    }
    private final RadioGroup.OnCheckedChangeListener radioChecker = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
             radioButton = findViewById(checkedId);
             findViewById(R.id.bt_Easy).setEnabled(true);
             findViewById(R.id.bt_Normal).setEnabled(true);
             findViewById(R.id.bt_Hard).setEnabled(true);
        }
    };
    public static String getCheckedButton()
    {
        return radioButton.getText().toString();
    }

}


