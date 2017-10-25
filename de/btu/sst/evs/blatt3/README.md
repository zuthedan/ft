# Exercise sheet 3 - Task 1  

The class `Aufgabe1.java` provides you with a structure and helper methods to read input from the CLI. This input has to be processed according to the six available options to choose from. Correspondingly, it is your task to modify the code in a way that the software ...
* ... keeps prompting for input from the CLI until you tell it to stop
* ... will compute the required values (listed in the prompted menu text) and outputs them.

In order to read values from the CLI use the provided helper functions:
* `private static double readDouble()`
    * The method reads a positive `double` value from the CLI and returns it to the caller. If the value is negative, `0` will be returned. If the given input is not a `double` value, the method will throw an exception. 
* `private static int readInt()`
    * The method reads a positive `int` value from the CLI and returns it to the caller. If the value is negative, `0` will be returned. If the given input is not an `int` value, the method will throw an exception. 

Your contribution should be made


## Clone the project to your local machine

Go [the project's root folder](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen/) and copy git project URL (linked [here](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen.git)). Open a git client and clone that URL to your machine. If you use the command line interface (cli) navigate to your workspace or the location where you want the code to be and type the following:

* `git clone https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen.git`

This will clone the project into your local workspace. If you want to check out the whole project into an IDE only refer to the repository URL:

* `https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen.git`

In order to compile and run the software, follow the instructions in the following sections.

## Compile and Run 

When you compile a java file with a package declaration you have to compile it from the project's root directory. For the class `Aufgabe1`, this means, navigate to [the project's root](https://git.informatik.tu-cottbus.de/schubmat/Entwicklung_von_Softwaresystemen/tree/master) on the command line interface (cli) and compile the file as follows: 

* `javac de/btu/sst/evs/blatt3/Aufgabe1.java`

The folder structure exactly matches the package declaration. If it doesn't, you will fail to compile the java file. As soon as it compiles without errors,  start the program as follows (analogous to the package declaration):

* `java de.btu.sst.evs.blatt3.Aufgabe1`

If you keep struggling with the package structure you may also remove the package declaration. Navigate back to the folder containing [Aufgabe1.java](de/btu/sst/evs/blatt3/Aufgabe1.java) and execute the following commands:

* `javac Aufgabe1.java`
* `java Aufgabe1`

Pay attention to the missing ". java" ending when you start the program. This is necessary to start the program successfully.

If you have any trouble compiling the software, please consult Mathias Schubanz at [M.Schubanz@b-tu.de](mailto:M.Schubanz@b-tu.de)

Good luck! 