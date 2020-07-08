package com.example.fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IButtonsFragmentListener,IForResult {

ArrayList <String> elements2 = new ArrayList<>();
    private ArrayList<String> resultArray = new ArrayList<>();


int i1 = 0;
    private static final int REQUEST = 42;
    public static final String RESULT_KEY = "result_key";
    String saved = "" ;
    String safe = "History:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(ButtonsFragment.newInstance("",""));

        Button btnresult = findViewById(R.id.sendText);
        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(safe);
            }
        });
    }

    @Override
    public void onFirstButtonClick() {
        FragmentManager manager = getSupportFragmentManager();
        TextViewFragment textFragment = (TextViewFragment) manager.findFragmentById(R.id.textView_fragment);
        textFragment.changeFirst("THIS IS NEW TEXT");

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,ButtonsFragment.newInstance("",""));
        transaction.commit();
    }

    @Override
    public void onSecondButtonClick() {
        FragmentManager manager = getSupportFragmentManager();
        TextViewFragment textFragment = (TextViewFragment) manager.findFragmentById(R.id.textView_fragment);
        elements2.add("new");
        //System.out.println(elements2);
        //for (int i = i1; i<elements2.size();i++){
          //  textFragment.adapter.History(elements2.get(i));
           // i1+=1;
        //}

        //textFragment.changeSecond("news");

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,TextViewFragment.newInstance("",""));
        transaction.commit();
    }

    private void change (String text, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        TextViewFragment textFragment = (TextViewFragment) manager.findFragmentById(R.id.textView_fragment);
      //  textFragment.changeSecond(text);

        changeFragment(fragment);
    }

    private  void  changeFragment (Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }



    @Override
    public void openCalculator() {
        Intent intent = new Intent(MainActivity.this,Calculator.class);
        intent.putExtra(RESULT_KEY, saved);
        startActivityForResult(intent,REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK){
            FragmentManager manager = getSupportFragmentManager();
            TextViewFragment textFragment2 = (TextViewFragment) manager.findFragmentById(R.id.textView_fragment);
            Log.d("new","AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            String text = data.getStringExtra(Calculator.RETURN_RESULT);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA:" + text);
            resultArray.add(text);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA:"+ resultArray);
            textFragment2.adapter.History(text);
            for (int i = i1; i <resultArray.size(); i++){
                safe =safe + resultArray.get(i) + ";" ;
                i1+=1;
            }
        }
    }
    public void share(String text) {
        final Intent intent = new Intent();
        intent.setAction(intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(intent.EXTRA_TEXT, text);
        startActivity(intent.createChooser(intent, saved));
    }
}