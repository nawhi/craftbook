# CraftBook

A programming exercise for the interview process at [Codurance](https://codurance.com).

## Features
**Posting:** Alice can publish messages to a personal timeline

```
> Alice -> I love the weather today
> Bob -> Damn! We lost!
> Bob -> Good game though.
```

**Reading:** I can view Alice and Bob's timelines

```
> Alice
I love the weather today (5 minutes ago)
> Bob
Good game though. (1 minute ago)
Damn! We lost! (2 minutes ago)
```

**Following:** Charlie can subscribe to Alice's and Bob's timelines, and view an aggregated list of all subscriptions

```
> Charlie -> I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
> Charlie follows Alice
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
Alice - I love the weather today (5 minutes ago)

> Charlie follows Bob
> Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - I love the weather today (5 minutes ago)
```

## Notes
* The application starts with an empty list of users. Non-existing users are created when they post their first message.
* The user details are stored in memory and won't persist through a program restart!

## Compilation
### Command-line compilation (Linux/Mac)
Requires a JDK to be installed.

From the command line:

1. Ensure you're in the root directory of the project
```
cd /path/to/CraftBook
```
2. Make the directory for the binary, if it doesn't already exist: 
```
mkdir -p bin
```
3. Run the Java compiler, defining the directory to put the compiled classes in, the path with the source files and the file with the static main() entry point:
```
javac -d bin -sourcepath src src/craftbook/Application.java
```
4. Run the compiled solution using the directory you just compiled into as the classpath:
```
java -cp bin craftbook.Application
```

### Compilation in Eclipse
Tested in Eclipse Neon.
Use the 'Import Projects from Git' wizard which can be found under File > Import... > Git > Projects from Git.
Example steps through the wizard:
1. Select "Clone URI".
2. Type in the HTTPS address for the repository (check above).
3. Choose an appropriate branch (probably master) in 'Select branches to clone'.
4. Keep clicking through the wizard, and Eclipse should do the rest.

You should then be able to run the application, or the tests if you have JUnit installed, from within Eclipse.
