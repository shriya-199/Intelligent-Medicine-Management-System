# Medicine Management System (Java Console Application)

The Medicine Management System is a robust, modular, and console-based Java application developed to manage personal or patient-centric medication schedules. This system offers functionality to record, update, monitor, and remind users about their daily medicines in a structured and conflict-free manner. It provides a real-world solution tailored for patients, caregivers, and healthcare support systems that need lightweight, portable, and reliable medicine tracking without the overhead of a complex GUI or cloud infrastructure.

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Technical Specifications](#technical-specifications)
- [System Architecture](#system-architecture)
- [Functional Modules](#functional-modules)
- [Sample Workflow](#sample-workflow)
- [Developer Profile](#developer-profile)
- [Getting Started](#getting-started)

---

## Overview

This project simulates a medicine tracking system designed to:

- Record medicines with associated dose schedules.
- Notify users about upcoming doses in real-time.
- Track changes and generate a full history of actions.
- Prevent conflicting dose timings.
- Provide a user-friendly console-based interface.

The goal is to offer an efficient solution for individuals or caregivers who need to manage regular medications in a structured way. The system ensures reliability, clarity, and safety through strict scheduling validation and background processing for timely reminders.

---

## Key Features

### 1. Medicine Schedule Management
- Add, update, and delete medicines.
- Assign schedules based on specific days (e.g., Monday, Wednesday).
- Support for multiple dose times per day (e.g., 8:00 AM, 3:30 PM).
- Schedule validation to prevent timing conflicts across medicines.

### 2. Background Dose Reminder
- A background thread continuously checks for upcoming doses.
- Sends real-time console notifications as soon as a scheduled time is reached.

### 3. Historical Logging
- Every action (add/update/delete) is timestamped.
- Maintains a complete history of changes for accountability and audit purposes.

### 4. Console-Based User Interface
- Uses ANSI escape codes for structured color-coded output (platform-independent).
- Simple and intuitive menu-driven interaction.

---

## Technical Specifications

- **Language:** Java (JDK 17+ recommended)
- **Data Structures:** ArrayList, HashMap, LocalDate, LocalTime
- **Concurrency:** Java Threads
- **I/O Handling:** `Scanner`, `System.out`, `ANSI escape codes`
- **Time Management:** `java.time` API

---

## System Architecture

The system consists of three core classes:

1. **Medicine.java**
   - Acts as the main entry point.
   - Controls user interaction through a menu.
   - Initializes a background thread for dose notification.
   - Handles CRUD operations on medicines.
   - Manages logs and history display.

2. **MedicineSchedule.java**
- Manages medicine dose schedules including number of doses, timings, and days.
- Implements conflict resolution to prevent overlapping time slots across medicines.
- Stores existing time slots globally to avoid clashes when new medicines are added.
- Provides a real-time notifier to remind users of scheduled doses based on current time and day.
- Supports user input for fully customizable scheduling using createScheduleFromInput() method.

Each class is self-contained, follows object-oriented principles, and communicates using clearly defined methods to ensure modularity and maintainability.

---

## Functional Modules

### 1. Medicine Registration
Allows users to input:
- Medicine name
- Dosage details (e.g., 500mg)
- Schedule: Days (Monday–Sunday), Dose Times (in 24-hr format)

### 2. Updation
- The Update function allows you to update medicine 
- The updateSchedule is used to update the schedule of the existing medicine

### 3. Schedule Validation
- Checks if a new medicine conflicts with existing scheduled times.
- Prevents two medicines from being taken at the exact same time on the same day.

### 4. Notifications Engine
- Runs as a background thread.
- Uses the system clock to trigger alerts when a dose time matches the current time.
- Efficient and lightweight, with a one-minute interval for checks.

### 5. History Tracking
- Every action is stored with a timestamp.
- Accessible via the menu for reviewing past changes or debugging.

### 6. View Schedule
- The medicies that are added, their schedule will be displayed

---

## Getting Started

### Prerequisites
- Java SDK 17 or higher installed
- Any IDE or terminal that supports Java console applications

### Sample Workflow
flowchart TD
    A0([Start: Medicine Management System])
    A1[Display welcome message & instructions\nStart reminder thread (loop every 60 secs)]
    A2[Show Main Menu]
    A3[User Input (1–8)]
    
    subgraph Main Options
        B1[1: Add medicine]
        B2[2: View medicines]
        B3[3: Update medicine]
        B4[4: Delete medicine]
        B5[5: View history]
        B6[6: Update schedule]
        B7[7: View schedule]
        B8[8: Exit]
    end

    A0 --> A1 --> A2 --> A3
    A3 --> B1 --> AddStart
    A3 --> B2 --> ViewStart
    A3 --> B3 --> UpdateStart
    A3 --> B4 --> DeleteStart
    A3 --> B5 --> HistStart
    A3 --> B6 --> UpdateSchedStart
    A3 --> B7 --> ViewSchedStart
    A3 --> B8 --> ExitSystem

%% ------------------ Add Medicine ------------------
    subgraph Add Medicine
        AddStart[Start addMedicines()]
        AddPrompt[Prompt: Enter number of medicines]
        AddTry[Try reading integer input]
        AddValid{Input valid?}
        AddInvalid[Print "Invalid input"\nClear buffer\nReturn]
        AddNumCheck{numberOfMedicines > 0?}
        AddErr[Print error & return]
        AddLoop[Loop i = 1 to numberOfMedicines]
        AddName[Prompt for medicine name]
        AddNameValid[Validate name not null/empty]
        AddExistCheck[Check if medicine exists]
        AddExists[Prompt: exists\nAsk: Skip?]
        AddSkip{If YES → skip\nIf NO → ask new name}
        AddNew[Add to list, add to history, prompt for schedule\nStore schedule\nPrint success]
    end

    AddStart --> AddPrompt --> AddTry --> AddValid
    AddValid -->|No| AddInvalid
    AddValid -->|Yes| AddNumCheck
    AddNumCheck -->|No| AddErr
    AddNumCheck -->|Yes| AddLoop
    AddLoop --> AddName --> AddNameValid --> AddExistCheck
    AddExistCheck -->|Yes| AddExists --> AddSkip --> AddLoop
    AddExistCheck -->|No| AddNew --> AddLoop

%% ------------------ Update Medicine ------------------
    subgraph Update Medicine
        UpdateStart[Start updateMedicines()]
        UpdateChoice[Prompt: Choose 1 (Replace) or 2 (Add new)]
        UpdateChoice1[Choice = 1: Replace med]
        UpdateChoice2[Choice = 2: Add new]
        UpdateRemove[Prompt: med to remove\nCheck exists]
        UpdateNotFound[Print not found & break]
        UpdateDel[Remove old med, add to history]
        UpdateNewName[Prompt new name\nValidate: not empty, not same, not dup]
        UpdateSave[Add new, add to history, prompt for schedule\nSave to map\nPrint success]
        UpdateAskMore[Ask update another? yes/no]
    end

    UpdateStart --> UpdateChoice --> UpdateChoice1 --> UpdateRemove
    UpdateRemove -->|Not found| UpdateNotFound
    UpdateRemove -->|Exists| UpdateDel --> UpdateNewName --> UpdateSave --> UpdateAskMore
    UpdateChoice --> UpdateChoice2 --> UpdateSave

%% ------------------ Update Schedule ------------------
    subgraph Update Schedule
        UpdateSchedStart[Start updateScheduleForExistingMedicine()]
        USPrompt[Prompt: Enter medicine name]
        USFound{Medicine found?}
        USNotFound[Print: Not found\nReturn]
        USCreate[Prompt for new schedule\nUse createScheduleFromInput()]
        USUpdate[Update schedule map\nAdd to history\nPrint success]
    end

    UpdateSchedStart --> USPrompt --> USFound
    USFound -->|No| USNotFound
    USFound -->|Yes| USCreate --> USUpdate

%% ------------------ Delete Medicine ------------------
    subgraph Delete Medicine
        DeleteStart[Start deleteMedicines()]
        DelPrompt[Prompt: Enter medicine name]
        DelCheck{Medicine exists?}
        DelNotFound[Print: Not found\nReturn]
        DelRemove[Remove from list, add to history\nPrint success]
    end

    DeleteStart --> DelPrompt --> DelCheck
    DelCheck -->|No| DelNotFound
    DelCheck -->|Yes| DelRemove

%% ------------------ View History ------------------
    subgraph View History
        HistStart[Start viewMedicineHistory()]
        HistCheck{History empty?}
        HistNone[Print: No history\nReturn]
        HistLoop[Loop: print history entries]
        HistPrint[Print all current medicines or "No medicines"]
    end

    HistStart --> HistCheck
    HistCheck -->|Yes| HistNone
    HistCheck -->|No| HistLoop --> HistPrint

%% ------------------ View Schedule ------------------
    subgraph View Schedule
        ViewSchedStart[Start viewMedicineSchedule()]
        VSInput[Prompt: Enter medicine name]
        VSRead[Read & clean input]
        VSLookup[Lookup in schedule map]
        VSSchedFound{Schedule found?}
        VSFound[Print: "Schedule for [name]"]
        VSShow[Print schedule]
        VSNotFound[Print: Not found\nReturn]
    end

    ViewSchedStart --> VSInput --> VSRead --> VSLookup --> VSSchedFound
    VSSchedFound -->|Yes| VSFound --> VSShow
    VSSchedFound -->|No| VSNotFound

%% ------------------ Exit ------------------
    subgraph Exit
        ExitSystem[Exit Program]
    end


MedicineSchedule 
flowchart TD
    A[Start] --> B[Create Schedule From Input]

    B --> B1[Ask "Enter number of doses per day"]
    B1 --> B2[Validate (must be > 0)]

    B2 --> C1[Ask "Enter number of days"]
    C1 --> C2[Validate (must be > 0)]

    C2 --> D[For each day:\n Ask day name (e.g., Monday)\n Add to 'days' list]

    D --> E[For each dose:\n Ask time in HH:mm\n Validate format ✓]

    E --> F[For each entered day:\n Generate slot as "Day-Time"]
    F --> G[Check if slot exists in existingDayTimeSlots]

    G -->|Conflict| H1[Print "Conflict detected"\n Adjust time +10 mins repeatedly until free\n Show adjusted time]
    H1 --> H2[Add new slot to existingDayTimeSlots]
    G -->|No conflict| H2

    H2 --> I[Add final time to doseTimes list]

    I --> J[Return new MedicineSchedule object\n(numberOfDoses, doseTimes, days)]

    J --> K[Check & Notify Dose (Runtime)]
    K --> K1[Get current time (HH:mm) and day (e.g., MONDAY)]

    K1 --> K2[For each scheduled day:\n If matches today's day ➜ check each doseTime]
    K2 --> K3[If doseTime == current time ➜ Print Reminder:\n"It's time to take your medicine: <name> at <doseTime>"]

    K3 --> Z[End]



---
### Developer Profile
Name: Shriya Verma
Role: Java Developer | B.Tech CSE Student | AI + Health and Tech Enthusiast
University: Lovely Professional University
Current Year: 3rd Year (BTech CSE)
Location: Punjab, India
🌟 About Me
I am a passionate Computer Science undergrad currently in my third year at LPU. With a strong foundation in core programming (Java, C++), data structures, and OOP design, I build real-world projects aimed at solving practical healthcare and productivity challenges.
My focus is on building robust, modular software systems that reflect industry-grade thinking — from clean architecture and user-centric workflows to concurrency and performance. I enjoy breaking down complex systems and reimagining them with precision, clarity, and real-world utility.
My long-term vision is to innovate at the intersection of artificial intelligence and healthcare along with tech, and I’m actively working toward building impactful systems that promote well-being and digital accessibility for all.

### Run Instructions
```bash
javac Medicine.java
java Medicine




