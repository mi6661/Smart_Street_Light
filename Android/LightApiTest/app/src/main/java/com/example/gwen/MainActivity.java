package com.example.gwen;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gwen.http.HttpManager;
import com.example.gwen.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private static final String baseUrl = "http://49.232.141.65:8081";

    private TextView tv_light_info;
    private EditText et_id;
    private EditText et_location;
    private EditText et_status;
    private EditText et_brightness;
    private EditText et_auto;
    private Button btn_light_get;
    private HttpManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new HttpManager(baseUrl);

        tv_light_info = findViewById(R.id.tv_light_info);
        btn_light_get = findViewById(R.id.btn_light_get);
        btn_light_get.setOnClickListener(view->{
            try {
                int id = Integer.parseInt(et_id.getText().toString());
                String response = manager.getLightById(id);
                tv_light_info.setText(response);
            }catch (NullPointerException e){
                tv_light_info.setText("请输入路灯id后再查询！！！");
            }catch (NumberFormatException e){
                tv_light_info.setText(e.toString());
            }
        });


        et_id = findViewById(R.id.et_id);
        et_location = findViewById(R.id.et_location);
        et_status = findViewById(R.id.et_status);
        et_brightness = findViewById(R.id.et_brightness);
        et_auto = findViewById(R.id.et_auto);


    }
}