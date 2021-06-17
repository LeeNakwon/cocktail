/*
  Blink

  Turns an LED on for one second, then off for one second, repeatedly.

  Most Arduinos have an on-board LED you can control. On the UNO, MEGA and ZERO
  it is attached to digital pin 13, on MKR1000 on pin 6. LED_BUILTIN is set to
  the correct LED pin independent of which board is used.
  If you want to know what pin the on-board LED is connected to on your Arduino
  model, check the Technical Specs of your board at:
  https://www.arduino.cc/en/Main/Products

  modified 8 May 2014
  by Scott Fitzgerald
  modified 2 Sep 2016
  by Arturo Guadalupi
  modified 8 Sep 2016
  by Colby Newman

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/Blink
*/

#include <SoftwareSerial.h>

//상수화.
#define TX 2
#define RX 3  

//릴레이 모듈 부하 문제 모두 해결됨.
#define BASE 800//기본 작동시간.
#define AMOUNT 25//양 조절.


String recipe = "";

SoftwareSerial BTSerial(TX, RX);   //블루투스 객체 선언. 연결된 핀 번호와 일치시킬 것

// the setup function runs once when you press reset or power the board
void setup() {
    //시리얼 창 설정
  Serial.begin(9600);
  BTSerial.begin(9600);//블루투스 통신 시작.
  
  // initialize digital pin LED_BUILTIN as an output.
  //7~12는 워터펌프 연결된 릴레이 모듈 전용.
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
}

// the loop function runs over and over again forever
void loop() {
  while(BTSerial.available()){
    char myChar = (char)BTSerial.read();
    recipe += myChar;//문자 하나씩 이어붙이기
    delay(100);//수신 문자열 끊김방지
  }
  if(!recipe.equals("")){
    Serial.println(recipe);
    int cut = 0;//용량 단위 끊기
    int stringSize = 0;//현재 문자열 크기
    int volume = 0;//대상 재료 용량

    //칵테일 만들기
    for (int i = 0; i<6; i++) {
    cut = recipe.indexOf(',');//','로 구분되는 데이터를 분할하기 위한 것.
    stringSize = recipe.length();//현재 문자열 크기
    
    volume = recipe.substring(0,cut).toInt();//현재 순번에 해당하는 재료의 용량.
    recipe = recipe.substring(cut+1,stringSize);//끊어내고 남은 문자열.(나머지 재료의 용량 데이터)

      if(volume > 0){
       digitalWrite(i + 7, HIGH);
       if(i == 0 || i == 3){
        delay(volume * AMOUNT + BASE - 150);//공급 튜브 길이가 짧은 쪽.
       } else if( i == 1 || i == 4){
        delay(volume * AMOUNT + BASE - 75);//공급 튜브 길이가 긴 쪽.
       } else{
        delay(volume * AMOUNT + BASE);
       }
       
       digitalWrite(i + 7, LOW);
       delay(3000);
      }
    }
       
    recipe = "";
  } else {
  }
}
         
