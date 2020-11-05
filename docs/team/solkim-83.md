---
layout: page
title: Kim Sol's Project Portfolio Page
---

## Project: Athena

Athena is a **desktop app for managing contacts and events, optimized for use via a Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added `sort -e` command.
  * What it does: allows the user sort the event entries based on their preferred sorting order, such as the event description and event timing.
  * Justification: This feature improves the product significantly because a sorted order of events will increase the convenience of the users much more than the default arrangement of the entries.

* **New Feature**: Added `find -e` command.
  * What it does: allows the user to find (a) particular event(s) based on the keyword given as the input. 
  * Justification: This feature improves the product significantly because in a situation where the user needs to filter certain events (e.g. meetings), they can easily use the command and view certain events only. 
  
* **New Feature**: Added `list -e` command.
  * What it does: allows the user to view all registered event entries. 
  * Justification: This feature improves the product significantly because in a situation where the user previously searched for a particular event and wish to view the entire list, the command allows the user to do so. 
  
* **New Feature**: Added `clear -e` command.
  * What it does: allows the user to delete all registered event entries. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=solkim-83)

* **Enhancements to existing features**:
  * Updated `add -e` to include more time input formats from the users (Pull request [\#237]())
  * Wrote additional tests for all event features to increase coverage by 4.49% (Pull request [\#156]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort -e`, `find -e`, `list -e` and `clear -e`. (Pull request [\#72]())
    * Amended errors (e.g. grammatical) to existing documentation of all existing features (Pull request [\#74]())
  * Developer Guide:
    * Added implementation details of the `sort -e`, `find -e`, `list -e` and `clear -e` feature. (Pull request [\#151](), [\#32]()))

* **Community**:
  * Reported bugs and suggestions for other teams in the class ([Link](https://github.com/solkim-83/ped/issues))