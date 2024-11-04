import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';

Future initFirebase() async {
  if (kIsWeb) {
    await Firebase.initializeApp(
        options: const FirebaseOptions(
            apiKey: "AIzaSyA4WYObXHkDPZrJftxFcwhatrT6twBHO-E",
            authDomain: "flypro-360a0.firebaseapp.com",
            projectId: "flypro-360a0",
            storageBucket: "flypro-360a0.firebasestorage.app",
            messagingSenderId: "508183339408",
            appId: "1:508183339408:web:123deb8c154604a1649315",
            measurementId: "G-T2P0VRTVCM"));
  } else {
    await Firebase.initializeApp();
  }
}
