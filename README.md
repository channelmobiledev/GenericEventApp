# 📱 Generic Event App

A generic application for events written in Java and Android.

![image](https://drive.google.com/uc?export=view&id=1ARf46QQieeOE8amDzAXG0FyJDJYLEFDL)

## Features

- Shows activities, separated by what's going to happen, what's currently happening and what did happen.
- Shows event map
- Show event general information

## Instalation

### App

This project was updated to the latest Android SDK version, so it _should_ run on any recent Android Studio environments.

- Install and configure Android Studio to run the project
- Clone / fork the project
- Modify the `SERVER_URL` constant in `ServerConnection.java` to your server's address.

### Backend

This project relies on a Node.js backend to provide the data to the app.

- Install and configure node
- Download the [Backend project](https://github.com/channelmobiledev/GenericEventBackend)
- Install all the necessary packages
  ```bash
  npm install
  ```
- Run the project
  ```bash
  npm run start
  ```

## How to contribute

- Send a private message to our [Twitter handler ](https://twitter.com/chanmobiledev)
