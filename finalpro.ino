#include<Servo.h>
#include<math.h>
int x1=12;
int y1=47.5;//the default values of the problem
int x2=45;
int Trigpin=13;
int Echopin=8;
int angle;
float PingTime;
float Distance;
float y2,y;
float m;
int ServoControlPin=6;
Servo myPointer;
int sensorPin = A0; // select the input pin for ldr
int sensorValue = 0; // variable to store the value coming from the sensor

void setup() {
  Serial.begin(9600);
  pinMode(Trigpin,OUTPUT);
  pinMode(Echopin,INPUT);
  digitalWrite(Trigpin,LOW);
  delayMicroseconds(2000);
  digitalWrite(Trigpin,HIGH);
  delayMicroseconds(15);
  digitalWrite(Trigpin,LOW);
  PingTime=pulseIn(Echopin,HIGH);
  Distance=PingTime/2/29;
  Serial.print("Distance:");
  Serial.println(Distance);
  pinMode(ServoControlPin,OUTPUT);
  myPointer.attach(ServoControlPin);
  y2=Distance+11.6;
  y=y1-y2;
  m=(y-y1)/(x2-x1);
  angle=m-((m*m*m)/3)+(pow(m,5))/5;
  angle=angle*57.32;
  angle=angle+45;
 Serial.print(angle);
   // put your setup code here, to run once:
//int i=0,j=0;
}

void loop() {
  sensorValue = analogRead(sensorPin);// read the value from the sensor
  
Serial.print("sensor:");
Serial.println(sensorValue);
 //prints the values coming from the sensor on the screen
//delay(100);
if(sensorValue>=850)
{
  //moved[j++]=sensorValue;
  for(int i=0;i<=45;i+=1)
  {myPointer.write(i);
  delay(15);}
  
  delay(150);
  for(int i=45;i>=0;i-=1)
  {myPointer.write(i);
  delay(15);}
  }//delay();
  


}
