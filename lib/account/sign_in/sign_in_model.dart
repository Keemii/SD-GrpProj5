import '/auth/firebase_auth/auth_util.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'sign_in_widget.dart' show SignInWidget;
import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class SignInModel extends FlutterFlowModel<SignInWidget> {
  ///  State fields for stateful widgets in this page.

  // State field(s) for emailAddressSignIn widget.
  FocusNode? emailAddressSignInFocusNode;
  TextEditingController? emailAddressSignInTextController;
  String? Function(BuildContext, String?)?
      emailAddressSignInTextControllerValidator;
  // State field(s) for passwordSignIn widget.
  FocusNode? passwordSignInFocusNode;
  TextEditingController? passwordSignInTextController;
  late bool passwordSignInVisibility;
  String? Function(BuildContext, String?)?
      passwordSignInTextControllerValidator;

  @override
  void initState(BuildContext context) {
    passwordSignInVisibility = false;
  }

  @override
  void dispose() {
    emailAddressSignInFocusNode?.dispose();
    emailAddressSignInTextController?.dispose();

    passwordSignInFocusNode?.dispose();
    passwordSignInTextController?.dispose();
  }
}
