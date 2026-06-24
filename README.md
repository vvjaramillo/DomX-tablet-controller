# DomX Tablet Controller

Android application to control the NAO robot remotely from a tablet.

## Features

- **Basic Movements**: Walk, turn, sit, stand
- **Animations**: Execute predefined animations/behaviors
- **Real-time Control**: Connect via Wi-Fi to NAO robot
- **User-friendly Interface**: Simple and intuitive controls

## Requirements

- Android 7.0 or higher
- NAO robot connected to the same Wi-Fi network
- Android Studio (for development)

## Getting Started

### Prerequisites

1. **Android Studio**: [Download here](https://developer.android.com/studio)
2. **NAO Robot**: Ensure it's powered on and connected to Wi-Fi
3. **NAOqi SDK**: Will be added as a dependency via Gradle

### Installation

1. Clone this repository
2. Open with Android Studio
3. Build and run on your Android device or emulator
4. Connect to NAO by entering its IP address

## Project Structure

```
DomX-tablet-controller/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/domx/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── NAOConnection.kt
│   │   │   │   ├── RobotController.kt
│   │   │   │   └── ...
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   └── values/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle
├── gradle/
├── build.gradle
└── settings.gradle
```

## Dependencies

- Kotlin coroutines
- Retrofit (for HTTP requests to NAOqi)
- Gson (JSON parsing)
- Material Design Components

## Usage

1. Launch the app on your tablet
2. Enter the NAO robot's IP address
3. Tap "Connect"
4. Use the control buttons to:
   - Move the robot
   - Execute animations
   - Control robot posture

## Development

### NAOqi API

The app communicates with NAO using the NAOqi REST API:
- Base URL: `http://<robot_ip>:8080/`
- Endpoints: `/motion/`, `/animation/`, `/tts/`, etc.

### Building

```bash
./gradlew build
```

### Running

```bash
./gradlew installDebug
```

## Contributing

Feel free to submit issues and enhancement requests!

## License

MIT License

## Authors

- vvjaramillo

## Support

For questions or issues, please open a GitHub issue.
