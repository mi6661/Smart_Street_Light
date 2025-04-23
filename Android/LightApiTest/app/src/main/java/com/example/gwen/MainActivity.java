package com.example.gwen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gwen.entity.Light;
import com.example.gwen.http.HttpManager;
import com.example.gwen.utils.PermissionUtils;
import com.example.gwen.utils.TimeUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String baseUrl = "http://49.232.141.65:8081";

    private TextView tv_light_info;
    private EditText et_id;
    private EditText et_location;
    private EditText et_status;
    private EditText et_brightness;
    private EditText et_auto;
    private Button btn_light_get;
    private HttpManager manager;
    private Button btn_light_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new HttpManager(baseUrl);

        tv_light_info = findViewById(R.id.tv_light_info);
        btn_light_get = findViewById(R.id.btn_light_get);
        btn_light_get.setOnClickListener(this);


        et_id = findViewById(R.id.et_id);
        et_location = findViewById(R.id.et_location);
        et_status = findViewById(R.id.et_status);
        et_brightness = findViewById(R.id.et_brightness);
        et_auto = findViewById(R.id.et_auto);
        btn_light_update = findViewById(R.id.btn_light_update);
        btn_light_update.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String id = et_id.getText().toString();
        String location = et_location.getText().toString();
        String status = et_status.getText().toString();
        String brightness = et_brightness.getText().toString();
        String auto = et_auto.getText().toString();

        //获取按钮id
        int id_btn = view.getId();

        if(id_btn == R.id.btn_light_get){
            try {
                int light_id = Integer.parseInt(id);
                //通过新线程来启动网络请求
                new Thread(){
                    @Override
                    public void run(){
                        String response = manager.getLightById(light_id);
                        tv_light_info.setText(response);
                    }
                }.start();
            }catch (NumberFormatException e){
                Log.e("Gwen", TimeUtil.getTime()+e.getMessage());
            }

        }
        if(id_btn == R.id.btn_light_update){
            Log.e("Gwen","更新按钮被点击");
            int light_id;
            int light_brightness;
            Light light;
            try{
                light_id = Integer.parseInt(id);
                light_brightness = Integer.parseInt(brightness);
                light = new Light(light_id,location,status,light_brightness,auto);

                //启动线程，发送请求
                new Thread(){
                    @Override
                    public void run(){
                        String response = manager.setLight(light);
                        tv_light_info.setText(response);
                    }
                }.start();
            } catch (NumberFormatException e) {
                Log.e("Gwen","出现异常：");
                e.printStackTrace();
            }

        }
    }
}