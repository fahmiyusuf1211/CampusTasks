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

# Project Reflection — CampusTasks

## What I Learned

Through the CampusTasks project, I learned how to design a Kotlin domain model by separating data representation, validation, object creation, and query operations. I implemented a `Task` data class, priority and status enums, and an `Identifiable` interface to support reusable ID-based lookup.

I also learned the importance of input normalization and validation. Trimming titles, converting blank descriptions to `null`, and removing blank tags help maintain consistent data. The `CreateTaskResult` sealed interface makes successful task creation and validation failures explicit.

## Challenges and Solutions

One challenge was ensuring that invalid data could be tested without preventing the validator from receiving it. I addressed this by keeping validation in `TaskValidator` and `TaskFactory`, allowing unit tests to check invalid inputs directly. I also expanded tests for boundary values, normalization, state transitions, and query results.

## Use of AI Assistance

AI assistance was used to help review the implementation, explain Kotlin concepts, improve unit-test coverage, and prepare project documentation. I reviewed and ran the suggested code in Android Studio to verify that the tests passed.

## Conclusion

This project improved my understanding of Kotlin data classes, interfaces, enums, sealed interfaces, collection operations, and unit testing. It also demonstrated why clear responsibilities and automated tests are important in domain-model development.
