package com.aaup.mokhlesturkman.mapapi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aaup.mokhlesturkman.mapapi.WeatherAPIpkg.Weather;
import com.aaup.mokhlesturkman.mapapi.WeatherAPIpkg.WeatherResponse;
import com.aaup.mokhlesturkman.mapapi.WeatherAPIpkg.WeatherService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public TextView mWeatherDesc;
    public TextView mLocation;
    public TextView mTemp;
    public TextView mWeather;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mWeatherDesc = findViewById(R.id.weather_desc);
        mLocation = findViewById(R.id.location);
        mTemp= findViewById(R.id.temp);
        mWeather = findViewById(R.id.weather);
    }

    //Weather Service Fetch.
    private void FetchWeather(int lat, int lon){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);

        service.get("ad0630440f0795c3b86778def40529bf",lat+"",lon+"").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                //Handle Error
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"ERROR ",Toast.LENGTH_LONG);

                }
                try{
                    Log.d("RESPONSEeRROR",response.errorBody().string());

                } catch (Exception err){
                    Log.d("ResponseErrror",err.getMessage());
                }
                //Successful Response !
                Double temp=response.body().getmMain().getmTemp();
                Double Celsius = temp-273.15;
                String mTempreture = Math.round(Celsius)+ " " + "℃";
                mTemp.setText(mTempreture);
                List<Weather> mList = response.body().getmWeather();
                String mListWeather =  mList.get(0).getmMain();
                String mListWeatherDescription = mList.get(0).getmDescription();
                mWeather.setText(mListWeather);
                mWeatherDesc.setText(mListWeatherDescription);







            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                LatLng mCoor = new LatLng(latLng.latitude,latLng.longitude);
                int lat = (int)latLng.latitude;
                int lon = (int)latLng.longitude;
                FetchWeather(lat,lon);

                mMap.addMarker(new MarkerOptions().position(mCoor).title("Marker Set"));
               // mCoordinates.setText(mCoor.latitude+""+mCoor.longitude);
                mLocation.setText("  ");





            }
        });


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
