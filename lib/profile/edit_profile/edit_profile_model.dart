import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'edit_profile_widget.dart' show EditProfileWidget;
import 'package:flutter/material.dart';

class EditProfileModel extends FlutterFlowModel<EditProfileWidget> {
  ///  State fields for stateful widgets in this page.

  bool isDataUploading = false;
  FFUploadedFile uploadedLocalFile =
      FFUploadedFile(bytes: Uint8List.fromList([]));
  String uploadedFileUrl = '';

  // State field(s) for yourNameEdit widget.
  FocusNode? yourNameEditFocusNode;
  TextEditingController? yourNameEditTextController;
  String? Function(BuildContext, String?)? yourNameEditTextControllerValidator;
  // State field(s) for yourCityEdit widget.
  FocusNode? yourCityEditFocusNode;
  TextEditingController? yourCityEditTextController;
  String? Function(BuildContext, String?)? yourCityEditTextControllerValidator;
  // State field(s) for yourStateEdit widget.
  String? yourStateEditValue;
  FormFieldController<String>? yourStateEditValueController;
  // State field(s) for yourBioEdit widget.
  FocusNode? yourBioEditFocusNode;
  TextEditingController? yourBioEditTextController;
  String? Function(BuildContext, String?)? yourBioEditTextControllerValidator;

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {
    yourNameEditFocusNode?.dispose();
    yourNameEditTextController?.dispose();

    yourCityEditFocusNode?.dispose();
    yourCityEditTextController?.dispose();

    yourBioEditFocusNode?.dispose();
    yourBioEditTextController?.dispose();
  }
}
