package com.example.apppresentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.apppresentacion.interfaces.botons_menu_principal;
import com.example.libreriainsertar.InsertarCita;

public class Inicio2 extends AppCompatActivity
        implements  botons_menu_principal, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mManager;

    private static int timeout=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     /*   FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        Fragment fragment=new frag_principal();
        getSupportFragmentManager().beginTransaction().add(R.id.content_inicio2,fragment).commit();


/*

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                String nombres=intent.getStringExtra("nombres");
                String direccion=intent.getStringExtra("direccion");
                int telefono=intent.getIntExtra("telefono",-1);
                String usuarios=intent.getStringExtra("usuarios");
                String password=intent.getStringExtra("password");
                String id_cliente = intent.getStringExtra("id_cliente");
                String id_usuario = intent.getStringExtra("id_usuario");

                Intent intentdato =new Intent(Inicio2.this,Perfil_Cliente.class);
                  intentdato.putExtra("nombres",nombres);
                  intentdato.putExtra("direccion",direccion);
                  intentdato.putExtra("telefono",telefono);
                  intentdato.putExtra("usuarios",usuarios);
                  intentdato.putExtra("password",password);
                  intentdato.putExtra("id_usuario",id_usuario);
                  intentdato.putExtra("id_cliente",id_cliente);
                startActivity(intentdato);
            }
        },timeout);
*/

    }

    public void NavTitulos(String titulo){
        getSupportActionBar().setTitle(titulo);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Inicio2.this,R.style.AlertD);
        builder.setMessage("¿Deseas salir de la aplicación?");
        builder.setCancelable(true);
        builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();;
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio2, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;


        if (id == R.id.nav_home) {
            miFragment = new frag_principal();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_gallery) {
            miFragment = new frag_list_mascota();
            fragmentSeleccionado = true;
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_inicio2,miFragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void iniciar_frag_add_mascota() {
        Fragment frag_add_masc = new frag_add_mascota();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_inicio2,frag_add_masc).commit();
    }

    @Override
    public void iniciar_insertarcita() {

        Fragment iniciar_insertarcita = new InsertarCita();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_inicio2,iniciar_insertarcita).commit();

    }


}
