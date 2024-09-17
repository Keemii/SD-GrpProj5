import '/flutter_flow/flutter_flow_util.dart';
import '/flutter_flow/form_field_controller.dart';
import 'create_profile_widget.dart' show CreateProfileWidget;
import 'package:flutter/material.dart';

class CreateProfileModel extends FlutterFlowModel<CreateProfileWidget> {
  ///  State fields for stateful widgets in this page.

  bool isDataUploading = false;
  FFUploadedFile uploadedLocalFile =
      FFUploadedFile(bytes: Uint8List.fromList([]));
  String uploadedFileUrl = '';

  // State field(s) for yourName widget.
  FocusNode? yourNameFocusNode;
  TextEditingController? yourNameTextController;
  String? Function(BuildContext, String?)? yourNameTextControllerValidator;
  // State field(s) for yourCity widget.
  FocusNode? yourCityFocusNode;
  TextEditingController? yourCityTextController;
  String? Function(BuildContext, String?)? yourCityTextControllerValidator;
  // State field(s) for yourState widget.
  String? yourStateValue;
  FormFieldController<String>? yourStateValueController;
  // State field(s) for yourBio widget.
  FocusNode? yourBioFocusNode;
  TextEditingController? yourBioTextController;
  String? Function(BuildContext, String?)? yourBioTextControllerValidator;

  @override
  void initState(BuildContext context) {}

  @override
  void dispose() {
    yourNameFocusNode?.dispose();
    yourNameTextController?.dispose();

    yourCityFocusNode?.dispose();
    yourCityTextController?.dispose();

    yourBioFocusNode?.dispose();
    yourBioTextController?.dispose();
  }
}
