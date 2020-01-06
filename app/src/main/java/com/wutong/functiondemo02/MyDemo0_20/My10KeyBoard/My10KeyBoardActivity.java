package com.wutong.functiondemo02.MyDemo0_20.My10KeyBoard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wutong.functiondemo02.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class My10KeyBoardActivity extends AppCompatActivity {

    @BindView(R.id.act_key_board_et)
    EditText actKeyBoardEt;

    @BindView(R.id.ky_keyboard_parent)
    LinearLayout kyKeyboardParent;
    @BindView(R.id.ky_keyboard)
    CarKeyboardView kyKeyboard;


    private CarKeyBoardUtil carKeyBoardUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my10_key_board);
        ButterKnife.bind(this);

        carKeyBoardUtil = new CarKeyBoardUtil(kyKeyboardParent,kyKeyboard,actKeyBoardEt);

        actKeyBoardEt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                carKeyBoardUtil.showKeyboard();
                return false;
            }
        });
    }
}
