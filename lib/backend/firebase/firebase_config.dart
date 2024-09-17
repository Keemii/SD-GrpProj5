import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';

Future initFirebase() async {
  if (kIsWeb) {
    await Firebase.initializeApp(
        options: FirebaseOptions(
            apiKey: "AIzaSyDDZZuEdUg6PMLbtRAwTMgtqkT3asm-E7I",
            authDomain: "fly-pro-asqtbr.firebaseapp.com",
            projectId: "fly-pro-asqtbr",
            storageBucket: "fly-pro-asqtbr.appspot.com",
            messagingSenderId: "379695783404",
            appId: "1:379695783404:web:cf55853873e12659cab4c9"));
  } else {
    await Firebase.initializeApp();
  }
}
