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
┌───────────────────────────────────────────┐
│     Start: Medicine Management System     │
└───────────────────────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────────┐
│ Display welcome message & instructions      │
│ Start reminder thread (loop every 60 secs) │
└─────────────────────────────────────────────┘
                 │
                 ▼
      ┌────────────────────────┐
      │    Show Main Menu:     │
      └────────────────────────┘
                 │
                 ▼
┌────────────────────────────────────────────┐
│ User Input (1–8):                          │
│   1: Add medicine                          │
│   2: View medicines                        │
│   3: Update medicine                       │
│   4: Delete medicine                       │
│   5: View history                          │
│   6: Update schedule                       │
│   7: View schedule                         │
│   8: Exit                                  │
└────────────────────────────────────────────┘
                 │
   ┌─────────────┼─────────────────────────────────────────────────────┐
   │             │             │            │             │            │
   ▼             ▼             ▼            ▼             ▼            ▼
[Add]        [View]       [Update]     [Delete]      [View Hist]   [Update Sched]
 Medicine   Medicines     Medicine     Medicine      All Events     for Medicine
                 │
                 ▼
           [View Schedule]
                 │
                 ▼
             [Exit System]
                 │
                 ▼
       ┌─────────────────────┐
       │   Exit Program      │
       └─────────────────────┘

AddMedicine:
┌──────────────────────────────────────────────┐
│ Start addMedicines(Scanner sc)              │
└──────────────────────────────────────────────┘
                    │
                    ▼
   ┌────────────────────────────────────────┐
   │ Prompt: "Enter number of medicines"   │
   └────────────────────────────────────────┘
                    │
                    ▼
      ┌────────────────────────────┐
      │ Try reading integer input  │
      └────────────────────────────┘
                    │
        ┌───────────┴───────────┐
        ▼                       ▼
┌───────────────┐     ┌────────────────────────────┐
│ Input valid?  │ No  │ Print "Invalid input"      │
└───────────────┘     │ Clear scanner buffer       │
        │             │ Return from method         │
        ▼             └────────────────────────────┘
  Yes   │
        ▼
┌────────────────────────────────────────────┐
│ Check if numberOfMedicines > 0            │
└────────────────────────────────────────────┘
        │
    ┌───▼────────────┐
    │ No: Print error│
    │ & return       │
    └────────────────┘
        │
    Yes ▼
┌────────────────────────────────────────────┐
│ Loop i = 1 to numberOfMedicines            │
│ Prompt for medicine name                   │
└────────────────────────────────────────────┘
                    │
                    ▼
     ┌────────────────────────────────────┐
     │ Validate name is not null/empty    │
     └────────────────────────────────────┘
                    │
                    ▼
     ┌────────────────────────────────────┐
     │ Check if medicine already exists   │
     └────────────────────────────────────┘
                    │
         ┌──────────┼─────────────┐
         │ Yes                     │ No
         ▼                         ▼
┌──────────────────────────────┐ ┌──────────────────────────────┐
│ Prompt: Medicine exists      │ │ Add to medicines list        │
│ Ask: "Skip this medicine?"   │ │ Add entry to history         │
└──────────────────────────────┘ │ Prompt for schedule           │
         │                        │ Store schedule in Map        │
         ▼                        │ Print success messages       │
┌──────────────────────────────┐ └──────────────────────────────┘
│ If YES → skip to next        │
│ If NO → ask for new name     │
│ Loop until unique name       │
└──────────────────────────────┘
                    │
                    ▼
      (Repeat loop until all medicines processed)
                    │
                    ▼
        ┌──────────────────────┐
        │ End addMedicines()   │
        └──────────────────────┘


