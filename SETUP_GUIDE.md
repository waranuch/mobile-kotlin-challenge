# Development Environment Setup Guide

## Java Installation and JAVA_HOME Configuration

### 1. Install Java Development Kit (JDK)

For Android development with Kotlin, you need **Java 17 or later**. Here are the recommended installation methods:

#### Option A: Using Homebrew (Recommended)

```bash
# Install Homebrew if you don't have it
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install OpenJDK 17
brew install openjdk@17

# Create symlink for system Java wrappers
sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
```

#### Option B: Download from Oracle/OpenJDK

1. Visit [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
2. Download JDK 17 or later for macOS
3. Install the downloaded `.dmg` file
4. Follow the installation wizard

### 2. Set JAVA_HOME Environment Variable

After installing Java, you need to set the JAVA_HOME environment variable:

#### Check Available Java Versions
```bash
/usr/libexec/java_home -V
```

#### Set JAVA_HOME in your shell profile

**For zsh (default on macOS Catalina+):**
```bash
# Open your .zshrc file
nano ~/.zshrc

# Add these lines to the end of the file:
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

# Save and reload your shell configuration
source ~/.zshrc
```

**For bash:**
```bash
# Open your .bash_profile file
nano ~/.bash_profile

# Add these lines:
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

# Save and reload
source ~/.bash_profile
```

### 3. Verify Java Installation

After setting up JAVA_HOME, verify everything is working:

```bash
# Check Java version
java -version

# Check JAVA_HOME
echo $JAVA_HOME

# Verify javac (Java compiler)
javac -version
```

You should see output similar to:
```
java version "17.0.9" 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 17.0.9+11-LTS-201)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.9+11-LTS-201, mixed mode, sharing)
```

## Android Studio Setup

### 1. Download and Install Android Studio

1. Visit [Android Studio Download Page](https://developer.android.com/studio)
2. Download Android Studio for macOS
3. Install the downloaded `.dmg` file
4. Follow the setup wizard

### 2. Configure Android SDK

During first launch, Android Studio will:
1. Download and install Android SDK
2. Install Android SDK Build-Tools
3. Install Android Emulator
4. Set up AVD (Android Virtual Device)

### 3. Verify Android Studio Setup

1. Open Android Studio
2. Create a new project or open existing one
3. Check that Gradle sync works without errors
4. Verify that you can run the app on an emulator

## Quick Setup Commands

Run these commands in sequence to set up your environment:

```bash
# 1. Install Java via Homebrew
brew install openjdk@17

# 2. Create system symlink
sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk

# 3. Add to shell profile (choose your shell)
# For zsh:
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc

# For bash:
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 17)' >> ~/.bash_profile
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile
source ~/.bash_profile

# 4. Verify installation
java -version
echo $JAVA_HOME
```

## Troubleshooting

### Common Issues and Solutions

1. **"Unable to locate a Java Runtime" Error**
   - Make sure Java is properly installed
   - Verify JAVA_HOME is set correctly
   - Restart your terminal after setting environment variables

2. **Gradle Sync Issues**
   - Ensure you're using a compatible Java version (17+)
   - Clear Gradle cache: `./gradlew clean`
   - Invalidate caches in Android Studio: File → Invalidate Caches and Restart

3. **Build Errors**
   - Check that Android SDK is properly installed
   - Verify build.gradle files have correct SDK versions
   - Ensure all required dependencies are downloaded

### Environment Verification Script

Run this script to verify your setup:

```bash
#!/bin/bash
echo "=== Development Environment Check ==="
echo

echo "1. Java Version:"
java -version
echo

echo "2. JAVA_HOME:"
echo $JAVA_HOME
echo

echo "3. Java Compiler:"
javac -version
echo

echo "4. Android Studio (if installed):"
if command -v studio &> /dev/null; then
    echo "Android Studio command found"
else
    echo "Android Studio command not found (this is normal if installed via GUI)"
fi
echo

echo "5. Gradle (if available):"
if command -v gradle &> /dev/null; then
    gradle --version
else
    echo "Gradle not found in PATH (will use project wrapper)"
fi

echo "=== Setup Complete ==="
```

## Next Steps

Once you have Java and Android Studio set up:

1. Open the mobile-kotlin-challenge project in Android Studio
2. Let Android Studio download any missing SDK components
3. Sync the project with Gradle files
4. Run the app on an emulator or device

If you encounter any issues, refer to the troubleshooting section above or the main README.md for additional guidance.
