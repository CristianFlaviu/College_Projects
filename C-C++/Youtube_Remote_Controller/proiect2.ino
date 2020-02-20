
#include <SoftwareSerial.h>
#include "Game_of_Thrones.h"

SoftwareSerial mySerial(2, 3); // RX, TX
String inputString = "";
String outputString = "";

int count_sound = 0;
long sum_sound = 0;
int average_sound;

const int trigPin2 = 7;
const int echoPin2 = 6;

bool stringComplete = false;
bool direction = false;
int soundPin = A2;
int soundVal;

bool var = true;

long current_time, last_time;

int dist_Sensor;
int distance;


int buzzer = 8;
bool stopSong = false;
int divider = 0, noteDuration = 0;
bool print_sound_value = false;
void setup()
{
  pinMode(soundPin, INPUT);
  pinMode(trigPin2, OUTPUT);
  pinMode(echoPin2, INPUT);

  inputString.reserve(200);
  outputString.reserve(200);
  Serial.begin(9600);
  while (!Serial)
  {
  }
  mySerial.begin(38400);
  mySerial.println("Hello \n you have conncted to the bluetooth");
 
  mySerial.println("--------------------------------------------------");

  mySerial.write("Give a few seconds to calibrate the sound sensor\n");
  while(millis()<2000)
  { 
    count_sound++;
    
    soundVal = analogRead(soundPin);
    sum_sound += soundVal;
    
    Serial.print("#");
    Serial.print(count_sound);
    Serial.print(" Value_init:");
    Serial.println(soundVal);
  }
  average_sound = sum_sound / count_sound;
  Serial.print("Average_Value is ");
  Serial.println(average_sound);
}


bool new_delay(int delay_time)
{
  if (var)
  {
    current_time = millis();
    last_time = current_time;
    var = false;
  }
  current_time = millis();
  if (current_time - last_time > delay_time)
  {
    Serial.print("Time passed :");
    Serial.println(delay_time);

    last_time = current_time;
    var = true;
    return true;
  }
  return false;
}

int computeDistance(int trigPin, int echoPin)
{
  long duration;
  int distance;

  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);
  distance = duration * 0.034 / 2;

  return distance;
}

void ultrasonic_Sensor()
{
  dist_Sensor = computeDistance(trigPin2, echoPin2);

  if (dist_Sensor <= 11 && dist_Sensor >= 7)
  {
    //delay(1000);
    Serial.print("#time elapsed ");
    Serial.println(current_time - last_time);

    if (new_delay(2000) == true)
    {
      dist_Sensor = computeDistance(trigPin2, echoPin2);

      if (dist_Sensor <= 11 && dist_Sensor >= 7)
      {
        distance = dist_Sensor; //Distanta cand este stabil
        Serial.print("#S-a stabilizat la ");
        Serial.println(distance);

        while (dist_Sensor <= 15 && dist_Sensor >= 1)
        {
          dist_Sensor = computeDistance(trigPin2, echoPin2);
          Serial.print("#distance: ");
          Serial.println(dist_Sensor);
          mySerial.println("you can move your hand");
          if (direction)
          {
            if (dist_Sensor > distance + 3)
              Serial.println("ml");
            else if (dist_Sensor <= distance - 2)
              Serial.println("mr");
          }

          else
          {
            if (dist_Sensor > distance + 3)
              Serial.println("mu");
            else if (dist_Sensor <= distance - 2)
              Serial.println("md");
          }

          delay(1000);
        }
      }
    }
    else
    {
      dist_Sensor = computeDistance(trigPin2, echoPin2);
      if (dist_Sensor > 15)
      {
        var = true;
      }
    }
  }
  
  // else
  // {
  //   var=true;
  // }
  
  
}

void play_song(int tempo, int *melody, int notes)
{
  int wholenote = (60000 * 4) / tempo;

  for (int thisNote = 0; (thisNote < notes * 2) && (stopSong == false); thisNote = thisNote + 2)
  {
    divider = melody[thisNote + 1];
    if (divider > 0)
    {
      noteDuration = (wholenote) / divider;
    }
    else if (divider < 0)
    {

      noteDuration = (wholenote) / abs(divider);
      noteDuration *= 1.5;
    }
    tone(buzzer, melody[thisNote], noteDuration * 0.9);

    delay(noteDuration);

    noTone(buzzer);
    while (mySerial.available())
    {
      char c = mySerial.read();
      inputString += c;
      if (c == '\n')
        stringComplete = true;
    }
    if (stringComplete)
    {
      stringComplete = false;
      Serial.println(inputString.c_str());
      if (inputString.startsWith("stop"))

      {
        stopSong = true;
      }

      inputString = "";
    }
  }
}

void loop()
{

   ultrasonic_Sensor();

  while (Serial.available())
  {
    mySerial.write(Serial.read());
  }

  while (mySerial.available())
  {
    char c = mySerial.read();
    inputString += c;
    if (c == '\n')
      stringComplete = true;
  }

  //  if (inputString[0] == 'c' && inputString[1] == 'h' && inputString[2] == 'g'&& inputString[3] == '1')
  //  {
  //    direction = true;
  //    mySerial.write("Now you can shift tab\n");
  //    Serial.println("matchhh");
  //
  //
  //  }
  //
  //  if(inputString.startsWith("play"))
  //
  //    {
  //     Serial.println("match");
  //    play_song(tempo_Game_of_Thrones,melody_Game_of_Throness,notes_Game_of_Thrones);
  //    }

  if (stringComplete)
  {
    stringComplete = false;
    Serial.println(inputString.c_str());

    if (inputString.startsWith("printS"))
    {
      print_sound_value =!print_sound_value;
    }
    else if (inputString.startsWith("play"))
    {
      play_song(tempo_Game_of_Thrones, melody_Game_of_Throness, notes_Game_of_Thrones);
    }
    else if (inputString.startsWith("#chg1"))
    {
      direction = true;
      mySerial.write("Now you can shift tab\n");
      Serial.println("matchhh");
    }

    inputString = "";
  }

  soundVal = analogRead(soundPin);
  if (soundVal > average_sound + 11)
  {
    Serial.print("#peak");
    Serial.println(soundVal);
    direction=!direction;
    mySerial.println("direction changed");
  }

  if(print_sound_value)
    {
       Serial.print("#"); 
    Serial.println(soundVal);
    }
}
