zhengming
==========

### Running on the command line

1. In the project directory you should be able to run the Robolectric tests:
    ```bash
    ./gradlew test
    ```

1. You can also run the Espresso tests:
    ```bash
    ./gradlew connectedAndroidTest
    ```
    Note: Make sure to start an Emulator or connect a device first so the test has something to connect to.

1. Finally you can build a debug `.apk` of the project for installation on phones:
    ```bash
    ./gradlew assemble
    ```
    This will output the file to `build/outputs/apk/*-debug.apk`