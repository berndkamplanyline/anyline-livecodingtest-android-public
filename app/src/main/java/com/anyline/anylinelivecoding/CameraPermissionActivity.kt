package com.anyline.anylinelivecoding

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

open class CameraPermissionActivity : AppCompatActivity() {

    var functionToCallWithCameraPermissionGranted: () -> Unit = {}

    fun executeIfCameraPermissionGranted(function: () -> Unit) {
        functionToCallWithCameraPermissionGranted = function
        checkAndRequestCameraPermission()
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERMISSION)
    private fun checkAndRequestCameraPermission() {
        val permission = Manifest.permission.CAMERA
        if (EasyPermissions.hasPermissions(this, permission)) {
            // Requested permissions have been granted, invoke the function
            functionToCallWithCameraPermissionGranted.invoke()
        } else {
            // Requested permissions haven't been granted (yet), request them
            EasyPermissions.requestPermissions(
                this,
                "[Placeholder: Explain in a succinct manner, what we need this permission for]",
                REQUEST_CAMERA_PERMISSION,
                permission
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    companion object {
        const val REQUEST_CAMERA_PERMISSION = 1
    }
}