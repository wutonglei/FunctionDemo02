package com.wutong.functiondemo02.MyDemo0_20.My12CaughtException;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wutong.functiondemo02.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My12CaughtExceptionActivity extends AppCompatActivity {

    @BindView(R.id.btn_exception)
    Button btnException;
    @BindView(R.id.btn_write)
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my12_caught_exception);
        ButterKnife.bind(this);


    }



    @OnClick({R.id.btn_exception, R.id.btn_write})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_exception:
                int i = 0 / 0;
                break;
            case R.id.btn_write:
                String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+this.getPackageName()+"/"+"xxx.txt";
                File file=new File(path);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                try {
                    FileOutputStream outputStream=new FileOutputStream(file);
                    String str="dsasdsadasda";

                    outputStream.write(str.getBytes());
                    str="我日";
                    outputStream.flush();
                    outputStream.write(str.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
        }
    }
}