updateMedicine:
┌────────────────────────────────────────────┐
│ Start updateMedicines(Scanner sc)         │
└────────────────────────────────────────────┘
                    │
                    ▼
    ┌────────────────────────────────────┐
    │ Prompt: Choose 1 (Replace) or 2    │
    │ (Add new while keeping old)        │
    └────────────────────────────────────┘
                    │
        ┌───────────┼─────────────┐
        ▼                         ▼
 ┌─────────────┐         ┌────────────────────────────┐
 │ Choice = 1  │         │ Choice = 2                 │
 │ Replace med │         │ Add new while keeping      │
 └─────────────┘         └────────────────────────────┘
        │                         │
        ▼                         ▼
┌──────────────────────────┐  ┌────────────────────────────┐
│ Loop: continueUpdating?  │  │ Prompt for new medicine    │
└──────────────────────────┘  │ Validate name (not empty,  │
        │                    │ not duplicate)              │
        ▼                    └────────────────────────────┘
┌─────────────────────────────┐        │
│ Prompt: medicine to remove  │        ▼
│ Check if it exists          │  ┌───────────────────────────────┐
└─────────────────────────────┘  │ Add new medicine to list       │
        │                         │ Add to history (added)         │
        ▼                         │ Prompt for schedule input      │
┌───────────────────────────────┐│ Save to schedule map            │
│ If not found → print msg &    ││ Print success messages          │
│ break                         │└────────────────────────────────┘
└───────────────────────────────┘
        │
        ▼
┌────────────────────────────────────┐
│ Remove old medicine from list      │
│ Add to history (deleted)           │
└────────────────────────────────────┘
        │
        ▼
┌────────────────────────────────────┐
│ Prompt for new medicine name       │
│ Validate:                          │
│ - not empty                        │
│ - not same as old                 │
│ - not duplicate                   │
└────────────────────────────────────┘
        │
        ▼
┌────────────────────────────────────┐
│ Add new medicine to list           │
│ Add to history (added, updated)    │
└────────────────────────────────────┘
        │
        ▼
┌────────────────────────────────────┐
│ Prompt for schedule for new med    │
│ Save to schedule map               │
│ Print success messages             │
└────────────────────────────────────┘
        │
        ▼
┌────────────────────────────────────┐
│ Ask: Update another? (yes/no)      │
│ If no → Exit update mode           │
└────────────────────────────────────┘
                    │
                    ▼
          ┌──────────────────────┐
          │ End updateMedicines  │
          └──────────────────────┘


updateScheduleForExistingMedicine

┌────────────────────────────────────────────┐
│ Start updateScheduleForExistingMedicine    │
└────────────────────────────────────────────┘
                    │
                    ▼
┌────────────────────────────────────────────┐
│ Prompt: Enter medicine name                │
└────────────────────────────────────────────┘
                    │
        ┌───────────┴────────────┐
        ▼                        ▼
┌─────────────────────────┐  ┌────────────────────────────┐
│ Medicine found?         │  │ Print: "Medicine not found"│
│ Yes                     │  │ Return                     │
└─────────────────────────┘  └────────────────────────────┘
        │
        ▼
┌────────────────────────────────────────────┐
│ Prompt for new schedule                    │
│ Use createScheduleFromInput()             │
│ Update schedule in medicineSchedules Map   │
└────────────────────────────────────────────┘
        │
        ▼
┌────────────────────────────────────────────┐
│ Add update entry in medicineHistories      │
│ Print success message                      │
└────────────────────────────────────────────┘
        │
        ▼
        ┌──────────────────────┐
        │ End Function         │
        └──────────────────────┘

deleteMedicine

┌───────────────────────────────────────┐
│ Start deleteMedicines(Scanner sc)     │
└───────────────────────────────────────┘
                    │
                    ▼
┌────────────────────────────────────────────┐
│ Prompt: Enter medicine name to delete      │
└────────────────────────────────────────────┘
                    │
        ┌───────────┴────────────┐
        ▼                        ▼
┌────────────────────────────┐  ┌──────────────────────────────┐
│ Medicine exists in list?   │  │ Print "Medicine not found"   │
│ Yes                        │  │ Return                       │
└────────────────────────────┘  └──────────────────────────────┘
        │
        ▼
