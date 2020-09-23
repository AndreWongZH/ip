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
- [5. Command Summary](#command-summary)

## Quick Start
1. Ensure you have Java `11` installed in your computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to a folder of your choice.
4. Start up your favourite terminal of choice and navigate to the folder.
5. Run `java -jar duke.jar` and you will be prompted with an opening greeting.
6. You can now start to enter commands
7. Refer to [Features](#features) below for all the available commands.

## Command Format
- Words in `UPPERCASE` are parameters to be supplied by the user.
- Words encased in `<>` are required parameters or else you will receive an error
- Words encased in `()` are optional parameters that you can disregard.

## Features 

### Adding a task `todo` `deadline` `event`
Adds a task to the schedule list.

#### Format: 
`todo <TASK>`

`deadline <TASK> /by <DATE>`

`event <TASK> /at <DATE>`

#### Examples:
`todo read book`

`deadline CS2113 project /by 11 oct 2020 12pm`

`event concert /at 22 oct 2020 9.30pm`

>#### Expected Outcome:

<br/>
<br/>

### Toggling task done `done`
Toggles the status of the task.
If task is currently not done, set task to be done.
If task is currently done, set task to not done.
 
#### Format: 
`done <INDEX>`

#### Examples:
`done 1`

#### Expected Outcome:

<br/>
<br/>

### Deleting a task `delete`
Remove a task from your schedule.
 
#### Format: 
`delete <INDEX>`

#### Examples:
`delete 1`

#### Expected Outcome:

<br/>
<br/>

### Listing all tasks `list`
Shows a list of all tasks in the user's schedule.
 
#### Format: 
`list`

#### Examples:
`list`

#### Expected Outcome:

<br/>
<br/>

### Find tasks `find`
Find tasks which contains any of the given keywords or dates.
 
#### Format:

- The search using keyword is case-insensitive. e.g `cs2113` will match `CS2113`
- The date can be replaced with today to find tasks on today's date.
- Todo tasks will have their date set as today's date.

`find (KEYWORD) (/on <DATE>)`

`find (KEYWORD) (/bf <DATE>)`

`find (KEYWORD) (/af <DATE>)`

#### Examples:
`find book`

`find /on today`

`find /af 20 jun 2020`

`find project /bf 13 oct 2020`

#### Expected Outcome:

<br/>
<br/>

### Exiting the program `bye`
Exits the program.
 
#### Format: 
`bye`

#### Examples:
`bye`

#### Expected Outcome:

<br/>
<br/>

## Date formats

<br/>
<br/>

## Command Summary

Action | Format | Examples
--------|--------|----------
**Add** | `todo <TASK>` `deadline <TASK> /by <DATE>` `event <TASK> /at <DATE>` | `todo read book` `deadline CS2113 project /by 1 oct 2020 12pm` `event <TASK> /at <DATE>`
**Done** | `done <INDEX>` | `done 1`
**Delete** | `delete <INDEX>` | `delete 3`
**List** | `list`
**Find** | `find (KEYWORD) (/on <DATE>)` `find (KEYWORD) (/bf <DATE>)` `find (KEYWORD) (/af <DATE>)` | `find book` `find /on today` `find /af 20 jun 2020` `find project /bf 13 oct 2020`
**Bye** | `bye`