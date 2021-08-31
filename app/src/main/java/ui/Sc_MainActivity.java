package ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import data.AND;
import data.GameState;
import data.HMGame;

public class Sc_MainActivity extends AppCompatActivity {

    private static Context context;
    private AND objectOfAndroid;
    private Button Ans_Bt;
    private  EditText edTxt;
    private TextView txtGuessW;
    private Dialog dialog;
    private Intent intent;
    private ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.second_activtiy);

        Ans_Bt = findViewById(R.id.bt_ans);

        edTxt = findViewById(R.id.guessinput);
        edTxt.addTextChangedListener(txtW);

        im = findViewById(R.id.tries);

        TextView txtHint = findViewById(R.id.txt_hintword);
        txtGuessW = findViewById(R.id.txt_wordtoguess);

        context = this;
        int level = getIntent().getIntExtra("Level",0);
        String wordType = getIntent().getExtras().getString("WordType");

        objectOfAndroid = new AND(new HMGame(),level,wordType);
        objectOfAndroid.startTheGame();

        txtGuessW.setText(objectOfAndroid.getHiddenWord());
        txtHint.setText(objectOfAndroid.getHintWord());

    }
    public static Context getContext(){
        return context;
    }

    private final TextWatcher txtW = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Ans_Bt.setEnabled(!edTxt.getText().toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void GoAHeadFun(View view) {

        objectOfAndroid.setNextGuess(edTxt.getText().toString());
        objectOfAndroid.play();
        DoChanges();

    }
    private void DoChanges(){
        txtGuessW.setText(objectOfAndroid.getHiddenWord());
        drawPartOfBody(objectOfAndroid.getPartOfBody());
        if(objectOfAndroid.getPlayerState() == GameState.lose || objectOfAndroid.getPlayerState() == GameState.win) Pop_upResult();
    }
    private void drawPartOfBody(int i){
        if(i == 0)  return;
        switch (i)
        {
            case 1: { changeImage(R.drawable.fir_try); break;}
            case 2: { changeImage(R.drawable.sec_try); break;}
            case 3: { changeImage(R.drawable.thi_try); break;}
            case 4: { changeImage(R.drawable.four_try); break;}
            case 5: { changeImage(R.drawable.fif_try); break;}
            case 6: { changeImage(R.drawable.six_try); break;}
            case 7: { changeImage(R.drawable.sev_try); break;}
            case 8: { changeImage(R.drawable.eig_try); break;}
            case 9: { changeImage(R.drawable.nin_try); break;}
            case 10:{ changeImage(R.drawable.ten_try); break;}
            default:break;
        }
    }
    private void changeImage(int id){
        im.setImageResource(id);
    }

    private void  INI_Dialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_up);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

    }
    private void  Pop_upResult(){

        INI_Dialog();

        ImageView im = dialog.findViewById(R.id.IM_pop);;
        switch (objectOfAndroid.getPlayerState())
        {
            case win: { im.setImageResource(R.drawable.youwin); break;  }
            case lose:{ im.setImageResource(R.drawable.youlose); break; }
            default: return;
        }

        Button bt_exit,bt_replay;
        bt_exit = dialog.findViewById(R.id.bt_exit);
        bt_replay = dialog.findViewById(R.id.bt_playagain);
        bt_exit.setOnClickListener(closeApp);
        bt_replay.setOnClickListener(replay);

        dialog.show();
    }
    private final View.OnClickListener closeApp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    };
    private final View.OnClickListener replay = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(getContext(),MainActivity.class);
            dialog.dismiss();
            finish();
            startActivity(intent);
        }
    };
}
