package com.example.en_dec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.*;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    public void clear(View view)
    {
        EditText t = (EditText) findViewById(R.id.editText);
        t.setText("");
    }
    public void paste(View view)
    {
        ClipboardManager manager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData p=manager.getPrimaryClip();
        ClipData.Item i=p.getItemAt(0);
        String str=i.getText().toString();
        EditText t = (EditText) findViewById(R.id.editText);
        t.setText(str);
    }
    public void send(View view) {
        EditText t = (EditText) findViewById(R.id.editText);
        String s = String.valueOf(t.getText());
        int n = s.length();
        char[] en = new char[n];
        for (int i=0; i<n; i++) {
            en[i] = s.charAt(i);
        }
        for (int i = 0; i<n; i++) {
            if (en[i] != 32 ) {
                if (i % 2 == 0) {
                    en[i] += 4;
                } else {
                    en[i] -= 4;
                }
            }
        }
        s = "";
        for (int i = 0; i < n; i++) {
            s += en[i];
        }
        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText(s);



    PackageManager pm = MainActivity.this.getPackageManager();
        try

    {
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
        waIntent.setPackage("com.whatsapp");
        waIntent.putExtra(Intent.EXTRA_TEXT, s);
        startActivity(Intent.createChooser(waIntent, "Share with"));

    }
        catch(
    PackageManager.NameNotFoundException e)

    {
        makeText(MainActivity.this, "Whatsapp not installed", Toast.LENGTH_SHORT).show();
    }

          }//end of send
    public void decrypt(View view)
    {
        EditText t = (EditText) findViewById(R.id.editText);
        String s=String.valueOf(t.getText());
        int n = s.length();
        char[] en = new char[n];
        for (int i=0; i<n; i++) {
            en[i] = s.charAt(i);
        }
        for (int i = 0; i <n; i++) {
            if (en[i] != 32 ) {
                if (i % 2 == 0) {
                    en[i] -= 4;
                } else {
                    en[i] += 4;
                }
            }
        }
        s = "";
        for (int i = 0; i < n; i++) {
            s += en[i];
        }
        TextView tv=(TextView) findViewById(R.id.textView);
        tv.setText(s);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
