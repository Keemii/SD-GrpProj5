import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';

Future initFirebase() async {
  if (kIsWeb) {
    await Firebase.initializeApp(
        options: FirebaseOptions(
            apiKey: "AIzaSyDOdSpxP6ahN7SqH6b7L2AJTlLVN6M4R3c",
            authDomain: "fly-pro-dtexcj.firebaseapp.com",
            projectId: "fly-pro-dtexcj",
            storageBucket: "fly-pro-dtexcj.appspot.com",
            messagingSenderId: "420996664359",
            appId: "1:420996664359:web:6a1f01a83099c676f5d202"));
  } else {
    await Firebase.initializeApp();
  }
}
