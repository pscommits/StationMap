package com.example.maps;

import android.os.Bundle;
import android.util.Log;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;

import java.util.List;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MapStyleOptions;





public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //LatLng location = new LatLng(22.5839, 88.3434);
        //googleMap.addMarker(new MarkerOptions().position(location).title("Howrah Station"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(22.583081238080016, 88.342410382103)) // Set the center of the map
                .zoom(18) // Zoom level
                .tilt(45) // Tilt angle
                .build();


        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setIndoorEnabled(true);

        // Set minimum and maximum zoom levels
        googleMap.setMinZoomPreference(17.0f); // Minimum zoom level
        googleMap.setMaxZoomPreference(19.0f); // Maximum zoom level


        // Platform Data
        List<PlatformData> platformDataList = new ArrayList<>();
        platformDataList.add(new PlatformData(new LatLng(22.584546187862163, 88.3425153171344), "Platform 1"));
        platformDataList.add(new PlatformData(new LatLng(22.58438723625509, 88.34258070869373), "Platform 2"));
        platformDataList.add(new PlatformData(new LatLng(22.584139567103495, 88.34263275503979), "Platform 3"));
        platformDataList.add(new PlatformData(new LatLng(22.584093434566817, 88.34228485365641), "Platform 4"));
        platformDataList.add(new PlatformData(new LatLng(22.583948266989673, 88.34241541387168), "Platform 5"));
        platformDataList.add(new PlatformData(new LatLng(22.583948266989673, 88.34207062457077), "Platform 6"));
        platformDataList.add(new PlatformData(new LatLng(22.583679232944103, 88.34225520759871), "Platform 7"));
        platformDataList.add(new PlatformData(new LatLng(22.583791653551, 88.3406284409003), "Platform 8"));
        platformDataList.add(new PlatformData(new LatLng(22.58186026145008, 88.34175217744624), "Platform 9"));
        platformDataList.add(new PlatformData(new LatLng(22.58337222933309, 88.33835472888573), "Platform 10"));
        platformDataList.add(new PlatformData(new LatLng(22.58186139342754, 88.34174989736901), "Platform 11"));
        platformDataList.add(new PlatformData(new LatLng(22.58221598758322, 88.33969540334604), "Platform 12"));
        platformDataList.add(new PlatformData(new LatLng(22.583400867969928, 88.33834252669553), "Platform 13"));

        // Resize the custom icon
        Bitmap bitmapplat = BitmapFactory.decodeResource(getResources(), R.drawable.platform);
        Bitmap smallplatformMarker = Bitmap.createScaledBitmap(bitmapplat, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor platformIcon = BitmapDescriptorFactory.fromBitmap(smallplatformMarker);

        for (PlatformData platformData : platformDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(platformData.getLatLng())
                    .title(platformData.getTitle())
                    .icon(platformIcon));
        }// Custom color

        // Add waiting location markers using List
        List<WaitingLocationData> waitingLocationDataList = new ArrayList<>();
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.584320012019827, 88.34318854274419), "Waiting Location 1"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.58380701067675, 88.34333268604804), "Waiting Location 2"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.58333433793532, 88.34263992460558), "Waiting Location 3"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.582994556268126, 88.34296297478758), "Waiting Location 4"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.583051789397285, 88.3430381327664), "Waiting Location 5"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.58168178169191, 88.34191035478679), "Waiting Location 6"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.581281814704674, 88.34214102476417), "Waiting Location 7"));
        waitingLocationDataList.add(new WaitingLocationData(new LatLng(22.58076730437765, 88.34202501922066), "Waiting Location 9"));

        // Resize the custom icon
        Bitmap bitmapwaiting = BitmapFactory.decodeResource(getResources(), R.drawable.waiting);
        Bitmap smallwaitingMarker = Bitmap.createScaledBitmap(bitmapwaiting, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor waitingIcon = BitmapDescriptorFactory.fromBitmap(smallwaitingMarker);

        // Add markers for each waiting location
        for (WaitingLocationData waitingLocationData : waitingLocationDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(waitingLocationData.getLatLng())
                    .title(waitingLocationData.getTitle())
                    .icon(waitingIcon));
        }

        List<FoodLocationData> foodLocationDataList = new ArrayList<>();
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.583180217633014, 88.34231221134306), "Food Location 1"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.582946280333825, 88.3425666584121), "Food Location 2"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.582909290207965, 88.34267818202616), "Food Location 3"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.58375206270412, 88.34335165471818), "Food Location 4"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.58417194743196, 88.34367864627541), "Food Location 5"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.58198004237453, 88.34247518532877), "Food Location 6"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.581602824004094, 88.34226461058832), "Food Location 7"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.58182985369828, 88.34230748209038), "Food Location 8"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.58146427749408, 88.3421259086699), "Food Location 9"));
        foodLocationDataList.add(new FoodLocationData(new LatLng(22.582441085648448, 88.341597580453), "Food Location 10"));

        // Resize the custom icon
        Bitmap bitmapfood = BitmapFactory.decodeResource(getResources(), R.drawable.food);
        Bitmap smallfoodMarker = Bitmap.createScaledBitmap(bitmapfood, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor foodIcon = BitmapDescriptorFactory.fromBitmap(smallfoodMarker);

        // Add markers for each food location with custom color (Green)
        for (FoodLocationData foodLocationData : foodLocationDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(foodLocationData.getLatLng())
                    .title(foodLocationData.getTitle())
                    //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); // Set marker color to green
                    .icon(foodIcon));  // Use resized custom icon
        }

        List<RestroomLocationData> restroomLocationDataList = new ArrayList<>();
        restroomLocationDataList.add(new RestroomLocationData(new LatLng(22.584076593416246, 88.34350727064493), "Restroom 1"));
        restroomLocationDataList.add(new RestroomLocationData(new LatLng(22.581239575708825, 88.34182706907113), "Restroom 2"));
        restroomLocationDataList.add(new RestroomLocationData(new LatLng(22.582000228573452, 88.34164149236315), "Restroom 3"));
        restroomLocationDataList.add(new RestroomLocationData(new LatLng(22.583081238080016, 88.342410382103), "Restroom 4"));

        // Resize the custom icon
        Bitmap bitmaprestroom = BitmapFactory.decodeResource(getResources(), R.drawable.restroom);
        Bitmap smallrestroomMarker = Bitmap.createScaledBitmap(bitmaprestroom, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor restroomIcon = BitmapDescriptorFactory.fromBitmap(smallrestroomMarker);

        // Add markers for each restroom location with custom color (Orange)
        for (RestroomLocationData restroomLocationData : restroomLocationDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(restroomLocationData.getLatLng())
                    .title(restroomLocationData.getTitle())
                    .icon(restroomIcon));  // Set marker color to orange
        }

        List<StoreLocationData> storeLocationDataList = new ArrayList<>();
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.583011541160584, 88.3426474515566), "Store 1"));
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.58303114342221, 88.34309800147358), "Store 2"));
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.58427152539841, 88.34333625039461), "Store 3"));
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.58433577666162, 88.34330322579379), "Store 4"));
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.584458834083023, 88.34297180033505), "Store 5"));
        storeLocationDataList.add(new StoreLocationData(new LatLng(22.5813676441315, 88.34215995368625), "Store 6"));

        // Resize the custom icon
        Bitmap bitmapstores = BitmapFactory.decodeResource(getResources(), R.drawable.stores);
        Bitmap smallstoresMarker = Bitmap.createScaledBitmap(bitmapstores, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor storesIcon = BitmapDescriptorFactory.fromBitmap(smallstoresMarker);

        // Add markers for each store location with custom color (Yellow)
        for (StoreLocationData storeLocationData : storeLocationDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(storeLocationData.getLatLng())
                    .title(storeLocationData.getTitle())
                    .icon(storesIcon));  // Set marker color to yellow
        }

        List<OfficeLocationData> officeLocationDataList = new ArrayList<>();
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.584255382153888, 88.34341231467323), "Office 1"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.58347699573558, 88.34264211058695), "Office 2"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.5836029529882, 88.34277479325715), "Office 3"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.58324494287168, 88.34292095763291), "Office 4"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.58274015313665, 88.34185500122003), "Office 5"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.582597482976396, 88.34276337726277), "Office 6"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.58223143966718, 88.34207819327176), "Office 7"));
        officeLocationDataList.add(new OfficeLocationData(new LatLng(22.582303495513603, 88.34256047539755), "Office 8"));

        // Resize the custom icon
        Bitmap bitmapoffice = BitmapFactory.decodeResource(getResources(), R.drawable.office);
        Bitmap smallofficeMarker = Bitmap.createScaledBitmap(bitmapoffice, 100, 100, false);  // Resize to 100x100 pixels
        BitmapDescriptor officeIcon = BitmapDescriptorFactory.fromBitmap(smallofficeMarker);

        // Add markers for each office location with custom color (Purple)
        for (OfficeLocationData officeLocationData : officeLocationDataList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(officeLocationData.getLatLng())
                    .title(officeLocationData.getTitle())
                    .icon(officeIcon));  // Set marker color to purple
        }

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle));

            if (!success) {
                Log.e("MapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity", "Can't find style. Error: ", e);
        }
    }

    private static class PlatformData {
        private final LatLng latLng;
        private final String title;

        PlatformData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }

    private static class WaitingLocationData {
        private final LatLng latLng;
        private final String title;

        WaitingLocationData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }

    private static class FoodLocationData {
        private final LatLng latLng;
        private final String title;

        FoodLocationData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }

    private static class RestroomLocationData {
        private final LatLng latLng;
        private final String title;

        RestroomLocationData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }

    private static class StoreLocationData {
        private final LatLng latLng;
        private final String title;

        StoreLocationData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }

    private static class OfficeLocationData {
        private final LatLng latLng;
        private final String title;

        OfficeLocationData(LatLng latLng, String title) {
            this.latLng = latLng;
            this.title = title;
        }

        LatLng getLatLng() {
            return latLng;
        }

        String getTitle() {
            return title;
        }
    }
}
