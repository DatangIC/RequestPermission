package com.datangic.permissions

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.datangic.permissions.databinding.ActivityMainBinding
import com.datangic.requestpermissions.RequestPermissions
import com.datangic.requestpermissions.callbacks.PermissionCallbacks
import com.datangic.requestpermissions.dialogs.SettingsDialog
import com.datangic.requestpermissions.models.PermissionRequest
import com.google.android.material.snackbar.Snackbar


const val REQUEST_CODE_CAMERA_STORAGE_PERMISSION = 1023

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initOnClick()
    }

    val mPermissionCallbacks = object : PermissionCallbacks {
        override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        }

        override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
            if (RequestPermissions.somePermissionPermanentlyDenied(this@MainActivity, perms)) {

                if (perms.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    SettingsDialog.Builder(this@MainActivity)
                            .theme(R.style.AppTheme_MaterialDialog)
                            .positiveButtonText(R.string.settings)
                            .negativeButtonText(R.string.cancel)
                            .title(R.string.location_permission_title)
                            .icon(R.drawable.ic_location_off)
                            .rationale(R.string.location_permission_info)
                            .build()
                            .show()
                } else if (perms.contains(Manifest.permission.CAMERA)) {
                    SettingsDialog.Builder(this@MainActivity)
                            .theme(R.style.AppTheme_MaterialDialog)
                            .positiveButtonText(R.string.settings)
                            .negativeButtonText(R.string.cancel)
                            .title(R.string.camera_permission_title)
                            .icon(R.drawable.ic_camera)
                            .rationale(R.string.camera_permission_info)
                            .build()
                            .show()
                }
            }
        }

        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            displaySnackBar(mBinding.root, "onRequestPermissionsResult")
        }
    }


    private fun initOnClick() {

        mBinding.ble.setOnClickListener {
            if (RequestPermissions.hasPermissions(this, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN)) {
                displaySnackBar(it, mBinding.ble.text)
            } else {
                val request = PermissionRequest.Builder(this)
                        .code(REQUEST_CODE_CAMERA_STORAGE_PERMISSION)
                        .theme(R.style.AppTheme_MaterialDialog)
                        .positiveButtonText(R.string.grant)
                        .negativeButtonText(R.string.cancel)
                        .title(R.string.bluetooth_disabled_title)
                        .icon(R.drawable.ic_bluetooth_disabled)
                        .rationale(R.string.bluetooth_disabled_info)
                        .perms(arrayOf(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN))
                        .build()
                RequestPermissions.requestPermissions(this, request)
            }
        }
        mBinding.location.setOnClickListener {
            if (RequestPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                displaySnackBar(it, mBinding.location.text)
            } else {
                val request = PermissionRequest.Builder(this)
                        .code(REQUEST_CODE_CAMERA_STORAGE_PERMISSION)
                        .theme(R.style.AppTheme_MaterialDialog)
                        .positiveButtonText(R.string.grant)
                        .negativeButtonText(R.string.cancel)
                        .title(R.string.location_permission_title)
                        .icon(R.drawable.ic_location_off)
                        .rationale(R.string.location_permission_info)
                        .perms(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
                        .build()
                RequestPermissions.requestPermissions(this, request)
            }
        }
        mBinding.storage.setOnClickListener {
            if (RequestPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                displaySnackBar(it, mBinding.storage.text)
            } else {
                val request = PermissionRequest.Builder(this)
                        .code(REQUEST_CODE_CAMERA_STORAGE_PERMISSION)
                        .theme(R.style.AppTheme_MaterialDialog)
                        .positiveButtonText(R.string.grant)
                        .negativeButtonText(R.string.cancel)
                        .title(R.string.storage_permission_title)
                        .icon(R.drawable.ic_file)
                        .rationale(R.string.storage_permission_info)
                        .perms(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                        .build()
                RequestPermissions.requestPermissions(this, request)
            }
        }
        mBinding.camera.setOnClickListener {
            if (RequestPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
                displaySnackBar(it, mBinding.camera.text)
            } else {
                val request = PermissionRequest.Builder(this)
                        .code(REQUEST_CODE_CAMERA_STORAGE_PERMISSION)
                        .theme(R.style.AppTheme_MaterialDialog)
                        .positiveButtonText(R.string.grant)
                        .negativeButtonText(R.string.cancel)
                        .title(R.string.camera_permission_title)
                        .icon(R.drawable.ic_camera)
                        .rationale(R.string.camera_permission_info)
                        .perms(arrayOf(Manifest.permission.CAMERA))
                        .build()
                RequestPermissions.requestPermissions(this, request)
            }
        }
    }

    fun displaySnackBar(view: View, text: Any) {
        var mSnackBar: Snackbar? = null
        if (text is String) {
            mSnackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        } else if (text is Int) {
            mSnackBar = Snackbar.make(view, view.resources.getString(text), Snackbar.LENGTH_LONG)
        }
        mSnackBar?.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, mPermissionCallbacks)
    }

}