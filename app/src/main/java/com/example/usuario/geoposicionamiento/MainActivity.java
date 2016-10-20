package com.example.usuario.geoposicionamiento;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        String locationProvider = LocationManager.GPS_PROVIDER;
        String locationProvider2 = LocationManager.NETWORK_PROVIDER;
// Or, use GPS location data:
// String locationProvider = LocationManager.GPS_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);


    }

    private void makeUseOfNewLocation(Location location) {
        double latitud = location.getLatitude();
        double longitud = location.getLongitude();
        double distancia = 0;
        double distanciaMetros= 0;
        ImageView imagen ;
        double bloque = 0;
        double bloqueDos= 0;
        String lugar = null;
        if (latitud == 4.6366017)

        {
         bloque = 4.6366017;
         bloqueDos=-74.0552138;
         lugar = "Bloque L";
        }
        else if( latitud== 4.637007)
        {
            bloque = 4.637007;
            bloqueDos = -74.054714;
            lugar="Bloque I";
        }
        else if (latitud == 4.636418)
        {
            bloque = 4.636418;
            bloqueDos = -74.055428;
            lugar = "Bloque J";
        }
        else if (latitud==4.637141)
        {
            bloque = 4.637141;
            bloqueDos= -74.055353;
            lugar = "Bloque K";
        }
        else if (latitud==4.6364801)
        {
            bloque = 4.6364801;
            bloqueDos= -74.0557007;
            lugar = "Muro de escalar";
        }
        distancia=Math.sqrt(Math.pow((bloque- latitud),2)+ Math.pow((bloqueDos-longitud),2));
        distanciaMetros=distancia*113.319;
        if (distanciaMetros <= 0.005) {
            Toast.makeText(MainActivity.this, lugar, Toast.LENGTH_LONG).show();

            imagen = (ImageView) findViewById(R.id.imageView);
           // imagen.setImageResource(R.drawable.bruja);

        }
       // else   {
         //   Toast.makeText(MainActivity.this,"Frio", Toast.LENGTH_SHORT).show();
        //}
    }


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

    public void onLocationChanged(Location location) {}


    public void onProviderDisabled(String provider) {
        Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);}


    public void onStatusChanged(String provider, int status, Bundle extras) {}


    public void onProviderEnabled(String provider) {}

}
