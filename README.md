# CampusTasks — Kotlin Domain Model

## Overview

CampusTasks is a Kotlin domain-model project that represents and manages academic tasks. It demonstrates data modeling, validation, normalization, immutable state transitions, and collection queries.

## Domain Model

The `Task` data class contains an ID, title, optional description, priority, status, estimated duration, and tags. `TaskPriority` defines `LOW`, `MEDIUM`, and `HIGH`, while `TaskStatus` defines `TODO`, `IN_PROGRESS`, and `DONE`. The `Identifiable` interface provides a common ID property.

## Validation and Factory

`TaskValidator` checks ID and title lengths, estimated minutes, and blank tags. `TaskFactory.create()` normalizes input and returns `CreateTaskResult.Success` or `CreateTaskResult.Invalid`. Empty descriptions become `null`, and blank tags are removed.

## Queries and State Changes

`Task` provides `markInProgress()` and `markDone()`, which return updated copies without modifying the original object. `TaskQueries` supports open-task filtering, priority filtering, keyword searching, grouping by status, and generic ID lookup.

## Testing

Unit tests cover valid and invalid inputs, normalization, boundary values, state transitions, and query behavior.

Run the tests from the project root:

```powershell
.\gradlew.bat testDebugUnitTest
```

A successful run ends with `BUILD SUCCESSFUL`.
