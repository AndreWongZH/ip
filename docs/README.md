# User Guide
Duke is a command line program that helps user to manage their schedules.
This program is best suited for people who can type fast.

## Features 

### Adding a task `todo` `deadline` `event`
Adds a task to the schedule list.

>#### Format: 
>`todo <TASK>`
>
>`deadline <TASK> /by <DATE>`
>
>`event <TASK> /at <DATE>`

>#### Examples:
>`todo read book`
>
>`deadline CS2113 project /by 1 oct 2020 12pm`
>
>`event concert /at 22 oct 2020 9.30pm`

>#### Expected Outcome:


### Toggling task done `done`
Toggles the status of the task.
If task is currently not done, set task to be done.
If task is currently done, set task to not done.
 
>#### Format: 
>`done <INDEX>`

>#### Examples:
>`done 1`

>#### Expected Outcome:


### Deleting a task `delete`
Remove a task from your schedule.
 
>#### Format: 
>`delete <INDEX>`

>#### Examples:
>`delete 1`

>#### Expected Outcome:


### Listing all tasks `list`
Shows a list of all tasks in the user's schedule.
 
>#### Format: 
>`list`

>#### Examples:
>`list`

>#### Expected Outcome:


### Find tasks `find`
Find tasks which contains any of the given keywords or dates.
 
>#### Format: 
>The search using keyword is case-insensitive. e.g `cs2113` will match `CS2113`
>
>The date can be replaced with today to find tasks on today's date.
>
>Todo tasks will have their date set as today's date.
>
>`find (KEYWORD) (/on <DATE>)`
>
>`find (KEYWORD) (/bf <DATE>)`
>
>`find (KEYWORD) (/af <DATE>)`

>#### Examples:
>`find book`
>
>`find /on today`
>
>`find /af 20 jun 2020`
>
>`find project /bf 13 oct 2020`

>#### Expected Outcome:


### Exiting the program `bye`
Exits the program.
 
>#### Format: 
>`bye`

>#### Examples:
>`bye`

>#### Expected Outcome:


## Command Summary
Action | Format, Examples
--------|------------------
**Add** | `todo <TASK>` `deadline <TASK> /by <DATE>` `event <TASK> /at <DATE>` <br> e.g., `todo read book` `deadline CS2113 project /by 1 oct 2020 12pm` <br/> `event <TASK> /at <DATE>`
**Done** | `done <INDEX>` <br> e.g., `done 1`
**Delete** | `delete <INDEX>`<br> e.g., `delete 3`
**List** | `list`
**Find** | `find (KEYWORD) (/on <DATE>)` `find (KEYWORD) (/bf <DATE>)` `find (KEYWORD) (/af <DATE>)`<br> e.g., `find book` `find /on today` `find /af 20 jun 2020` <br/> `find project /bf 13 oct 2020`
**Bye** | `bye`