┌────────────────────────────────────────────┐
│ Remove medicine from list                  │
│ Add deletion to medicineHistories          │
│ Print success message                      │
└────────────────────────────────────────────┘
        │
        ▼
        ┌──────────────────────┐
        │ End Function         │
        └──────────────────────┘

viewMedicineHistory

┌──────────────────────────────────────┐
│ Start viewMedicineHistory()          │
└──────────────────────────────────────┘
                    │
                    ▼
┌────────────────────────────────────────────┐
│ Check if medicineHistories is empty        │
└────────────────────────────────────────────┘
        ┌────────────┴────────────┐
        ▼                         ▼
┌───────────────────────────┐ ┌────────────────────────────────┐
│ Print: "No history found" │ │ Loop through history entries:   │
│ Return                    │ │  - Print Added / Updated /      │
└───────────────────────────┘ │    Deleted with timestamp       │
                              └────────────────────────────────┘
                                          │
                                          ▼
                         ┌────────────────────────────────┐
                         │ Print all current medicines    │
                         │ If empty, show "No medicines"  │
                         └────────────────────────────────┘
                                          │
                                          ▼
                             ┌──────────────────────┐
                             │ End Function         │
                             └──────────────────────┘

viewMedicineSchedule

┌──────────────────────────────────────────┐
│ Start viewMedicineSchedule()             │
└──────────────────────────────────────────┘
                    │
                    ▼
  ┌────────────────────────────────────┐
  │ Prompt: "Enter the name of the     │
  │ medicine to view schedule:"        │
  └────────────────────────────────────┘
                    │
                    ▼
  ┌────────────────────────────────────┐
  │ Read input from user               │
  │ Convert to lowercase & trim input │
  └────────────────────────────────────┘
                    │
                    ▼
  ┌────────────────────────────────────┐
  │ Look up medicineSchedules map      │
  │ using lowercase medicine name      │
  └────────────────────────────────────┘
                    │
                    ▼
         ┌──────────┴───────────┐
         ▼                      ▼
  Found Schedule?           Schedule Not Found
         │                      │
         ▼                      ▼
┌────────────────────┐  ┌────────────────────────────────┐
│ Print: "Schedule for │  │ Print: "No schedule found for  │
│ [medicineName]"      │  │ [medicineName]" (in RED)       │
└────────────────────┘  └────────────────────────────────┘
         │                      │
         ▼                      ▼
┌────────────────────┐      ┌────────────┐
│ Print schedule obj  │      │ Return     │
└────────────────────┘      └────────────┘
         │
         ▼
   ┌────────────┐
   │ Return     │
   └────────────┘

MedicineSchedule 

[Start]
   |
   v
[Create Schedule From Input]
   |
   |--> Ask "Enter number of doses per day"
   |         |
   |         v
   |    Validate (must be > 0)
   |
   |--> Ask "Enter number of days"
   |         |
   |         v
   |    Validate (must be > 0)
   |
   |--> For each day:
   |         Ask day name (e.g., Monday)
   |         Add to 'days' list
   |
   |--> For each dose:
             |
             v
        Ask time in HH:mm
             |
             v
        Validate format ✓
             |
             v
        For each entered day:
           |
           v
    Generate slot as "Day-Time"
           |
           v
    Check if slot exists in global existingDayTimeSlots
           |
           v
    If conflict:
       Print "Conflict detected"
       Adjust time +10 mins repeatedly until free
       Show adjusted time
           |
           v
    Add new slot to existingDayTimeSlots
           |
           v
    Add final time to doseTimes list
   |
   v
[Return new MedicineSchedule object with numberOfDoses, doseTimes, days]
   |
   v
[Check & Notify Dose (Runtime)]
   |
   |--> Get current time (HH:mm) and day (e.g., MONDAY)
   |
   |--> For each scheduled day:
              |
              v
         If matches today's day:
            For each doseTime:
               |
               v
          If matches current time:
              --> Print Reminder: "It's time to take your medicine: <name> at <doseTime>"
   |
   v
[End]




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




