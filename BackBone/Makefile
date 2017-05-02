#Slick2D libraries needed to build the game.
JAVALIBS=game/lib/jinput.jar:game/lib/lwjgl.jar:game/lib/lwjgl_util.jar:game/lib/slick.jar:.

#This variable defines game's base directory
BASEDIR=game/

#This variable lists all the files and directories that we will put into our resulting Game.jar
JARFILES=$(BASEDIR)manifest.txt\
         $(BASEDIR)Game.class\
         $(BASEDIR)Target.class\
         $(BASEDIR)Scoreboard.class\
         $(BASEDIR)Ball.class\
         $(BASEDIR)Constants.class\
         $(BASEDIR)Timer.class\
	 $(BASEDIR)GameMusic.class\
	 $(BASEDIR)GameObject.class\
	 $(BASEDIR)Player.class\
	 $(BASEDIR)Powerbar.class\
	 $(BASEDIR)Trustee.class\
         $(BASEDIR)res\
         $(BASEDIR)lib


#This is needed so that GNU Make recognizes .java and .class files 
#And knows how to go from one to the other
.SUFFIXES: .java .class
.java.class:
	javac $*.java

#Following is the list of targets and dependencies
#default target depends on Game.jar
all: Game.jar

#Game.jar depends on all of the $(JARFILES)
Game.jar: $(JARFILES)
	jar cvfm Game.jar $(JARFILES)

#Game.class depends on all of the .class files and Game.java
$(BASEDIR)Game.class: $(BASEDIR)Trustee.class $(BASEDIR)Powerbar.class $(BASEDIR)Player.class $(BASEDIR)GameObject.class $(BASEDIR)Target.class $(BASEDIR)Scoreboard.class $(BASEDIR)Ball.class $(BASEDIR)Constants.class $(BASEDIR)GameMusic.class $(BASEDIR)Game.java
	javac -cp $(JAVALIBS):$(BASEDIR)Trustee.class:$(BASEDIR)GameObject.class:$(BASEDIR)Powerbar.class:$(BASEDIR)Player.class:$(BASEDIR)Target.class:$(BASEDIR)Scoreboard.class:$(BASEDIR)Ball.class:$(BASEDIR)Constants.class:$(BASEDIR)GameMusic.class:. $(BASEDIR)Game.java

#Scoreboard.class depends on Constants.class, GameObject.class and Scoreboard.java
$(BASEDIR)Scoreboard.class: $(BASEDIR)Constants.class $(BASEDIR)GameObject.class $(BASEDIR)Scoreboard.java
	javac -cp $(JAVALIBS) $(BASEDIR)Scoreboard.java

#Ball.class depends on Constants.class, GameObject.class and Ball.java
$(BASEDIR)Ball.class: $(BASEDIR)Constants.class $(BASEDIR)GameObject.class $(BASEDIR)Ball.java
	javac -cp $(JAVALIBS) $(BASEDIR)Ball.java

#Timer.class depends on Timer.java and GameObject.class
$(BASEDIR)Timer.class: $(BASEDIR)Timer.java $(BASEDIR)GameObject.class
	javac -cp $(JAVALIBS) $(BASEDIR)Timer.java

#Target.class depends on Target.java and GameObject.class
$(BASEDIR)Target.class: $(BASEDIR)Target.java $(BASEDIR)GameObject.class
	javac -cp $(JAVALIBS) $(BASEDIR)Target.java

#GameObject.class depends on GameObject.java
$(BASEDIR)GameObject.class: $(BASEDIR)GameObject.java
	javac -cp $(JAVALIBS) $(BASEDIR)GameObject.java

#Constants.class depends on Constants.java
$(BASEDIR)Constants.class: $(BASEDIR)Constants.java
	javac -cp $(JAVALIBS) $(BASEDIR)Constants.java

#GameMusic.class depends on GameMusic.java
$(BASEDIR)GameMusic.class: $(BASEDIR)GameMusic.java
	javac -cp $(JAVALIBS) $(BASEDIR)GameMusic.java

#Player.class depends on Player.java and GameObject.class
$(BASEDIR)Player.class: $(BASEDIR)Player.java $(BASEDIR)GameObject.class
	javac -cp $(JAVALIBS) $(BASEDIR)Player.java

#Powerbar class depends on Powerbard.java and GameObject.class
$(BASEDIR)Powerbar.class: $(BASEDIR)Powerbar.java $(BASEDIR)GameObject.class
	javac -cp $(JAVALIBS) $(BASEDIR)Powerbar.java

#Trustee class depends on Trustee.java and GameObject.class
$(BASEDIR)Trustee.class: $(BASEDIR)Trustee.java $(BASEDIR)GameObject.class
	javac -cp $(JAVALIBS) $(BASEDIR)Trustee.java



clean:
	rm $(BASEDIR)*.class

