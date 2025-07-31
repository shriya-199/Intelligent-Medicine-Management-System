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
Welcome to the Medicine Management System
You can manage your medicines, view schedules, and track history.
==========================================================
Please follow the prompts to manage your medicines.
==========================================================
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 1
Enter the number of medicines to add: 4
Please enter the names of the medicines:
Medicine 1: Paracetamol
Medicine paracetamol added successfully.
Enter schedule details for paracetamol:
Enter number of doses per day: 2
Enter number of days to take the medicine: 2
Enter day 1 (e.g., Monday): Monday
Enter day 2 (e.g., Monday): Wednesday
Enter time for dose 1 (HH:mm): 08:00
Enter time for dose 2 (HH:mm): 19:00
Schedule for paracetamol added successfully.
Medicine 2: sprin
Medicine sprin added successfully.
Enter schedule details for sprin:
Enter number of doses per day: 1Exception in thread "main" java.util.NoSuchElementException: No line found
        at java.base/java.util.Scanner.nextLine(Scanner.java:1677)
        at MedicineSchedule.createScheduleFromInput(MedicineSchedule.java:70)
        at Medicine.addMedicines(Medicine.java:197)
PS C:\Users\hp\OneDrive\Desktop\Projects\DailyDose>  c:; cd 'c:\Users\hp\OneDrive\Desktop\Projects\DailyDose'; & 'C:\Program Files\Java\jdk-23\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'C:\Users\hp\AppData\Roaming\Code\User\workspaceStorage\5254cf5660f4f837f313b347c5ebff6d\redhat.java\jdt_ws\DailyDose_668154b7\bin' 'Medicine' 
Welcome to the Medicine Management System
You can manage your medicines, view schedules, and track history.
==========================================================
Please follow the prompts to manage your medicines.
==========================================================
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 1
Enter the number of medicines to add: 4
Please enter the names of the medicines:
Medicine 1: Paracetamol
Medicine paracetamol added successfully.
Enter schedule details for paracetamol:
Enter number of doses per day: 1
Enter number of days to take the medicine: 2
Enter day 1 (e.g., Monday): Monday
Enter day 2 (e.g., Monday): Tuesday
Enter time for dose 1 (HH:mm): 08:00
Schedule for paracetamol added successfully.
Medicine 2: Asprin
Medicine asprin added successfully.
Enter schedule details for asprin:
Enter number of doses per day: 2
Enter number of days to take the medicine: 1
Enter day 1 (e.g., Monday): Monday
Enter time for dose 1 (HH:mm): 08:00
Conflict on Monday at 08:00
Adjusted to: 08:10
Enter time for dose 2 (HH:mm): 16:00
Schedule for asprin added successfully.
Medicine 3: Vomin
Medicine vomin added successfully.
Enter schedule details for vomin:
Enter number of doses per day: 2
Enter number of days to take the medicine: 1
Enter day 1 (e.g., Monday): Sunday
Enter time for dose 1 (HH:mm): 12:00
Enter time for dose 2 (HH:mm): 18:00
Schedule for vomin added successfully.
Medicine 4: Ibuprofen
Medicine ibuprofen added successfully.
Enter schedule details for ibuprofen:
Enter number of doses per day: 1
Enter number of days to take the medicine: 3
Enter day 1 (e.g., Monday): Monday
Enter day 2 (e.g., Monday): Tuesday
Enter day 3 (e.g., Monday): Wednesday
Enter time for dose 1 (HH:mm): 09:00   
Schedule for ibuprofen added successfully.
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 2
List of Medicines:
- paracetamol
- asprin
- vomin
- ibuprofen
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 3
What do you want to do?
Choose 1 for 'Remove and Add' or 2 for 'Edit while keeping others':
Your choice: 1
You chose to remove a medicine and add a new one.
Enter the name of the medicine to remove: paracetamol
Enter the new name for the medicine: Acetaminophen
Medicine updated successfully from paracetamol to acetaminophen.
Enter schedule details for acetaminophen:
Enter number of doses per day: 1
Enter number of days to take the medicine: 2
Enter day 1 (e.g., Monday): Friday
Enter day 2 (e.g., Monday): Sunday
Enter time for dose 1 (HH:mm): 15:00
Schedule for acetaminophen added successfully.
Do you want to update another medicine? (yes/no): yes
You chose to remove a medicine and add a new one.
Enter the name of the medicine to remove: Asprin
Enter the new name for the medicine: Lasmiditan
Medicine updated successfully from asprin to lasmiditan.
Enter schedule details for lasmiditan:
Enter number of doses per day: 3
Enter number of days to take the medicine: 2
Enter day 1 (e.g., Monday): Monday
Enter day 2 (e.g., Monday): Tuesday
Enter time for dose 1 (HH:mm): Friday
Invalid time format. Use HH:mm.
Enter time for dose 1 (HH:mm): 09:30
Enter time for dose 2 (HH:mm): 14:37
Enter time for dose 3 (HH:mm): 22:45
Schedule for lasmiditan added successfully.
Do you want to update another medicine? (yes/no): no
Exiting update mode.
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 2
List of Medicines:
- vomin
- ibuprofen
- acetaminophen
- lasmiditan
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 3
What do you want to do?
Choose 1 for 'Remove and Add' or 2 for 'Edit while keeping others':
Your choice: 2
You chose to add a new medicine while keeping others.
Enter the new medicine name: Dihydroergotamine
Medicine dihydroergotamine added successfully.
Enter schedule details for dihydroergotamine:
Enter number of doses per day: 1
Enter number of days to take the medicine: 1
Enter day 1 (e.g., Monday): Saturday
Enter time for dose 1 (HH:mm): 07:50
Schedule for dihydroergotamine added successfully.
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 2
List of Medicines:
- vomin
- ibuprofen
- acetaminophen
- lasmiditan
- dihydroergotamine
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 4
Enter the name of the medicine to delete: Dolo
Medicine dolo not found.
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 2
List of Medicines:
- vomin
- ibuprofen
- acetaminophen
- lasmiditan
- dihydroergotamine
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 5
Medicine History:
- paracetamol [Added on 2025-07-31 22:36:27]
- asprin [Added on 2025-07-31 22:36:54]
- vomin [Added on 2025-07-31 22:37:40]
- ibuprofen [Added on 2025-07-31 22:39:34]
- paracetamol [Deleted on 2025-07-31 22:40:57]
- acetaminophen [Added on 2025-07-31 22:41:37]
- paracetamol -> acetaminophen [Updated on 2025-07-31 22:41:37]
- asprin [Deleted on 2025-07-31 22:42:29]
- lasmiditan [Added on 2025-07-31 22:42:52]
- asprin -> lasmiditan [Updated on 2025-07-31 22:42:52]
- dihydroergotamine [Added on 2025-07-31 22:44:32]
Current Medicines:
- vomin
- ibuprofen
- acetaminophen
- lasmiditan
- dihydroergotamine
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 6
Enter the name of the medicine to update schedule: vomin
Enter new schedule details for vomin:
Enter number of doses per day: 1
Enter number of days to take the medicine: 1
Enter day 1 (e.g., Monday): Monday
Enter time for dose 1 (HH:mm): 10:00
Schedule for vomin updated successfully.
Schedule for vomin updated successfully.
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 7
Enter the name of the medicine to view schedule: lasmiditan
Schedule for lasmiditan:
Schedule {Doses per day = 3, Existing Slots = [Monday-08:00, Tuesday-08:00, Monday-08:10, Monday-16:00, Sunday-12:00, Sunday-18:00, Monday-09:00, Tuesday-09:00, Wednesday-09:00, Friday-15:00, Sunday-15:00, Monday-09:30, Tuesday-09:30, Monday-14:37, Tuesday-14:37, Monday-22:45, Tuesday-22:45, Saturday-07:50, Monday-10:00]}
Enter 1 to add medicines
Enter 2 to view medicines
Enter 3 to update medicines
Enter 4 to delete medicines
Enter 5 to view medicine history
Enter 6 to update schedule for an existing medicine
Enter 7 to view the schedule of a medicine
Enter 8 to exit the system
Your choice: 8
Exiting the system. Goodbye!
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




