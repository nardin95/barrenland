# Barren Land

Simple java project for calculating the sizes of fertile lands in a segmented barren field

## Installation

```
./gradlew clean build
```

## Running the application
After building:
```
java -jar build/libs/barrenland-1.0-SNAPSHOT.jar
```
ctrl+d to exit

## Differing Implementations
* There are two different implementations, one in master branch, and another in linked-list branch. The master branch uses a 2D array to solve the problem, but after I completed it I was bothered by the fact that the loops iterating across the grid had to needlessly check positions that the inner loop had already set.
* The second implementation was a result of me trying to back the 2D array with a doubly linked list so that we wouldn't have to loop over unnecessary cells.
* The performance tests can be run by checking out each branch and running their tests separately, but I've included the output below as well.
* ~~Unfortunately my doubly linked list implementation performs worse overall likely due to all the extra heap allocations it requires, but the actual performance of the solver is significantly faster (more than an entire order of magnitude)~~ A bug I discovered late in testing means that my initial performance test data was inaccurate.
* Unfortunately the doubly linked solver is around 70% slower. I still feel like there might be a way to make it faster, but I was unable to find a way to do so.
```
Time to run 100 iterations with doubly linked solver (including time to setup): 1696 ms.

Time to run 100 iterations with array only solver (including time to setup): 997 ms.
```
