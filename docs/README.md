# Krunch: User Guide

Welcome to **Krunch**, a task management chatbot designed to help you stay on top of your tasks with some personality! Krunch is a feature-rich program built on Java that combines both a Command Line Interface and Graphical User Interface (GUI). It allows users to create, manage, and save different types of tasks like ToDo items, Deadlines, and Events efficiently.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Getting Started](#getting-started)
4. [CLI Commands](#cli-commands)
5. [Task Types](#task-types)
6. [Graphical User Interface (GUI)](#graphical-user-interface-gui)
7. [Saving and Loading Tasks](#saving-and-loading-tasks)
8. [Acknowledgements](#acknowledgements)

---

## Introduction

Krunch is your sassy task management assistant that helps you organize your tasks while delighting (or annoying) you with its personality. Whether you're a student juggling deadlines, a professional managing events, or simply someone who wants structured task management, **Krunch** has got you covered.

---

## Features

- **Multiple Task Types:**
    - Basic ToDo tasks.
    - Tasks with Deadlines.
    - Events with specific time ranges.

- **Task Management:**
    - Add, delete, or edit tasks.
    - Mark tasks as done or undone.
    - View a list of all your tasks.

- **Persistence:**
    - Saves tasks to a file (`tasks.txt`).
    - Automatically reloads tasks when the application restarts.

- **Command Line Interface:**
    - Flexible and interactive for quick task additions and updates.

- **Graphical User Interface (GUI):**
    - An intuitive JavaFX-powered interface for better user experience.

- **Error Handling:**
    - Friendly responses for invalid inputs to guide users.

---

## Getting Started

### Installation

1. **Prerequisites:**
    - Ensure you have [Java JDK 17 or later](https://www.oracle.com/java/technologies/javase-downloads.html).
    - Install [JavaFX](https://openjfx.io/) library if it's not already included in your setup.

2. **Running the Application:**
    - Clone/download the repository from GitHub:
```shell script
git clone <repository-url>
```
- Navigate to the directory:
```shell script
cd <repository-folder>
```
- Compile and run the program:
```shell script
javac -cp . Krunch/gui/Launcher.java
     java -cp . Krunch.gui.Launcher
```

---

## CLI Commands

Here is a list of commands you can use with Krunch in Command Line Interface (CLI) mode:

| **Command**          | **Purpose**                                                                                     | **Example**                                             |
|-----------------------|-------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| `todo <task>`         | Adds a ToDo task.                                                                              | `todo Read book`                                        |
| `deadline <task> /by <yyyy-mm-dd>` | Adds a deadline with a task description and due date.                                           | `deadline Finish assignment /by 2023-11-30`            |
| `event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>` | Adds an event with a start and end date.                                              | `event Conference /from 2023-12-01 /to 2023-12-02`     |
| `list`                | Lists all current tasks.                                                                       | `list`                                                 |
| `mark <task-number>`  | Marks a task as completed.                                                                     | `mark 2`                                               |
| `unmark <task-number>`| Unmarks a completed task.                                                                      | `unmark 3`                                             |
| `delete <task-number>`| Deletes a task from the list.                                                                  | `delete 1`                                             |
| `find <keyword>`      | Lists all tasks containing the provided keyword.                                               | `find read`                                            |
| `bye`                 | Exits the program                                                    | `bye`                                                  |

---

## Task Types

Krunch supports three types of tasks:

1. **ToDo**
    - For basic tasks without any date or time constraints.
    - Format: `[T][X] Read book`

2. **Deadline**
    - To manage tasks with a specific deadline.
    - Format: `[D][X] Finish assignment (by: Nov 30 2023)`

3. **Event**
    - To manage events with a specific start and end date.
    - Format: `[E][ ] Conference (from: Dec 01 2023 to: Dec 02 2023)`

Each task type is displayed with a unique format and status (`[X]` for completed, `[ ]` for incomplete).

---

## Graphical User Interface (GUI)

**Krunch** also features a GUI built with JavaFX. The GUI provides a sleek and user-friendly interface to add and manage tasks.

### Key Components:
1. **User Input Field:**
    - Enter your commands here just like in the CLI.

2. **Send Button:**
    - Click this button or press Enter to process your input.

3. **Dialog Box:**
    - Displays user input on the right and Krunch's witty responses on the left.

### How to Use:
- Start typing your commands in the input field.
- Press Enter or click on the **Send** button to submit.
- View Krunch's response in real-time as tasks are added, modified, or managed.

---

## Saving and Loading Tasks

Krunch automates task saving and loading so your tasks persist between runs:

### Saving
- Task data is written to a file named `tasks.txt` in the root directory.
- Each task is saved in a specific format for easy retrieval later.

### Loading
- On startup, **Krunch** retrieves tasks from `tasks.txt`.
- If the file is missing or corrupted, a new empty list is started.

---

Happy task managing with **Krunch!** 😊

---
### Acknowledgements
- IntelliJ's AI Assistant helped in doing this User Guide's format and polished the details of it.
