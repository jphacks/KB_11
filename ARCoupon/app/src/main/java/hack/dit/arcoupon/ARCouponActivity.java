package hack.dit.arcoupon;

import com.sonyericsson.extras.liveware.aef.registration.Registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by kiyomaru on 15/11/29.
 */
public class ARCouponActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 緯度経度の登録
        LatLng location = new LatLng(34.694524, 135.195996);
        // カメラポジション設定
        CameraPosition cameraPos = new CameraPosition.Builder().target(location).zoom(18.0f).bearing(0).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPos));
        // マーカーを作成
        Marker marker = mMap.addMarker(new MarkerOptions().position(location).title("お店の名前").snippet("クーポンの内容"));
        // インフォウィンドウ表示
        marker.showInfoWindow();
    }

    /**
     *  Start the app with the message "Hello SmartEyeglass"
     */
    public void startExtension() {
        // Check ExtensionService is ready and referenced
        if (ARCouponExtensionService.Object != null) {
            ARCouponExtensionService.Object
                    .sendMessageToExtension("Hello SmartEyeglass");
        }
    }
}
