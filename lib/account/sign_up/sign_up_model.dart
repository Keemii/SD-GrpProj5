import '/auth/firebase_auth/auth_util.dart';
import '/flutter_flow/flutter_flow_icon_button.dart';
import '/flutter_flow/flutter_flow_theme.dart';
import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/flutter_flow_widgets.dart';
import 'sign_up_widget.dart' show SignUpWidget;
import 'package:auto_size_text/auto_size_text.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class SignUpModel extends FlutterFlowModel<SignUpWidget> {
  ///  State fields for stateful widgets in this page.

  // State field(s) for emailAddressSignUp widget.
  FocusNode? emailAddressSignUpFocusNode;
  TextEditingController? emailAddressSignUpTextController;
  String? Function(BuildContext, String?)?
      emailAddressSignUpTextControllerValidator;
  // State field(s) for passwordSignUp widget.
  FocusNode? passwordSignUpFocusNode;
  TextEditingController? passwordSignUpTextController;
  late bool passwordSignUpVisibility;
  String? Function(BuildContext, String?)?
      passwordSignUpTextControllerValidator;
  // State field(s) for confirmPasswordSignUp widget.
  FocusNode? confirmPasswordSignUpFocusNode;
  TextEditingController? confirmPasswordSignUpTextController;
  late bool confirmPasswordSignUpVisibility;
  String? Function(BuildContext, String?)?
      confirmPasswordSignUpTextControllerValidator;

  @override
  void initState(BuildContext context) {
    passwordSignUpVisibility = false;
    confirmPasswordSignUpVisibility = false;
  }

  @override
  void dispose() {
    emailAddressSignUpFocusNode?.dispose();
    emailAddressSignUpTextController?.dispose();

    passwordSignUpFocusNode?.dispose();
    passwordSignUpTextController?.dispose();

    confirmPasswordSignUpFocusNode?.dispose();
    confirmPasswordSignUpTextController?.dispose();
  }
}
