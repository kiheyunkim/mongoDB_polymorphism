package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

enum class EditorType {
	TITLE_A, TITLE_B, IMAGE
}

@Document("editor")
data class Editor(
	@field:Id val id: Long,
	val EditorTitle: String,
	val editorContent: List<EditorContent>
)

interface EditorContent {
	fun getEditorType(): EditorType
}

@Document("editor")
@TypeAlias("TitleA")
class TitleA(val title: String) : EditorContent{
	override fun getEditorType() = EditorType.TITLE_A
}

@Document("editor")
@TypeAlias("TitleB")
class TitleB(val title: String, val subTitle: String) : EditorContent {
	override fun getEditorType() = EditorType.TITLE_B
}

@Document("editor")
@TypeAlias("Image")
class Image(val imageUrl: String, val imageThumbnail: String) : EditorContent {
	override fun getEditorType() = EditorType.IMAGE
}