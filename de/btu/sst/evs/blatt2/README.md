# Exercise sheet 2 - Task 7  

The class `TimeFormatter` provides you with a graphical user interface to pass a number of seconds which then has to be transformed into the required format. Your contribution should be made exactly where you see the "TODO" markers in the code. These are in the following two methods:

* `private boolean isNumeric(String str)` 
    *  This method checks whether a given string is a numeric value. The result is returned as a boolean value.
* `private String formatTime(int inputValue)` 
    * This method transforms a given integer into a string. The result string has to be formatted as described in the exercise sheet. 


## Clone the project to you local machine

Go [the project's root folder](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen/) and copy git project URL (linked [here](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen.git)). Open a git client and clone that URL to your machine. If you use the command line interface (cli) navigate to your workspace or the space where you want the code to be and type the folliwing:

* `git clone https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen.git`

This will clone the project into your local workspace. In order to compile and run the software, follow the following instructions.

## Compile and Run 

In order to compile the file 

* `TimeFormatter.java`

you need the JavaFX library. Please make sure that you can run JavaFX programs. This should be the case if you are running an Oracle Java Development Kit 8 (JDK) update 40 or higher. If you run the Open JDK please make sure that you installed JavaFX separately (e.g. by installing the OpenJFX). 

When you compile a java file with a package declaration you have to compile it from the projec's root directory. For our TimeFormatter this means, navigate to [the project's root](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen/tree/master) on the command line interface (cli) and compile the file as follows (assuming JavaFX is running as you are running the Oracle JDK 9): 

* `javac de/btu/sst/evs/blatt2/TimeFormatter.java`

The folder structure exactly matches the package declaration. If it doesn't, you will fail to compile to java file. As soon as it compiles without errors,  start the program as follows:

* `java de.btu.sst.evs.blatt2.TimeFormatter`

If you keep struggling with the package structure you may also remove the package declaration. Navigate back to the folder containing [TimeFormatter.java](de/btu/sst/evs/blatt2/TimeFormatter.java) and execute the following commands:

* `javac TimeFormatter.java`
* `java TimeFormatter`

Pay attention to the missing ". java" ending when you start the program. This is necessary to start the program successfully.


## Compile and Run - Open JDK + OpenJFX

If you use an Open JDK including the OpenJFX RCP, you need to include the "jfxrt.jar" file from your JDK/JRE libraries into the classpath. The following commands need to be executed (the package declaration is ignored here):

Linux / MacOS:
* `javac -cp .:"<PATH_TO_JFXRT.JAR>" TimeFormatter.java`
* `java TimeFormatter`

Windows:  
* `javac -cp .;"<PATH_TO_JFXRT.JAR>" TimeFormatter.java`
* `java TimeFormatter`

In the above commands `<PATH_TO_JFXRT.JAR>` needs to be replaced by the concrete path to the corresponding file. If 

If you have any trouble compiling the software, please consult Mathias Schubanz at [M.Schubanz@b-tu.de](mailto:M.Schubanz@b-tu.de)

Good luck! 