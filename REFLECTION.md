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
