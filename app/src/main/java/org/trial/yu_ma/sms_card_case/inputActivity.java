package org.trial.yu_ma.sms_card_case;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

//スタティックインポート
import static org.trial.yu_ma.sms_card_case.MainActivity.textSwitch;

public class inputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //画面遷移したときにキーボードを表示
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

    public void compbt(View view) {

        //入力したメッセージをプリファレンスに登録
        String str;
        EditText et = (EditText)findViewById(R.id.inputtext);
        str = et.getText().toString();
        SharedPreferences sp = getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();
        spEdit.putString( textSwitch, str );
        spEdit.commit();

        this.finish();
    }

}
