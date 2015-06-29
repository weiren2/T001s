T001s
====
CS 125 honor project.

A Tiny but Useful Tool on Android.

Developed by: [Wei Ren](https://github.com/victor-ren), [Qihao Shao](https://github.com/QihaoShao), [Junfei Zhang](https://github.com/harvey940906). (weiren2, qshao5, jzhng125)  

Thanks to: Thomas Reese, Github, StackOverFlow and many other online sources. 

If you have any advice, questions or bugs to report, feel free to email us or just post it on the issues page.
Introduction
---
Have you ever been wondering how to let the android device to speak out the current time？  
Have you ever thought of having a easier and cleaner calculator?  
Have you ever considered an graphing calulator on Android device?  
Don't worry, T001s can help you!  

Function
---
This Android application T001s is an integration of three small applications. They are AudioTimer, Calculator and Graphing Calculator respectively.  
You can easliy switch between these three tools in the main screen/activity of the app.

Key features:
 >1. Optimized for different kinds of android devices including smart phones and tablets.
 >2. Both portrait and landscape mode. 
 >3. Consistent UI for the whole application.
 >4. The Icon of the app is our prof. Angrave! :p

#### AudioTimer
Key features:  
 >1. With one major button on the screen.  
 >2. Easy to understand and use. Just a touch of the "SPEAK TIME" button will speak out the current time.  
 >3. Two other buttons lie in the bottom of the screen to navigate other apps/activities.  
 
Implementation:  
 >We make use of Android TextToSpeech engine to synthesize from text or string to convert into sound. "java.util.Calendar" is also helpful to get current date and time.
 
#### Calculator
Key features:
 >1. Clean UI. Easy to use.
 >2. Convenient to operate with one hand.
 >3. No need to convert mode. Just drag the Sliding Drawer located at the bottom to find out more advanced mathematical funtions.
 >4. Mathematical functions including Trigonometric functions, mod, squrate roots and simple mathematical expression.  
 >5. Compatible with theme "Holo Light".

Implementaion:
 >1. A lot of layouts and buttons. All buttons need to set onClickListener.
 >2. Sliding drawer enables it can keep both simple mode and scientific mode at the same screen without interfering the UI.
 
#### Graphing Calculator
Key features:
 >1. Convert user input into mathematical functions and plot the points.
 >2. Touch the graph! The points on the graph will be highlighted with the exact coordinates shown.
 >3. If you want to plot another graph, simply enter another expression and press the graph button.

Implementation:
 >1. Use the Canvas as the background for graphing.
 >2. We use another package from Github to convert the string to mathematcial expression then plot it.
 
### Optional features (To be completed in the future)
1. Add a zoom in and out feature in the graphing calculator.
2. Add more scientific modes for calculator such as binary, complex and matrix.
3. More featurs using TextToSpeech(TTS).
4. Speaking out the time when press the headset button. (This one was our original thought, but find it hard to implement)

### Screenshots:

##### Main Screen/AudioTimer 
![Main Screen](https://github.com/victor-ren/T001s/raw/master/01.png)  
##### Calculator
![Calculator](https://github.com/victor-ren/T001s/raw/master/02.png)
![Calculator](https://github.com/victor-ren/T001s/raw/master/03.png)
##### Graphing Calculator  
![Graphing Calculator](https://github.com/victor-ren/T001s/raw/master/04.png) 
