# User Guide
Duke is a command line program that helps user to manage their schedules.
This program is best suited for people who can type fast.

- [1. Quick Start](#quick-start)
- [2. Command Format](#command-format)
- [3. Features](#features)
    - [Add a task:](#adding-a-task-todo-deadline-event) `todo` `deadline` `event`
    - [Toogle task done:](#toggling-task-done-done) `done`
    - [Delete a task:](#deleting-a-task-delete) `delete`
    - [List all tasks:](#listing-all-tasks-list) `list`
    - [Find a task by keyword or date:](#find-tasks-find) `find`
    - [Exiting the program:](#exiting-the-program-bye) `bye`
- [4. Date format](#date-formats)
- [5. FAQ](#FAQ)
- [6. Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` and above installed in your computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to a folder of your choice.
4. Start up your favourite terminal of choice and navigate to the folder.
(Ensure width of the terminal is at least 63 pixels for the best viewing experience)
5. Run `java -jar duke.jar` and you will be prompted with an opening greeting.
![main](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/main.jpg)
6. You can view the number of todos, deadlines and events you have at the command header.
7. If there is no `./data/duke.txt` file, the duke program will auto create one for you
![createStore](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/createStore.jpg)
8. You can now start entering commands
9. Refer to [Features](#features) below for all the available commands.

## Command Format
- Words in `UPPERCASE` are parameters to be supplied by the user.
- Words encased in `<>` are required parameters or else you will receive an error
- Words encased in `()` are optional parameters that you can disregard.
- Type `help` in the program if you need easy reference to the format.

## Features 

### Adding a task `todo` `deadline` `event`
Adds a task to the schedule list.

#### Format:

- The date can be replaced with `today` to set date to today's date at 2359
- You cannot add repeated tasks
    - todo will compare with all the other descriptions of todo tasks
    - deadline will compare with all the other descriptions and date time of deadline tasks
    - event will compare with all the other descriptions and date time of event tasks

`todo <TASK>`

`deadline <TASK> /by <DATE>`

`event <TASK> /at <DATE>`

#### Examples:
`todo read book`

`deadline CS2113 project /by 11 oct 2020 12pm`

`deadline CS2113 project /by today`

`event concert /at 22 oct 2020 9.30pm`

`event concert /at today`

#### Expected Outcome:
![addTodo](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/addTodo.jpg)
![addDeadline](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/addDeadline.jpg)
![addEvent](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/addEvent.jpg)

<br/>
<br/>

### Toggling task done `done`
- Toggles the status of the task.
- If task is currently not done, set task to be done.
- If task is currently done, set task to not done.
- Task done is represented by [Y]
- Task not done is represented by [X]

#### Format: 
- The index corresponds to the numbers at the side of the `list` command.

`done <INDEX>`

#### Examples:
`done 1`

#### Expected Outcome:
![done](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/done1.jpg)
![undone](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/done2.jpg)

<br/>
<br/>

### Deleting a task `delete`
Remove a task from your schedule.
 
#### Format: 
`delete <INDEX>`

#### Examples:
`delete 1`

#### Expected Outcome:
![addTodo](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/deleteTask.jpg)

<br/>
<br/>

### Listing all tasks `list`
Shows a list of all tasks in the user's schedule.
 
#### Format:
- order can be either ascending: `asc`, or descending `desc`

`list (order)`

#### Examples:
`list`

`list asc`

`list desc`

#### Expected Outcome:
![list](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/listTask.jpg)

<br/>
<br/>

### Find tasks `find`
Find tasks which contains any of the given keywords or dates.
 
#### Format:

- The search using keyword is case-insensitive. e.g `cs2113` will match `CS2113`
- The date can be replaced with `today` to find tasks on today's date.
- Todo tasks will have their date set as today's date.
- To search any tasks on the given date , use the `/on` literal
- To search any tasks after the given date , use the `/af` literal
- To search any tasks before the given date, use the `/bf` literal

`find (KEYWORD) (/on <DATE>)`

`find (KEYWORD) (/bf <DATE>)`

`find (KEYWORD) (/af <DATE>)`

#### Examples:
`find book`

`find /on today`

`find /af 20 jun 2020`

`find project /bf 13 oct 2020`

#### Expected Outcome:
![find](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/findTask.jpg)

<br/>
<br/>

### Exiting the program `bye`
Exits the program.
 
#### Format: 
`bye`

#### Examples:
`bye`

#### Expected Outcome:
![bye](https://raw.githubusercontent.com/AndreWongZH/ip/master/docs/assets/images/bye.jpg)

<br/>
<br/>

## Date formats

- `am` and `pm` are case-insensitive.
- Months of the dates are case-insensitive.
- `d` is the day of the month.
- `M` is the month. (`03` `3` or `mar`)
- `y` is the year.
- `H` is the hour in 24-hour.
- `h` is the hour in 12-hour.
- `m` is the minute.
- `a` is am or pm.

Format | examples
-------|---------
`d MMM yyyy HHmm` | `2 mar 2019 1800`
`dd/M/yyyy HHmm` | `2/3/2019 1800`
`dd-M-yyyy HHmm` | `2-3-2019 1800`
`d MMM yyyy HH:mm` | `2 mar 2019 18:00`
`dd/M/yyyy HH:mm` | `2/3/2019 18:00`
`dd-M-yyyy HH:mm` | `2-3-2019 18:00`
`d MMM yyyy h.mma` | `2 mar 2019 6.00pm`
`dd/M/yyyy h.mma` | `2/3/2019 6.00pm`
`dd-M-yyyy h.mma` | `2-3-2019 6.00pm`
`d MMM yyyy h mma` | `2 mar 2019 6 00pm`
`dd/M/yyyy h mma` | `2/3/2019 6 00pm`
`dd-M-yyyy h mma` | `2-3-2019 6 00pm`
`d MMM yyyy ha` | `2 mar 2019 6pm`
`dd/M/yyyy ha` | `2/3/2019 6pm`
`dd-M-yyyy ha` | `2-3-2019 6pm`
`dd MMM yyyy h:mm a` | `2 mar 2019 6:00 pm`

<br/>
<br/>

## FAQ

Q: Can I edit the data directly?

A: Yes you can, but please follow the format of the file. If the file cannot be read then 
the program will recreate duke.txt.

<br/>
<br/>

## Command Summary

Action | Format | Examples
--------|--------|----------
**Help** | `help`
**Add** | `todo <TASK>` `deadline <TASK> /by <DATE>` `event <TASK> /at <DATE>` | `todo read book` `deadline CS2113 project /by 1 oct 2020 12pm` `event concert /at today`
**Done** | `done <INDEX>` | `done 1`
**Delete** | `delete <INDEX>` | `delete 3`
**List** | `list (order)` | `list` `list asc` `list desc`
**Find** | `find (KEYWORD) (/on <DATE>)` `find (KEYWORD) (/bf <DATE>)` `find (KEYWORD) (/af <DATE>)` | `find book` `find /on today` `find /af 20 jun 2020` `find project /bf 13 oct 2020`
**Bye** | `bye`