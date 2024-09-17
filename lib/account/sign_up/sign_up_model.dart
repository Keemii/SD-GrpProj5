import '/flutter_flow/flutter_flow_util.dart';
import 'sign_up_widget.dart' show SignUpWidget;
import 'package:flutter/material.dart';

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
