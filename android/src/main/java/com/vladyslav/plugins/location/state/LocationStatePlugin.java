package com.vladyslav.plugins.location.state;

import android.content.Context;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(name = "LocationState")
public class LocationStatePlugin extends Plugin {
private static final int BACKGROUND_LOCATION_PERMISSION_CODE = 2;

    @PluginMethod
    public void checkPermission(PluginCall call) {
        Context context = getContext();
        PackageManager pm = context.getPackageManager();

        boolean hasFineLocation = pm.checkPermission("android.permission.ACCESS_FINE_LOCATION", context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        boolean hasCoarseLocation = pm.checkPermission("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        boolean hasBackgroundLocation = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            hasBackgroundLocation = pm.checkPermission("android.permission.ACCESS_BACKGROUND_LOCATION", context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        }

        String permissionStatus = "";
        if (hasFineLocation && hasBackgroundLocation) {
            permissionStatus = "authorizedAlways"; // Requires both fine location and background location permissions
        } else if (hasFineLocation || hasCoarseLocation) {
            permissionStatus = "authorizedWhenInUse"; // Approximate, as Android permissions are not exactly like iOS
        } else {
            permissionStatus = "denied";
        }

        JSObject result = new JSObject();
        result.put("status", permissionStatus);
        call.resolve(result);
    }

    @PluginMethod
    public void openLocationSettings(PluginCall call) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                saveCall(call);
                ActivityCompat.requestPermissions(
                    getBridge().getActivity(),
                    new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    BACKGROUND_LOCATION_PERMISSION_CODE
                );
            } else {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Uri uri = Uri.fromParts("package", getBridge().getActivity().getPackageName(), null);
                intent.setData(uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBridge().getActivity().startActivity(intent);
                call.resolve();
            }
        } catch (Exception e) {
            // Fallback to application details settings if direct navigation fails
            try {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getBridge().getActivity().getPackageName(), null);
                intent.setData(uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBridge().getActivity().startActivity(intent);
                call.resolve();
            } catch (Exception fallbackError) {
                call.reject("Failed to open settings", fallbackError);
            }
        }
    }

    @Override
    protected void handleRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.handleRequestPermissionsResult(requestCode, permissions, grantResults);

        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }

        if (requestCode == BACKGROUND_LOCATION_PERMISSION_CODE) {
            try {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getBridge().getActivity().getPackageName(), null);
                intent.setData(uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBridge().getActivity().startActivity(intent);
                savedCall.resolve();
            } catch (Exception e) {
                savedCall.reject("Failed to open settings", e);
            }
        }
    }

}
