Hello TymeX Recruiters,

First of all, I sincerely apologize for sending the results to you late. Due to some repairs needed at my home, I was unable to send the results to you earlier.

Here is the description of the Currency Conversion App:

**TymeX Online Test App**
**App Structure**
The app appears to be an Android application with the following key components:
- Main Activity: Contains the core UI elements including:
    + Currency spinners for "from" and "to" conversion
    + Amount input field
    + Convert button
    + Results display
    + Swap button for quick currency exchange
- Resources:
    + Custom drawables for buttons and backgrounds
    + Defined styles and themes for consistent UI
    + String resources for localization support

**Build & Run Instructions:**
1. Prerequisites:
    + Android Studio
    + Android SDK with minimum required version
    + Gradle build system
2. Build Steps
    # Clone the repository
   git clone [repository-url]

   # Open in Android Studio
   # Wait for Gradle sync to complete

   # Build the app
   ./gradlew assembleDebug

3. Run the App
- Connect an Android device or use an emulator
- Click "Run" in Android Studio or execute:
# ./gradlew installDebug

**Additional Notes**
1. UI Components: The app uses several custom-styled components:
    - startLine: 903
    - endLine: 904

These include custom backgrounds for buttons and edit texts.

2. Theme Customization: The app implements a custom theme:
    - startLine: 1700
    - endLine: 1700

Using Theme_TymeX_OnlineTest for consistent styling.

3. Resource Management: Extensive use of Android resource system for:
    + Layouts
    + Drawables
    + Styles
    + Strings
    + Colors

**Video Demonstration**
https://www.youtube.com/watch?v=X-7oSBTicjo

**Additionally, in this repository, I have also included the two parts of Challenge 2, which I solved using C#.**