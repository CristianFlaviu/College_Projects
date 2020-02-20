import pynput.mouse as ms
import pynput.keyboard as kb
import time
import serial
from pynput.mouse import Button

mouse = ms.Controller()
keyboard = kb.Controller()

ArduinoSerial = serial.Serial('com15',9600)
time.sleep(2)

while 1:
    incoming = str(ArduinoSerial.readline())
    print (incoming)
    if incoming.startswith("st"):  #start/stop
        keyboard.press(kb.Key.space)
        keyboard.release(kb.Key.space)

    elif incoming[0] == 'j': #seek backward 10 sec
        keyboard.type("j")
    elif incoming[0] == 'i': #seek forward 10 sec
        keyboard.type("i")
    elif incoming.startswith("up"): #arrow up
        keyboard.press(kb.Key.up)
        keyboard.release(kb.Key.up)

    elif incoming.startswith("dw"): #arrow down
        keyboard.press(kb.Key.down)
        keyboard.release(kb.Key.down)

    elif incoming.startswith("lf"): #arrow left
        keyboard.press(kb.Key.down)
        keyboard.release(kb.Key.down)

    elif incoming.startswith("rh"): #arrow down
        keyboard.press(kb.Key.down)
        keyboard.release(kb.Key.down)


    elif incoming[0] == '1': #seek from 10% to 90%
        keyboard.type("1")
    elif incoming == "2":
        keyboard.type("2")
    elif incoming[0]=='3':
         keyboard.type("3")
    elif incoming[0]=='4':
         keyboard.type("4")
    elif incoming[0]=='5':
         keyboard.type("5")
    elif incoming[0]=='6':
         keyboard.type("6")
    elif incoming[0]=='7':
         keyboard.type("7")
    elif incoming[0]=='7':
         keyboard.type("8")
    elif incoming[0]=='9':
         keyboard.type("9")
    elif incoming[0]=='0':
         keyboard.type("0")


    elif incoming.startswith("wr"): # go to searchBox and type something
        keyboard.type("/");
        time.sleep(0.1)
        keyboard.type(incoming.split(" ")[1])

    elif incoming[0] == 'f':  #full sceen
        keyboard.type("f")

    elif incoming[0] == 'c': #subtitles
        keyboard.type("c")

    elif incoming.startswith("sn"): #next video
        keyboard.press(kb.Key.shift_l)
        time.sleep(0.1)
        keyboard.type("n")
        time.sleep(0.1)
        keyboard.release(kb.Key.shift_l)
    elif  incoming.startswith("sp"):    #previous video
        keyboard.press(kb.Key.shift_l)
        time.sleep(0.1)
        keyboard.type("p")
        time.sleep(0.1)
        keyboard.release(kb.Key.shift_l)

    elif incoming.startswith("yt"):#enter youtube
        keyboard.press(kb.Key.cmd_r)
        keyboard.release(kb.Key.cmd_r)
        time.sleep(1)
        keyboard.type("chrome")
        time.sleep(1)
        keyboard.press(kb.Key.enter)
        keyboard.release(kb.Key.enter)
        time.sleep(1)
        keyboard.type("www.youtube.com")
        time.sleep(1)
        keyboard.press(kb.Key.enter)
        keyboard.release(kb.Key.enter)

    elif incoming.startswith("tl"): #shift tab left
        keyboard.press(kb.Key.ctrl)
        keyboard.press(kb.Key.shift)
        keyboard.press(kb.Key.tab)
        time.sleep(0.1)
        keyboard.release(kb.Key.shift)
        keyboard.release(kb.Key.tab)
        keyboard.release(kb.Key.ctrl )


    elif incoming.startswith("tr"): #shift tab right
        keyboard.press(kb.Key.ctrl)
        keyboard.press(kb.Key.tab)
        time.sleep(0.1)
        keyboard.release(kb.Key.tab)
        keyboard.release(kb.Key.ctrl)

    elif incoming.startswith("ml"): #move mouse left
        mouse.move(-20, 0)
    elif incoming.startswith("mr"): #move mouse right
        mouse.move(20,0)
    elif incoming.startswith("mu"): #move mouse down
        mouse.move(0, -20)
    elif incoming.startswith("md"): #move mouse up
        mouse.move(0, 20)
    elif incoming.startswith("ent"): #enter;
        keyboard.press(kb.Key.enter)
    elif incoming.startswith("mc"):
        mouse.press(Button.left)
        mouse.release(Button.left)
    elif incoming.startswith("sf"): #fullscreen
        keyboard.press(kb.Key.cmd)
        time.sleep(0.1)
        keyboard.press(kb.Key.up);
        time.sleep(0.1)
        keyboard.release(kb.Key.cmd)
        keyboard.release(kb.Key.up)
    elif incoming.startswith("del"):
        keyboard.type("/")
        keyboard.press(kb.Key.ctrl)
        time.sleep(0.1)
        keyboard.type("a")
        keyboard.release(kb.Key.ctrl)
        keyboard.press(kb.Key.delete)
        keyboard.release(kb.Key.delete)


    incoming = ""
