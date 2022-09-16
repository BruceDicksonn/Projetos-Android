package com.example.apprecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] items = new String[]{
                "Elevation",
                "Ripple",
                "StateListAnimator",
                "Floating Action Button + Snackbar",
                "CoordinatorLayout",
                "CardView",
                "RecyclerView",
                "RecyclerView Add/Remove",
                "Reveal Effect",
                "Pallete",
                "Activity Transition",
        };

        ListView listView = new ListView(this);
        // adaptar o listView com meu array de nomes dos planetas
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items));


        setContentView(listView);//inicia a activity mostrando somente meu list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    switch (i) {
                        case 0:
                            Intent intent;
                            intent = new Intent(getApplicationContext(),Exemplo_Recycler_View.class);
                            startActivity(intent);
                            break;
                        case 1:
                            //show(new Intent(this, ExemploRecyclerViewAddRemoveActivity.class));
                            //break;
                        default:
                            finish();
                            break;
                    }
                } catch (Exception e) {
                    //   Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}