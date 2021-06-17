package com.example.cocktail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//디비연결하기, 디비에서 읽어온거 list에 표출해주기, 사용자가 list에서 선택한 값을 디비로 넘겨주기.
//내가 알기론 list에서 사용자가 값을 선택하는게 가능한걸로 아는데...만약 잘못했다면 check로 바꾸기.
public class editRecommend extends AppCompatActivity {
    Button okBtn;
    Button cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recommend);

        Intent intent = getIntent();
        //int recommend=intent.getIntExtra();


        okBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAlert(1);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAlert(2);
            }
        });
    }
    protected AlertDialog.Builder showAlert(int id) {
        switch (id) {
            case 1:
                AlertDialog.Builder builder1 =
                        new AlertDialog.Builder(this);
                builder1.setTitle("")
                        .setMessage("수정을 완료하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"완료되었습니다.",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("취소", null)
                ;
                builder1.show();
                return builder1;
            case 2:
                AlertDialog.Builder builder2 =
                        new AlertDialog.Builder(this);
                builder2.setTitle("")
                        .setMessage("수정을 취소하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"취소되었습니다.",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("취소", null)
                ;
                builder2.show();
                return builder2;
        }
        return null;
    }
}
