package com.gleidesilva.bookslibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.oceanbrasil.libocean.Ocean;
import com.oceanbrasil.libocean.control.http.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_book);
        //Efeito de pulsador
       /* setContentView(R.layout.pulsator4droid);
        PulsatorLayout pulsator = (PulsatorLayout) findViewById(R.id.pulsator);
        pulsator.start();*/


        String urlDataJson = "http://gitlab.oceanmanaus.com/snippets/1/raw";
        Ocean.newRequest(urlDataJson, new Request.RequestListener() {
            @Override
            public void onRequestOk(String result, JSONObject jsonObject, int code) {
                Log.d("Request",result);
                if(code == Request.NENHUM_ERROR){
                    Log.d(TAG, "Request: " + result);

                    ArrayList<Book> lista = new ArrayList<>();;

                    if (result != null) {
                        try {
                            JSONObject jsObject = new JSONObject(result);
                            JSONArray arrayOcean = jsObject.getJSONArray("ocean");

                            for (int i = 0;  i < arrayOcean.length(); i++){
                                JSONObject item = arrayOcean.getJSONObject(i);
                                JSONArray livros =  item.getJSONArray("livros");

                                for (int j = 0; j < livros.length(); j++){

                                    JSONObject livro = livros.getJSONObject(j);
                                    String titulo = livro.getString("titulo");
                                    String autor = livro.getString("autor");
                                    int ano = livro.getInt("ano");
                                    int paginas = livro.getInt("paginas");
                                    String capa = livro.getString("capa");

                                    Book mBook = new Book();
                                    mBook.setTitulo(titulo);
                                    mBook.setAutor(autor);
                                    mBook.setAno(ano);
                                    mBook.setPagina(paginas);
                                    mBook.setCapa(capa);

                                    lista.add(mBook);

                                    Log.i(TAG, "Titulo: " + titulo);
                                    Log.i(TAG, "Autor: " + autor);
                                    Log.i(TAG, "Ano: " + String.format("%s",ano));
                                    Log.i(TAG, "pagina: " + String.format("%s",paginas));
                                    Log.i(TAG, "capa: " + j);
                                }
                            }
                            createAdapter(lista);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }).get().send();

    }

    private void createAdapter(ArrayList<Book> lista) {
        MyAdapter adapter = new MyAdapter(this, lista);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recicler_view_book);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hideLoadProgressBar(lista);
    }

    private void hideLoadProgressBar(ArrayList<Book> lista) {
        if (lista.size() > 0){
            progressBar = (ProgressBar) findViewById(R.id.my_progress_bar);
            progressBar.setVisibility(View.GONE);
        }
    }


    /*public ArrayList<Book> iniciaLista(){
        ArrayList<Book> books = new ArrayList<>();
        //String urlBookCover = "http://pngimg.com/upload/book_PNG2118.png";

        Book mBook1 = new Book();
        mBook1.setTitulo("MOODLE 2 para Autores e Tutores - 3ª Edição");
        mBook1.setAutor("Robson Santos da Silva");
        mBook1.setAno(2013);
        mBook1.setPagina(168);
        //mBook1.setCapa("http://172.25.1.17/oceanbook/moodle2.jpg");
        mBook1.setCapa("http://pngimg.com/upload/book_PNG2118.png");
        //mBook1.setCapa(urlBookCover);
        books.add(mBook1);

        Book mBook2 = new Book();
        mBook2.setTitulo("NoSQL Essencial");
        mBook2.setAutor("Pramod J. Sadalage / Martin Fowler");
        mBook2.setAno(2013);
        mBook2.setPagina(216);
        //mBook2.setCapa(urlBookCover);
        mBook2.setCapa("http://pngimg.com/upload/img1415917828.jpg");
        books.add(mBook2);

        Book mBook3 = new Book();
        mBook3.setTitulo("Fundamentos de Bancos de Dados com C#");
        mBook3.setAutor("Michael Schmalz");
        mBook3.setAno(2012);
        mBook3.setPagina(120);
        //mBook3.setCapa(urlBookCover);
        mBook3.setCapa("http://pngimg.com/upload/img1428165014.jpg");
        books.add(mBook3);

        Book mBook4 = new Book();
        mBook4.setTitulo("Jovem e Bem-Sucedido");
        mBook4.setAutor("Juliano Niederauer");
        mBook4.setAno(2013);
        mBook4.setPagina(192);
        //mBook4.setCapa(urlBookCover);
        mBook4.setCapa("http://pngimg.com/upload/book_PNG2107.png");
        books.add(mBook4);

        Book mBook5 = new Book();
        mBook5.setTitulo("Lidando com a Incertezao");
        mBook5.setAutor("Jonathan Fields");
        mBook5.setAno(2013);
        mBook5.setPagina(208);
        //mBook5.setCapa(urlBookCover);
        mBook5.setCapa("http://pngimg.com/upload/book_PNG2105.png");
        books.add(mBook5);

        return books;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
