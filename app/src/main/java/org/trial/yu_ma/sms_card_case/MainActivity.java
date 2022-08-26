package org.trial.yu_ma.sms_card_case;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /***************************************************
     * グローバル変数
     */

    //startActivityForResult の第２引数用の定義（整数値）
    private final int MY_REQUEST_CODE_1 = 1;
    private final int MY_REQUEST_CODE_2 = 2;
    private final int MY_REQUEST_CODE_3 = 3;
    private final int MY_REQUEST_CODE_4 = 4;
    private final int MY_REQUEST_CODE_5 = 5;
    private final int MY_REQUEST_CODE_6 = 6;

    //宛先用の変数（初期値は初回起動時に表示される）
    public String setName = "No_Name";
    public String setNumber = "No_Number";

    //メッセージ用の変数（初期値は初回起動時に表示される）
    public String text01 = "メッセージはありません";
    public String text02 = "メッセージはありません";
    public String text03 = "メッセージはありません";
    public String text04 = "メッセージはありません";
    public String text05 = "メッセージはありません";

    //メッセージ入力画面へ渡すスイッチ用変数
    public static String textSwitch = "";

    /*************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //プリファレンスによる宛先の読み込み
        SharedPreferences sp = getSharedPreferences("mypref", MODE_PRIVATE);
        setName = sp.getString("toname", setName);
        setNumber = sp.getString("adress", setNumber);
        Button bt = (Button) findViewById(R.id.btn01);
        bt.setText("宛先：" + setName);

        //電話番号非表示化によるコメントアウト
        //bt.setText("宛先：" + setName + "\n" + setNumber);

        //プリファレンスによるメッセージの読み込み
        text01 = sp.getString("text01", text01);
        TextView tv1 = (TextView) findViewById(R.id.tView41);
        tv1.setText(text01);

        text02 = sp.getString("text02", text02);
        TextView tv2 = (TextView) findViewById(R.id.tView42);
        tv2.setText(text02);

        text03 = sp.getString("text03", text03);
        TextView tv3 = (TextView) findViewById(R.id.tView43);
        tv3.setText(text03);

        text04 = sp.getString("text04", text04);
        TextView tv4 = (TextView) findViewById(R.id.tView44);
        tv4.setText(text04);

        text05 = sp.getString("text05", text05);
        TextView tv5 = (TextView) findViewById(R.id.tView45);
        tv5.setText(text05);

    }

    //メニューの追加
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, 0, 0, "メッセージＡ");
        menu.add(Menu.NONE, 1, 1, "メッセージＢ");
        menu.add(Menu.NONE, 2, 2, "メッセージＣ");
        menu.add(Menu.NONE, 3, 3, "メッセージＤ");
        menu.add(Menu.NONE, 4, 4, "メッセージＥ");
        menu.add(Menu.NONE, 5, 5, "初期化");

        return super.onCreateOptionsMenu(menu);

    }

    //メニューを選択した時に呼ばれるメソッド
    public boolean onOptionsItemSelected(MenuItem mi) {

        switch (mi.getItemId()) {

            case 0:
                //スイッチの設定
                textSwitch = "text01";
                //インテントにより入力用アクティビティへ遷移
                Intent it1 = new Intent(getApplicationContext(), inputActivity.class);
                startActivityForResult(it1, MY_REQUEST_CODE_2);
                break;

            case 1:
                //スイッチの設定
                textSwitch = "text02";
                //インテントにより入力用アクティビティへ遷移
                Intent it2 = new Intent(getApplicationContext(), inputActivity.class);
                startActivityForResult(it2, MY_REQUEST_CODE_3);
                break;

            case 2:
                //スイッチの設定
                textSwitch = "text03";
                //インテントにより入力用アクティビティへ遷移
                Intent it3 = new Intent(getApplicationContext(), inputActivity.class);
                startActivityForResult(it3, MY_REQUEST_CODE_4);
                break;

            case 3:
                //スイッチの設定
                textSwitch = "text04";
                //インテントにより入力用アクティビティへ遷移
                Intent it4 = new Intent(getApplicationContext(), inputActivity.class);
                startActivityForResult(it4, MY_REQUEST_CODE_5);
                break;

            case 4:
                //スイッチの設定
                textSwitch = "text05";
                //インテントにより入力用アクティビティへ遷移
                Intent it5 = new Intent(getApplicationContext(), inputActivity.class);
                startActivityForResult(it5, MY_REQUEST_CODE_6);
                break;

            case 5:
                //確認画面の表示
                AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                alertbox.setMessage("初期化しますか？");

                alertbox.setPositiveButton("する", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        //初期化処理
                        SharedPreferences sp = getSharedPreferences("mypref", MODE_PRIVATE);
                        sp.edit().clear().commit();
                        Button bt = (Button) findViewById(R.id.btn01);
                        bt.setText("宛先：" + "No_Name");

                        TextView tv1 = (TextView) findViewById(R.id.tView41);
                        tv1.setText("メッセージはありません");

                        TextView tv2 = (TextView) findViewById(R.id.tView42);
                        tv2.setText("メッセージはありません");

                        TextView tv3 = (TextView) findViewById(R.id.tView43);
                        tv3.setText("メッセージはありません");

                        TextView tv4 = (TextView) findViewById(R.id.tView44);
                        tv4.setText("メッセージはありません");

                        TextView tv5 = (TextView) findViewById(R.id.tView45);
                        tv5.setText("メッセージはありません");
                    }
                });

                alertbox.setNegativeButton("やめる", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //ダイアログの終了
                        dialog.dismiss();
                    }
                });

                //ダイアログの生成と表示のメソッド
                alertbox.show();

                break;
        }

        return true;
    }

    /*********************************************************
     *   タッチイベント（デフォルトのＳＭＳアプリを呼び出し、電話番号と本文をセット）
     */

    public void cView01(View view) {

        TextView tv = (TextView) findViewById(R.id.tView41);
        String str = tv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //電話番号の指定
        intent.setData(Uri.parse("sms:" + setNumber));

        //本文の指定
        intent.putExtra("sms_body", str);

        //Activityの起動
        startActivity(intent);

        //Toast.makeText(this,"01_タップされました", Toast.LENGTH_SHORT).show();
    }

    public void cView02(View view) {

        TextView tv = (TextView) findViewById(R.id.tView42);
        String str = tv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //電話番号の指定
        intent.setData(Uri.parse("sms:" + setNumber));

        //本文の指定
        intent.putExtra("sms_body", str);

        //Activityの起動
        startActivity(intent);

        //Toast.makeText(this,"02_タップされました", Toast.LENGTH_SHORT).show();
    }

    public void cView03(View view) {

        TextView tv = (TextView) findViewById(R.id.tView43);
        String str = tv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //電話番号の指定
        intent.setData(Uri.parse("sms:" + setNumber));

        //本文の指定
        intent.putExtra("sms_body", str);

        //Activityの起動
        startActivity(intent);

        //Toast.makeText(this, "03_タップされました", Toast.LENGTH_SHORT).show();
    }

    public void cView04(View view) {

        TextView tv = (TextView) findViewById(R.id.tView44);
        String str = tv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //電話番号の指定
        intent.setData(Uri.parse("sms:" + setNumber));

        //本文の指定
        intent.putExtra("sms_body", str);

        //Activityの起動
        startActivity(intent);

        //Toast.makeText(this, "04_タップされました", Toast.LENGTH_SHORT).show();
    }

    public void cView05(View view) {

        TextView tv = (TextView) findViewById(R.id.tView45);
        String str = tv.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        //電話番号の指定
        intent.setData(Uri.parse("sms:" + setNumber));

        //本文の指定
        intent.putExtra("sms_body", str);

        //Activityの起動
        startActivity(intent);

        //Toast.makeText(this, "05_タップされました", Toast.LENGTH_SHORT).show();
    }

    /**************************************************************
     *
     *   宛先変更のボタンイベント
     *
     * */

    public void cbtn01(View view) {

        //インテントにより電話帳（引用）を起動
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        //MY_REQUEST_CODE はどの（自分の発行した）インテントから戻ってきたのかを判別する為の識別番号のようなもの（１以上を設定※０は startActivity と等価らしい）
        startActivityForResult(intent, MY_REQUEST_CODE_1);
    }

    //startActivityForResult によりインテント先からの情報を onActivityResult が受け取る（インテントを受け取った時に onActivityResult が呼び出される）
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch ( requestCode ) {

            case 1:
                if (data != null) {
                    Uri uri = data.getData();

                    if (uri != null) {
                        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                        Cursor cur = getContentResolver().query(uri, projection, null, null, null);

                        if (cur != null) {
                            cur.moveToFirst();
                            String displayName = cur.getString(0);
                            String number = cur.getString(1);

                            //連絡帳アプリで指定した宛先をグローバル変数にセット
                            setName = displayName;
                            setNumber = number;

                            //プリファレンスによる宛先の保存
                            SharedPreferences sp = getSharedPreferences("mypref", MODE_PRIVATE);
                            SharedPreferences.Editor spEdit = sp.edit();
                            spEdit.putString("toname", setName);
                            spEdit.putString("adress", setNumber);
                            spEdit.commit();

                            //宛先表示部分に情報をセット
                            Button bt = (Button) findViewById(R.id.btn01);
                            bt.setText("宛先：" + displayName);
                            
                            //電話番号非表示化によるコメントアウト
                            //bt.setText("宛先：" + displayName + "\n" + number);

                            cur.close();
                        }
                    }
                }
                break;

            /**********************************************************************************
             *
             * 入力画面で入力したメッセージをプリファレンスからテキストビューにセット
             *
             */

            case 2:
                SharedPreferences sp1 = getSharedPreferences("mypref", MODE_PRIVATE);
                text01 = sp1.getString("text01", text01);
                TextView tv1 = (TextView) findViewById(R.id.tView41);
                tv1.setText(text01);
                break;

            case 3:
                SharedPreferences sp2 = getSharedPreferences("mypref", MODE_PRIVATE);
                text02 = sp2.getString("text02", text01);
                TextView tv2 = (TextView) findViewById(R.id.tView42);
                tv2.setText(text02);
                break;

            case 4:
                SharedPreferences sp3 = getSharedPreferences("mypref", MODE_PRIVATE);
                text03 = sp3.getString("text03", text01);
                TextView tv3 = (TextView) findViewById(R.id.tView43);
                tv3.setText(text03);
                break;

            case 5:
                SharedPreferences sp4 = getSharedPreferences("mypref", MODE_PRIVATE);
                text04 = sp4.getString("text04", text01);
                TextView tv4 = (TextView) findViewById(R.id.tView44);
                tv4.setText(text04);
                break;

            case 6:
                SharedPreferences sp5 = getSharedPreferences("mypref", MODE_PRIVATE);
                text05 = sp5.getString("text05", text01);
                TextView tv5 = (TextView) findViewById(R.id.tView45);
                tv5.setText(text05);
                break;

        }
    }
}
