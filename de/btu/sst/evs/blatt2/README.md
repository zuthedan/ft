# Exercise sheet 2 - Task 7  #

In order to compile the file 

* TimeFormatter.java
 
you need the JavaFX library. Please make sure that you can run JavaFX programs. This should be the case if you are running an Oracle Java Development Kit 8 (JDK) update 40 or higher. If you run the Open JDK please make sure that you installed JavaFX separately (e.g. by installing the OpenJFX RCP). 

### Compile and Run - Oracle JDK ####

Use the following commands for compiling the program, if you use an Oracle JDK:

* javac TimeFormatter.java
* java TimeFormatter

### Compile and Run - Open JDK + OpenJFX ####

If you use an Open JDK including the OpenJFX RCP, you need to include the "jfxrt.jar" file from your JDK/JRE libraries into the classpath 

Linux / MacOS:
* javac -cp .:"\<PATH_TO_JFXRT.JAR\>" TimeFormatter.java
* java TimeFormatter

Windows:  
* javac -cp .;"\<PATH_TO_JFXRT.JAR\>" TimeFormatter.java
* java TimeFormatter

In the above commands \<PATH_TO_JFXRT.JAR\> needs to be replaced by the concrete path to the corresponding file.

If you have any trouble compiling the software, please consult Mathias Schubanz at M.Schubanz@b-tu.de