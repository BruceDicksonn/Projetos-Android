package com.example.appsearchview;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        search = (SearchView) searchItem.getActionView();

        // 3 maneiras de expandir a caixa de pesquisa:

        // 1. Inicia a caixa com a lupa à esquerda e o garfo de fechamento da caixa de pesquisa

        //search.setIconified(false);


        // 2. Inicia a caixa de pesquisa sem o garfo para fechar a caixa de pesquisa e
        // somente quando o user digita algo que ele aparece. Esse modo oculta o nome do app
        // deixando a barra de pesquisa oculpar a maior parte da action bar.

        //search.setIconifiedByDefault(false);

        // 3. Expande a caixa diretamente, mesmo sem  o user clicar, inicialmente começa com a lupa a esquerda
        // sem o garfo, a medida que o user digita, a lupa some e o garfo aparece.

        // search.onActionViewExpanded();


        // Configuraçoes gerais para a SearchView:

        // definir largura max:
        //search.setMaxWidth(500);

        //Definir se o botão de envio( > ) será exibido quando a caixa de pesquisa for expandida
        //search.setSubmitButtonEnabled(true);

        //Configurando prompts de caixa de entrada(dando um hint à barra de pesquisa)
        //search.setQueryHint("Dica");


        // Ouvindo o evendo SearchView

        //Clique no botão back fork quando a caixa de pesquisa se expandir
        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this, "Fechar", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //O evento click do botão do ícone de pesquisa (o botão que abre a caixa de pesquisa)
        search.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Abrir", Toast.LENGTH_SHORT).show();
            }
        });


        //Monitoramento de alteração de texto da caixa de pesquisa
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(MainActivity.this, "Texto enviado", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(MainActivity.this, "Texto sendo alterado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}