<h1 align="center">Pokemon Explorer</h1>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/osvaldo-esparza"><img alt="Profile" src="https://www.soti.com.mx/github.svg" with= "100" height= "25"/></a> 
</p>

<p align= "center">
    <img src="https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla2.png" alt="Pokemon Explorer" width="250" height="450">
</p>



## Download
Go to the [Releases](https://github.com/osvaldo-esparza/Pokedex/releases) to download the latest APK.


## Description

Pokemon Explorer is a mobile application designed to allow users to explore and discover detailed information about different Pokémon. Users can search for Pokémon by name, explore a complete list of Pokémon, and view details such as statistics, types, abilities, and more.

## Key Features

- Search for Pokémon by name.
- Explore a complete list of Pokémon.
- Detailed details of each Pokémon, including statistics, types, abilities, and more.
- Intuitive and easy-to-use user interface.

## Technologies Used

- **Android**: The application is developed using the Kotlin programming language and the Android SDK.
- **Firebase Authentication**: For user authentication.
- **Retrofit**: To make calls to the PokeAPI and retrieve information about the Pokémon.
- **Glide**: For loading images of the Pokémon.
- **Palette**: To extract dominant colors from Pokémon images and enhance the user interface.
- **ViewModel and LiveData**: For the MVVM (Model-View-ViewModel) architecture that separates UI presentation logic from the user interface.
- **RecyclerView**: To efficiently display the list of Pokémon.

## libreries SKYDOVES

```kotlin
dependencies {
    implementation "com.github.skydoves:progressview:1.1.3"
    implementation "com.github.skydoves:androidribbon:1.0.4"
    implementation("com.github.skydoves:transformationlayout:1.1.3")
}
```

## Architecture
**Pokedex** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

- **Model**: Represents the data and logic of the application. In this case, fetching data from the PokeAPI.
- **View**: The user interface of the application, which displays the data and receives user interaction.
- **ViewModel**: Acts as an intermediary between the View and the Model. It contains presentation logic and communicates with the Model to fetch and update data.

## Screenshots
<div style="display: flex; flex-direction: row;">
<img src="https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla1.png" alt="Screenshot 1" width="250" height="450">
<img src="https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla2.png" alt="Screenshot 2" width="250" height="450">
<img src="https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla3.png" alt="Screenshot 3" width="250" height="450">
<img src="https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla4.png" alt="Screenshot 4" width="250" height="450">
</div>

## Open API

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/osvaldo-esparza/Pokedex/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/osvaldo-esparza)__ on GitHub for my next creations! 🤩

## Installation

To run the application, follow these steps:

1. Clone this repository: `git clone https://github.com/osvaldo-esparza/pokemon-explorer.git`
2. Open the project in Android Studio.
3. Run the application on an emulator or Android device.

## Contributions

Contributions are welcome. If you find any issues or have any suggestions for improving the application, feel free to open an issue or submit a pull request.

# License
```xml
Designed and developed by 2022 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```




