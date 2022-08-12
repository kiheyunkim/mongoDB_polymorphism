package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document

enum class EditorTypeV2 {
	TITLE_A, TITLE_B, IMAGE
}

@Document("editorV2")
abstract class EditorV2(
	@field:Id val id: Long,
	open val title: String,
)

@Document("editorV2")
@TypeAlias("TitleA")
class TitleAV2(id: Long, title: String) : EditorV2(id, title)

@Document("editorV2")
@TypeAlias("TitleB")
class TitleBV2(id: Long, title: String, val subTitle: String) : EditorV2(id, title)
