@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del NO_ERROR_ACTUAL.TXT
del INVALID_COMMANDS_ACTUAL.TXT
del INVALID_PARAMETERS_ACTUAL.TXT
del FILE_ERROR_ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ^
..\src\main\java\duke\Duke.java ^
..\src\main\java\duke\task\TaskManager.java ^
..\src\main\java\duke\task\TaskType.java ^
..\src\main\java\duke\task\Task.java ^
..\src\main\java\duke\task\Todo.java ^
..\src\main\java\duke\task\Deadline.java ^
..\src\main\java\duke\task\Event.java ^
..\src\main\java\duke\task\TaskAction.java ^
..\src\main\java\duke\task\MissingTaskLiteralException.java ^
..\src\main\java\duke\command\IllegalCommandException.java ^
..\src\main\java\duke\command\Command.java ^
..\src\main\java\duke\command\CommandManager.java ^
..\src\main\java\duke\file\DataParser.java ^
..\src\main\java\duke\file\FileManager.java ^
..\src\main\java\duke\file\FileWritable.java ^
..\src\main\java\duke\file\FileCorruptedException.java


IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM compare the output to the expected output

del .\data\duke.txt
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < noErrorInput.txt > NO_ERROR_ACTUAL.TXT
FC NO_ERROR_ACTUAL.TXT NO_ERROR_EXPECTED.TXT

del .\data\duke.txt
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < invalidCommandsInput.txt > INVALID_COMMANDS_ACTUAL.TXT
FC INVALID_COMMANDS_ACTUAL.TXT INVALID_COMMANDS_EXPECTED.TXT

del .\data\duke.txt
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < invalidParametersInput.txt > INVALID_PARAMETERS_ACTUAL.TXT
FC INVALID_PARAMETERS_ACTUAL.TXT INVALID_PARAMETERS_EXPECTED.TXT

del .\data\duke.txt
echo hello > .\data\duke.txt
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < fileErrorInput.txt > FILE_ERROR_ACTUAL.TXT
FC FILE_ERROR_ACTUAL.TXT FILE_ERROR_EXPECTED.TXT