import '/flutter_flow/flutter_flow_util.dart';
import 'phone_verify_widget.dart' show PhoneVerifyWidget;
import 'package:flutter/material.dart';

class PhoneVerifyModel extends FlutterFlowModel<PhoneVerifyWidget> {
  ///  State fields for stateful widgets in this page.

  // State field(s) for PinCode widget.
  TextEditingController? pinCodeController;
  String? Function(BuildContext, String?)? pinCodeControllerValidator;

  @override
  void initState(BuildContext context) {
    pinCodeController = TextEditingController();
  }

  @override
  void dispose() {
    pinCodeController?.dispose();
  }
}
