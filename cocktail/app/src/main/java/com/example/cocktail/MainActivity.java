package com.example.cocktail;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//디비연결, 디비에서 읽어온거 textInputLayout에 textinputedittext에 힌트로 적어주기
public class MainActivity extends AppCompatActivity {

    Button okBtn;
    Button cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        //int recipeId=intent.getIntExtra();

        okBtn=(Button)findViewById((R.id.button));
        cancelBtn=(Button)findViewById(R.id.button2);
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
                                //textinputedittext에 있는 값 디비로 넘겨주기
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
