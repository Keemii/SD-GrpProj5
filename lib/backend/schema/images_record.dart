import 'dart:async';

import 'package:collection/collection.dart';

import '/backend/schema/util/firestore_util.dart';

import 'index.dart';
import '/flutter_flow/flutter_flow_util.dart';

class ImagesRecord extends FirestoreRecord {
  ImagesRecord._(
    super.reference,
    super.data,
  ) {
    _initializeFields();
  }

  // "imageUrl" field.
  String? _imageUrl;
  String get imageUrl => _imageUrl ?? '';
  bool hasImageUrl() => _imageUrl != null;

  // "uploadedAt" field.
  DateTime? _uploadedAt;
  DateTime? get uploadedAt => _uploadedAt;
  bool hasUploadedAt() => _uploadedAt != null;

  // "uploaderId" field.
  String? _uploaderId;
  String get uploaderId => _uploaderId ?? '';
  bool hasUploaderId() => _uploaderId != null;

  void _initializeFields() {
    _imageUrl = snapshotData['imageUrl'] as String?;
    _uploadedAt = snapshotData['uploadedAt'] as DateTime?;
    _uploaderId = snapshotData['uploaderId'] as String?;
  }

  static CollectionReference get collection =>
      FirebaseFirestore.instance.collection('images');

  static Stream<ImagesRecord> getDocument(DocumentReference ref) =>
      ref.snapshots().map((s) => ImagesRecord.fromSnapshot(s));

  static Future<ImagesRecord> getDocumentOnce(DocumentReference ref) =>
      ref.get().then((s) => ImagesRecord.fromSnapshot(s));

  static ImagesRecord fromSnapshot(DocumentSnapshot snapshot) => ImagesRecord._(
        snapshot.reference,
        mapFromFirestore(snapshot.data() as Map<String, dynamic>),
      );

  static ImagesRecord getDocumentFromData(
    Map<String, dynamic> data,
    DocumentReference reference,
  ) =>
      ImagesRecord._(reference, mapFromFirestore(data));

  @override
  String toString() =>
      'ImagesRecord(reference: ${reference.path}, data: $snapshotData)';

  @override
  int get hashCode => reference.path.hashCode;

  @override
  bool operator ==(other) =>
      other is ImagesRecord &&
      reference.path.hashCode == other.reference.path.hashCode;
}

Map<String, dynamic> createImagesRecordData({
  String? imageUrl,
  DateTime? uploadedAt,
  String? uploaderId,
}) {
  final firestoreData = mapToFirestore(
    <String, dynamic>{
      'imageUrl': imageUrl,
      'uploadedAt': uploadedAt,
      'uploaderId': uploaderId,
    }.withoutNulls,
  );

  return firestoreData;
}

class ImagesRecordDocumentEquality implements Equality<ImagesRecord> {
  const ImagesRecordDocumentEquality();

  @override
  bool equals(ImagesRecord? e1, ImagesRecord? e2) {
    return e1?.imageUrl == e2?.imageUrl &&
        e1?.uploadedAt == e2?.uploadedAt &&
        e1?.uploaderId == e2?.uploaderId;
  }

  @override
  int hash(ImagesRecord? e) =>
      const ListEquality().hash([e?.imageUrl, e?.uploadedAt, e?.uploaderId]);

  @override
  bool isValidKey(Object? o) => o is ImagesRecord;
}
