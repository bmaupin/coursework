# CS106A - Programming Methodology (Stanford)
https://see.stanford.edu/Course/CS106A

<br>
**To set up Eclipse with the Stanford plugin:**
- If you don't currently use Eclipse, follow the instructions here:  
  http://web.stanford.edu/class/cs106a/eclipse.shtml
  
- If you already use Eclipse:
  1. Go here to see what version of Eclipse Stanford is currently using:  
  http://www.martystepp.com/software
  
    Ex: eclipse-here-is-mars-2.txt = Mars
  
  2. Upgrade Eclipse to the version Stanford is using  
  https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F
  
  3. Make sure JDT is installed
    1. *Help* → *Install New Software* → *Work with* → name of Stanford Eclipse version (e.g. Mars)
    2. Select *Programming Languages* → *Eclipse Java Development Tools* → Next → follow the instructions to finish installation  
    (If you have *Hide items that are already installed* checked and this doesn't show up, you probably already have it installed)
  
  4. Download the Stanford plugin
    1. Go here:  
    http://web.stanford.edu/class/cs106a/eclipse.shtml
    2. Scroll down to the bottom of the page and click *Download Stanford Eclipse plugin*
  
  5. Copy the Stanford plugin to the *dropins* folder inside your Eclipse installation
  
  6. Close and reopen Eclipse for the plugin to install
  
  7. *Project* → make sure *Build Automatically* is checked

<br>
**To get Karel working in Eclipse:**

1. Download and install Java 6
  - Ubuntu:  
    https://gist.github.com/bmaupin/16855ce1b2484c459f41ad836a7d3f2f
  - Others:  
    http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html#jdk-6u45-oth-JPR

2. Configure your project to use Java 6
  1. Right-click the assignment project that requires Karel (e.g. Assignment1) → *Properties* → *Java Build Path* → *Add Library* → *JRE System Library* → *Next*
  
  2. If you haven't yet set up Java 6 in Eclipse:
    1. *Installed JREs* → *Add* → *Standard VM* → *Next* → *JRE home* → path to JRE (e.g. /usr/lib/jvm/java-6-oracle) → *Finish* → *OK*
    
  3. Select *Alternate JRE* → select the Java 6 JRE → *Finish*

  4. Select any other JREs → *Remove*
  
  5. *Java Compiler* → check *Enable project specific settings* → *Compiler compliance level* → *1.6* → *OK*
