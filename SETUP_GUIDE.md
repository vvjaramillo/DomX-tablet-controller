# DomX Tablet Controller - Setup Guide

This guide will help you set up and run the NAO robot controller app from scratch.

## Prerequisites

### 1. Android Studio Installation
- **Download**: https://developer.android.com/studio
- **Installation**: Follow the official guide for your OS (Windows, Mac, Linux)
- **Verify**: When you run Android Studio, it should auto-install the Android SDK

### 2. Java Development Kit (JDK)
- Android Studio includes JDK automatically
- If installing separately: JDK 11 or higher

### 3. NAO Robot Prerequisites
- NAO robot powered on and connected to Wi-Fi
- Note the robot's **IP address** (it will announce it when you press its chest button)
- Ensure your tablet/emulator is on the **same Wi-Fi network**

## Step-by-Step Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/vvjaramillo/DomX-tablet-controller.git
cd DomX-tablet-controller
```

### Step 2: Open in Android Studio

1. Open Android Studio
2. Click **File > Open**
3. Navigate to the cloned repository folder
4. Click **Open**
5. Wait for Gradle to sync (this may take 2-5 minutes on first run)

### Step 3: Configure Emulator or Connect Device

#### Option A: Using Android Emulator (Recommended for Testing)

1. In Android Studio, go to **Tools > Device Manager**
2. Click **Create Device**
3. Select **Tablet** device (e.g., "Pixel Tablet")
4. Choose **API Level 33** (or higher)
5. Click **Create**
6. Start the emulator by clicking the play button

**Important**: To test NAO connection from emulator:
- Ensure NAO robot is on the same network as your dev machine
- Use NAO's actual IP address (not localhost)
- Some network configurations may prevent emulator from reaching the robot

#### Option B: Using Physical Android Tablet

1. Enable **Developer Mode** on your tablet:
   - Go to Settings > About > Tap "Build number" 7 times
2. Enable **USB Debugging**:
   - Settings > Developer Options > USB Debugging
3. Connect tablet to computer via USB
4. Click "Trust" when prompted on the tablet
5. Android Studio should detect your device

### Step 4: Build and Run

1. In Android Studio, select your device from the top toolbar
2. Click the green **Run button** (or press Shift+F10)
3. Wait for the app to build and deploy (2-3 minutes first time)

### Step 5: Test the App

1. **Enter NAO IP Address**:
   - In the app, enter your robot's IP address (e.g., `192.168.1.100`)
   - Click **Connect**

2. **Verify Connection**:
   - Status should show "Connected to [IP]" in green
   - You should see a success toast message

3. **Test Movement**:
   - Click **Forward** - NAO should move forward
   - Click **Backward** - NAO should move backward
   - Click **Stop** - NAO should stop

4. **Test Postures**:
   - Click **Stand** - NAO stands up
   - Click **Sit** - NAO sits down

5. **Test Animations**:
   - Click **Wave Hand** - NAO waves
   - Click **Dance** - NAO dances

## Troubleshooting

### Connection Issues

**Problem**: "Connection failed" when clicking Connect
- **Solution 1**: Verify NAO robot is powered on
- **Solution 2**: Check that both devices are on the same Wi-Fi network
- **Solution 3**: Confirm NAO IP address is correct
- **Solution 4**: Try pinging the robot: `ping [robot_ip]`

### Build Issues

**Problem**: "Gradle sync failed"
- **Solution**: 
  1. Go to **File > Settings > System Settings > Android SDK**
  2. Ensure "Android SDK Build-Tools" is installed
  3. Click **Apply** and **OK**
  4. Go to **File > Sync Now**

**Problem**: "Failed to find target with hash string android-33"
- **Solution**:
  1. Go to **Tools > SDK Manager**
  2. Install API Level 33 (or adjust `compileSdk` in `build.gradle` to your installed API level)

### Runtime Issues

**Problem**: App crashes on launch
- **Solution**:
  1. Check logcat: **View > Tool Windows > Logcat**
  2. Look for red error messages
  3. Take a screenshot and share in GitHub issues

**Problem**: Buttons don't respond
- **Solution**: Ensure you've clicked "Connect" first and status is green

## Project Structure Overview

```
DomX-tablet-controller/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/domx/
│   │   │   │   ├── MainActivity.kt          # Main UI activity
│   │   │   │   └── robot/
│   │   │   │       ├── RobotController.kt   # High-level robot commands
│   │   │   │       └── NAOApiService.kt     # Low-level Retrofit API
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml    # UI layout
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── drawable/
│   │   │   └── AndroidManifest.xml          # App configuration
│   │   └── test/
│   ├── build.gradle                         # App dependencies
│   └── proguard-rules.pro
├── build.gradle                             # Root build config
├── settings.gradle                          # Gradle settings
└── README.md
```

## Key Technologies Used

- **Kotlin**: Modern Android programming language
- **Retrofit 2**: HTTP client for NAO REST API
- **Coroutines**: Asynchronous programming
- **Material Design**: UI components
- **Gradle**: Build system

## Next Steps

1. **Customize Animations**: Add more animations to `RobotController.kt`
2. **Add Video Feed**: Integrate NAO's camera stream
3. **Add Voice Control**: Use Android speech recognition
4. **Create Custom Behaviors**: Program complex robot sequences
5. **Improve UI**: Add joystick controls, real-time status display

## Resources

- [Android Developer Guide](https://developer.android.com/guide)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [NAO Developer Documentation](https://developer.softbankrobotics.com/nao6/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Support

If you encounter any issues:
1. Check this guide's troubleshooting section
2. Open a GitHub issue with:
   - Error message/screenshot
   - What you were trying to do
   - Your device/emulator details
3. Check Android Studio's logcat for error traces

Good luck! 🚀
