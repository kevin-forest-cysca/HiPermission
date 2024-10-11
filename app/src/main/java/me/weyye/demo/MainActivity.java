package me.weyye.demo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {//default is normal style
            HiPermission.create(MainActivity.this)
                    .animStyle(me.weyye.hipermission.R.style.PermissionAnimFade)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            Log.i(TAG, "onClose");
                            showToast(getString(R.string.permission_on_close));
                        }

                        @Override
                        public void onFinish() {
                            showToast(getString(R.string.permission_completed));
                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            Log.i(TAG, "onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            Log.i(TAG, "onGuarantee");
                        }
                    });
        } else if (id == R.id.btn2) {//After you have set the theme, you must called filterColor () to set the color of the icon
            // ,otherwise the default is black
            List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
            permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", me.weyye.hipermission.R.drawable.permission_ic_phone));
            HiPermission.create(MainActivity.this)
                    .title(getString(R.string.permission_cus_title))
                    .permissions(permissionItems)
                    .msg(getString(R.string.permission_cus_msg))
                    .animStyle(me.weyye.hipermission.R.style.PermissionAnimScale)
                    .style(me.weyye.hipermission.R.style.PermissionDefaultBlueStyle)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            Log.i(TAG, "onClose");
                            showToast(getString(R.string.permission_on_close));
                        }

                        @Override
                        public void onFinish() {
                            showToast(getString(R.string.permission_completed));
                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            Log.i(TAG, "onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            Log.i(TAG, "onGuarantee");
                        }
                    });
        } else if (id == R.id.btn3) {
            List<PermissionItem> permissions = new ArrayList<PermissionItem>();
            permissions.add(new PermissionItem(Manifest.permission.CALL_PHONE, getString(R.string.permission_cus_item_phone), me.weyye.hipermission.R.drawable.permission_ic_phone));
            HiPermission.create(MainActivity.this)
                    .title(getString(R.string.permission_cus_title))
                    .permissions(permissions)
                    .msg(getString(R.string.permission_cus_msg))
                    .animStyle(me.weyye.hipermission.R.style.PermissionAnimModal)
                    .style(me.weyye.hipermission.R.style.PermissionDefaultGreenStyle)
//                        .style(R.style.CusStyle)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            Log.i(TAG, "onClose");
                            showToast(getString(R.string.permission_on_close));
                        }

                        @Override
                        public void onFinish() {
                            showToast(getString(R.string.permission_completed));
                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            Log.i(TAG, "onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            Log.i(TAG, "onGuarantee");
                        }
                    });
        } else if (id == R.id.btn4) {//request single permission only called onDeny or onGuarantee
            HiPermission.create(MainActivity.this).checkSinglePermission(Manifest.permission.CAMERA, new PermissionCallback() {
                @Override
                public void onClose() {

                }

                @Override
                public void onFinish() {

                }

                @Override
                public void onDeny(String permission, int position) {
                    showToast("onDeny");
                }

                @Override
                public void onGuarantee(String permission, int position) {
                    showToast("onGuarantee");
                }
            });
        }
    }
}
