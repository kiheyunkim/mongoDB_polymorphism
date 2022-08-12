package com.example.demo

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

	fun addEditorV2(id: Long): Boolean {
		val titleAEditor = TitleAV2(id, "TITLEA")
		mongoTemplate.save(titleAEditor)

		val titleBEditor = TitleBV2(id + 1, "TITLEB", "subTitle")
		mongoTemplate.save(titleBEditor)

		return true
	}

	fun getEditorV2(id: Long): EditorV2? {
		return mongoTemplate.findById(id, EditorV2::class.java)
	}
}