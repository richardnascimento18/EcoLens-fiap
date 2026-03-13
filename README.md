# EcoLens

EcoLens is a mobile Android application designed to visualize public air quality data from different locations around the world. The application provides an intuitive interface for exploring environmental indicators through an interactive map and data charts.

The goal of this project is to make environmental data easier to understand and more accessible to users, increasing awareness about air pollution and its potential impacts on human health.

---

# Features

The application includes the following features:

* Interactive global map displaying air quality monitoring stations
* Visualization of environmental indicators such as PM2.5, PM10, O₃, and NO₂
* Detailed sensor information for each monitoring station
* Line charts displaying historical air quality measurements
* Support for multiple languages (Portuguese and English)
* Informational content explaining the meaning of each pollution indicator

Users can navigate the map, select monitoring locations, and view real environmental data provided by public air quality monitoring networks.

---

# Technologies Used

The project was developed using modern Android development tools and libraries.

Main technologies used:

* Kotlin
* Android Studio
* OSMDroid (map integration)
* MPAndroidChart (data visualization)
* OpenAQ API (environmental data source)
* Coroutines for asynchronous API requests

---

# API Used

The application consumes environmental data from the OpenAQ public API.

API endpoint:

[https://api.openaq.org](https://api.openaq.org)

This API provides access to air quality monitoring data from stations around the world, including pollutant measurements and sensor metadata.

---

# Installation

To run this project locally:

1. Clone the repository

```
git clone <repository-url>
```

2. Open the project in Android Studio

3. Sync Gradle dependencies

4. Build and run the project on an Android device or emulator

---

# Important Configuration Warning

⚠️ **Important:** After cloning the repository, the application will **not run immediately**.

This is because the project requires an **OpenAQ API key**, which is not included in the repository for security reasons.

You must configure your own API key in the `local.properties` file.

---

# Setting Up the API Key

1. Obtain your API key from the OpenAQ platform.

2. Open the `local.properties` file located in the root of the project.

3. Add your API key in the following format:

```
OPENAQ_API_KEY=your_api_key_here
```

4. Sync the project again in Android Studio.

After this configuration, the application should run normally.

---

# Project Structure

The project is organized into several main components:

* **screens** – Activities representing the main application screens
* **clients** – API client responsible for communicating with the OpenAQ service
* **components** – Reusable UI components
* **ui** – Navigation helpers and UI utilities

---

# Educational Purpose

This project was developed as part of an academic assignment focused on mobile application development and ESG-related solutions. The objective was to demonstrate the integration of public environmental data with an interactive mobile interface.

---

# License

This project is intended for educational purposes.
