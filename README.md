# The Nearly Impossible Game
This game was made as a part of the course "TDT4240 - Software architecture" at the Norwegian University of Science and Technology. In this project, the main task is to make a functioning multiplayer game for Android or iPhone, based on your own game concept.

## Inspiration
Our group had several ideas about what type of game we wanted to create. after
some discussion we decided to try to create a version of ”The Impossible Game”, a
mobile game some of us had played when we were younger. It is a game where you
play as a square going through a course made up of various obstacles appearing
in its path with increased frequency and difficulty. The goal of the game is simply
to reach the end of the level without dying. 

## Our Game
Our game turned out to be a combination of the original inspiration and the classic super mario game.
The objective of the game is to get as far as possible, as fast as possible, while collecting coins to increase score
and avoid obstacles to not die. If playing multiplayer mode, the opposing player can press a button
in the top right hand corner, shoving the active player in a random direction in
order to try to stop him from getting a good score.



![GIF](https://i.imgur.com/MCSkLUW.mp4)


## Project overview


<img src="https://github.com/tanchuanxin/tdt4240_project/blob/main/android/assets/ProjectStructure.png?raw=true">



## User Manual

To run this game you will need an Android device with API level 30 or later, or
Android Studios. In order to save scores and see the high score list, you need to
be connected to wi-fi and use either Android emulator or an Android device. The
app can be run on desktop, but the online multiplayer component(leaderboards,
score submission) will not work.

There are two ways of playing our game. You can either download the game for
your android device, or you can clone the codebase and run the game through
the Android Studio IDE(if you don’t have it, just google it and download).

### On Android device
1. Make sure your device has an Android API version 30 or more
2. Make sure that the browser you will be using for the download of the APK-file from has enabled downloads from unknown sources (Settings — Security
— Install from unknown sources — Enable browser).
3. Click this link: https://drive.google.com/file/d/1fKjBXbZBmYt4hdGbiVmrmn3dPx12aUFP/view?usp=sharing
4. Install the APK file on your Android device
5. Open the app you have installled to play the game



### On computer
1. Clone the repo using:
 ```bash 
git clone git@github.com:tanchuanxin/tdt4240_project.git
```
or 
 ```bash 
git clone https://github.com/tanchuanxin/tdt4240_project.git
```

2. Open the project in Android Studio
3. Run the project with an Android emulator, an Android device connected to your computer, or in DesktopLauncher.




## How to play

When the game launches, you will be shown the menu. Here you can
select game mode(1 or 2 players), or go check the leaderboards, or exit the game.
To start a game against a friend, click 2 Player.

Then you will be directed to a map select menu. To begin the game,
click the map you wish to play.

### Controls on Desktop
UP | W | SPACE - Jump/Walljump

LEFT | A - Move left

RIGHT | D - Move Right

DOWN | S - Stomp if in the air

LEFTMOUSE - Attack (click punch icon)

### Controls on Mobile
JUMPBUTTON - Jump/Walljump

JUMPBUTTON - Stomp if in the air

TOUCHPAD - Move left, right

LEFTMOUSE - Attack (click punch icon)


Trailer video on Youtube

[![TRAILER](https://img.youtube.com/vi/HLZb8V3Nm3s/0.jpg)](https://www.youtube.com/watch?v=HLZb8V3Nm3s)


# To TDT4240 staff

You can find a copy of our deliverables (report, apk file etc.) under the folder titled **SUBMISSION**



