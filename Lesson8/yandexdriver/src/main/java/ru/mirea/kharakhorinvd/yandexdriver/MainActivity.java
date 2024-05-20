package ru.mirea.kharakhorinvd.yandexdriver;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.kharakhorinvd.yandexdriver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements DrivingSession.DrivingRouteListener, UserLocationObjectListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private ActivityMainBinding binding;
    private static final Point START_POINT = new Point(55.670005, 37.479894);
    private static final Point END_POINT = new Point(55.794229, 37.700772);
    private static final Point CAMERA_TARGET = new Point(
            (START_POINT.getLatitude() + END_POINT.getLatitude()) / 2,
            (START_POINT.getLongitude() + END_POINT.getLongitude()) / 2);
    private MapView mapView;
    private DrivingRouter routeRouter;
    private UserLocationLayer userLocationLayer;
    private MapObjectCollection mapObjects;
    private DrivingSession routeSession;
    private int[] routeColors = {0xFFFF0000, 0xFF00FF00, 0x00FFBBBB, 0xFF0000FF};
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        MapKitFactory.initialize(this);
        DirectionsFactory.initialize(this);
        setContentView(binding.getRoot());

        mapView = binding.mapview;
        mapView.getMap().move(new CameraPosition(CAMERA_TARGET, 10, 0, 0));
        routeRouter = DirectionsFactory.getInstance().createDrivingRouter();
        mapObjects = mapView.getMap().getMapObjects().addCollection();

        initializeMarker(new Point( 55.7935328, 37.7012789), "Стромынка 20");

        checkLocationPermission();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    private void initializeMarker(Point location, String message) {
        PlacemarkMapObject marker = mapView.getMap().getMapObjects().addPlacemark(location);
        marker.setIcon(ImageProvider.fromResource(this, com.yandex.maps.mobile.R.drawable.search_layer_pin_selected_default));
        marker.addTapListener((MapObject mapObject, Point point) -> {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    private void requestRoute(Point startPoint, Point endPoint) {
        DrivingOptions options = new DrivingOptions();
        VehicleOptions vehicleOptions = new VehicleOptions();
        options.setRoutesCount(4);

        List<RequestPoint> points = new ArrayList<>();
        points.add(new RequestPoint(startPoint, RequestPointType.WAYPOINT, null));
        points.add(new RequestPoint(endPoint, RequestPointType.WAYPOINT, null));

        progressDialog = ProgressDialog.show(this, "Route Search", "Please wait...", true);
        routeSession = routeRouter.requestRoutes(points, options, vehicleOptions, this);
    }

    @Override
    public void onDrivingRoutes(@NonNull List<DrivingRoute> routes) {
        for (int i = 0; i < routes.size(); i++) {
            mapObjects.addPolyline(routes.get(i).getGeometry()).setStrokeColor(routeColors[i]);
        }
        progressDialog.dismiss();
    }

    @Override
    public void onDrivingRoutesError(@NonNull Error error) {
        String errorMsg = "An error occurred";
        if (error instanceof RemoteError) {
            errorMsg = "Remote error";
        } else if (error instanceof NetworkError) {
            errorMsg = "Network error";
        }
        progressDialog.dismiss();
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            initializeUserLocationLayer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeUserLocationLayer();
            } else {
                Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeUserLocationLayer() {
        MapKitFactory.getInstance().resetLocationManagerToDefault();
        userLocationLayer = MapKitFactory.getInstance().createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);
        userLocationLayer.setObjectListener(this);
    }

    @Override
    public void onObjectAdded(@NonNull UserLocationView userLocationView) {
        userLocationView.getArrow().setIcon(ImageProvider.fromResource(this, android.R.drawable.arrow_up_float));
        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();
        pinIcon.setIcon("pin",
                ImageProvider.fromResource(this, R.drawable.ic_launcher_foreground),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );
        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);

        MapKitFactory.getInstance().createLocationManager().requestSingleUpdate(new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                Log.d("LocationUpdate", "Received location update");
                requestRoute(location.getPosition(), new Point(55.704205, 37.507699));
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus status) {}
        });
    }

    @Override
    public void onObjectRemoved(@NonNull UserLocationView userLocationView) {}

    @Override
    public void onObjectUpdated(@NonNull UserLocationView userLocationView, @NonNull ObjectEvent event) {}
}