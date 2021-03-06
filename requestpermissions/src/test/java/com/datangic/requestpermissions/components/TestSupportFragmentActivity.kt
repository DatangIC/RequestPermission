/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datangic.requestpermissions.components

import androidx.fragment.app.FragmentActivity
import com.datangic.requestpermissions.annotations.Mockable
import com.datangic.requestpermissions.callbacks.PermissionCallbacks
import com.datangic.requestpermissions.callbacks.RationaleCallbacks

@Mockable
class TestSupportFragmentActivity :
    FragmentActivity(),
        PermissionCallbacks,
        RationaleCallbacks {

    companion object {
        const val REQUEST_CODE = 4
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {}

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {}

    override fun onRationaleAccepted(requestCode: Int) {}

    override fun onRationaleDenied(requestCode: Int) {}
}