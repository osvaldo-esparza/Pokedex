# Pokemon Explorer

![Pokemon Explorer](https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla2.png)

## Descripción

Pokemon Explorer es una aplicación móvil diseñada para permitir a los usuarios explorar y descubrir información detallada sobre diferentes Pokémon. Los usuarios pueden buscar Pokémon por nombre, explorar una lista completa de Pokémon y ver detalles como estadísticas, tipos, habilidades y más.

## Características principales

- Búsqueda de Pokémon por nombre.
- Exploración de una lista completa de Pokémon.
- Detalles detallados de cada Pokémon, incluyendo estadísticas, tipos, habilidades y más.
- Interfaz de usuario intuitiva y fácil de usar.

## Tecnologías utilizadas

- **Android**: La aplicación está desarrollada utilizando el lenguaje de programación Kotlin y el SDK de Android.
- **Firebase Authentication**: Para la autenticación de usuarios.
- **Retrofit**: Para realizar llamadas a la PokeAPI y obtener información sobre los Pokémon.
- **Glide**: Para la carga de imágenes de los Pokémon.
- **Palette**: Para extraer colores dominantes de las imágenes de los Pokémon y mejorar la interfaz de usuario.
- **ViewModel y LiveData**: Para la arquitectura MVVM (Model-View-ViewModel) que separa la lógica de presentación de la interfaz de usuario.
- **RecyclerView**: Para mostrar la lista de Pokémon de manera eficiente.

##Liberias de terceros SKYDOVES

```kotlin
dependencies {
    implementation "com.github.skydoves:progressview:1.1.3"
    implementation "com.github.skydoves:androidribbon:1.0.4"
    implementation("com.github.skydoves:transformationlayout:1.1.3")
}
```



## Arquitectura

La aplicación sigue una arquitectura MVVM (Model-View-ViewModel), donde:

- **Model**: Representa los datos y la lógica de la aplicación. En este caso, la obtención de datos de la PokeAPI.
- **View**: La interfaz de usuario de la aplicación, que muestra los datos y recibe la interacción del usuario.
- **ViewModel**: Actúa como intermediario entre la Vista y el Modelo. Contiene la lógica de presentación y se comunica con el Modelo para obtener y actualizar los datos.

## Capturas de pantalla

![Screenshot 1](https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla1.png)
![Screenshot 2](https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla2.png)
![Screenshot 3](https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla3.png)
![Screenshot_4](https://github.com/oseo27jul/Pokedex/blob/main/blob/pantalla4.png)

## Instalación

Para ejecutar la aplicación, sigue estos pasos:

1. Clona este repositorio: `git clone https://github.com/tu_usuario/pokemon-explorer.git`
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un emulador o dispositivo Android.

## Contribuciones

Las contribuciones son bienvenidas. Si encuentras algún problema o tienes alguna sugerencia para mejorar la aplicación, no dudes en abrir un problema o enviar una solicitud de extracción.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
