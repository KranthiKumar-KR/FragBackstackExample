package com.example.cyma.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyma.myapplication.fragments.FragmentA;
import com.example.cyma.myapplication.fragments.FragmentB;

public class MainActivity extends AppCompatActivity implements FragmentA.OnFragmentInteractionListener, FragmentB.OnFragmentInteractionListener{
    android.app.FragmentManager fm;
    TextView tv;
    Button clearBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();
        tv = (TextView) findViewById(R.id.scrollingtext);
        clearBS = (Button) findViewById(R.id.clearBS);
        clearBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBS();
            }
        });
    }
    public void addA(View view) {
        FragmentA fA = new FragmentA();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.group1, fA, getString(R.string.A));
        ft.addToBackStack(getString(R.string.addA));
        ft.commit();

    }
    public void addB(View view) {
        FragmentB fB = new FragmentB();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.group1, fB, getString(R.string.B));
        ft.addToBackStack(getString(R.string.addB));
        ft.commit();

    }
    public void removeA(View view) {
        FragmentA fA = (FragmentA) fm.findFragmentByTag("A");
        FragmentTransaction ft = fm.beginTransaction();
        if (fA != null) {
            ft.remove(fA);
            ft.addToBackStack("removeA");
            ft.commit();
        } else {
            Toast.makeText(this, R.string.failedtoremoveA, Toast.LENGTH_SHORT).show();
        }


    }
    public void removeB(View view) {
        FragmentB fB = (FragmentB) fm.findFragmentByTag("B");
        FragmentTransaction ft = fm.beginTransaction();
        if (fB != null){
            ft.remove(fB);
            ft.addToBackStack("removeB");
            ft.commit();
        } else
            Toast.makeText(this, R.string.failedToRemoveB, Toast.LENGTH_SHORT).show();


    }
    public void replaceWithA(View view) {

            FragmentA fA = new FragmentA();
        if (fA != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.group1, fA, "A");
            ft.addToBackStack("replaceWithA");
            ft.commit();
        } else
            Toast.makeText(this, R.string.fragmentEmpty, Toast.LENGTH_SHORT).show();

    }
    public void replaceWithB(View view) {
        FragmentB fB = new FragmentB();
        if (fB != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.group1, fB, "B");
            ft.addToBackStack("replaceWithB");
            ft.commit();
        } else
            Toast.makeText(this, "fragment manager is empty", Toast.LENGTH_SHORT).show();

    }
    public void clearBS() {
        FragmentManager fm = this.getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public void onFragmentInteractionA() {

    }

    @Override
    public void onFragmentInteractionB() {

    }
}
