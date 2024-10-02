package model.common

case class ViewValueCommon(
  title:  String,      // pageのタイトル
  cssSrc: Seq[String], // pageで読み込むcssのファイル名
  jsSrc:  Seq[String], // pageで読み込むjavascriptのファイル名
) {}
