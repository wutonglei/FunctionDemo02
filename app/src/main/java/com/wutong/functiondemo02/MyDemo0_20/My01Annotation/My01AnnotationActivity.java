package com.wutong.functiondemo02.MyDemo0_20.My01Annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.wutong.functiondemo02.R;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My01AnnotationActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;
    private String name;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my01_annotation);
        ButterKnife.bind(this);
        getAnnotation(My01AnnotationActivity.class);

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        testMethod();
    }

    @TestAnnotation(name = "wangjitao", id = 1)
    public void testMethod() {
        tv.setText(name + ":" + id);
    }

    private void getAnnotation(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
            if (testAnnotation != null) {
                name = testAnnotation.name();
                id = testAnnotation.id();
            }
        }
    }
}
