package clickHandler;

import android.content.Intent;
import android.view.View;

import ui.MainActivity;
import ui.R;
import ui.Sc_MainActivity;

public class OnLevelClick implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        int level = ExtractLevel(v);
        Intent inT = new Intent(MainActivity.getContext(), Sc_MainActivity.class);
        inT.putExtra("Level", level);
        inT.putExtra("WordType",MainActivity.getCheckedButton());
        MainActivity.getContext().startActivity(inT);

    }
    private int ExtractLevel(View view){
        switch (view.getId())
        {
            case R.id.bt_Easy :   { return 1; }
            case R.id.bt_Normal : { return 2; }
            case R.id.bt_Hard :   { return 3; }
            default: return -1;
        }
    }
}
