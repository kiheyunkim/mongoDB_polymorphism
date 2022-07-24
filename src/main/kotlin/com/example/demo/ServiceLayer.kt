package com.example.demo

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Service

@Service
class ServiceLayer(val mongoTemplate: MongoTemplate) {

	fun addEditor(id: Long): Boolean {
		val editorContentList: List<EditorContent> = listOf(
			TitleA("타이틀 A 제목"),
			TitleB("타이틀 B 제목", "타이틀 B 부제목"),
			TitleA("타이틀 A 제목2"),
			Image("이이지 유알엘", "썸네일"),
			TitleB("타이틀 B 제목2", "타이틀 B 부제목2"),
		)

		val editor = Editor(id = id, "난 제목이다", editorContentList)

		mongoTemplate.save(editor)

		return true
	}

	fun getEditor(id: Long): Editor {
		return mongoTemplate.findById(id, Editor::class.java) ?: Editor(1, "1", listOf())
	}
}

enum class EditorType {
	TITLE_A, TITLE_B, IMAGE
}

@Document("editor")
data class Editor(
	@field:Id val id: Long,
	val EditorTitle: String,
	val editorContent: List<EditorContent>
)

@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "editorType.name"
)
@JsonSubTypes(
	JsonSubTypes.Type(value = TitleA::class),
	JsonSubTypes.Type(value = TitleB::class),
	JsonSubTypes.Type(value = Image::class)
)
interface EditorContent {
	fun getEditorType(): EditorType
}

@Document("editor")
@TypeAlias("TITLE_A")
//@JsonTypeName("TITLE_A")
class TitleA(val title: String) : EditorContent{
	override fun getEditorType() = EditorType.TITLE_A
}

@Document("editor")
@TypeAlias("TITLE_B")
//@JsonTypeName("TITLE_B")
class TitleB(val title: String, val subTitle: String) : EditorContent {
	override fun getEditorType() = EditorType.TITLE_B
}

@Document("editor")
@TypeAlias("IMAGE")
//@JsonTypeName("IMAGE")
class Image(val imageUrl: String, val imageThumbnail: String) : EditorContent {
	override fun getEditorType() = EditorType.IMAGE
}