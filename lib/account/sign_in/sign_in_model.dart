import '/flutter_flow/flutter_flow_util.dart';
import 'sign_in_widget.dart' show SignInWidget;
import 'package:flutter/material.dart';

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
