package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
	@Autowired
	private lateinit var serviceLayer: ServiceLayer

	@GetMapping("/v1/test/add")
	fun addEditor(@RequestParam("id") id: Long): String {
		return serviceLayer.addEditor(id).toString()
	}

	@GetMapping("/v1/test/get")
	fun getEditor(@RequestParam("id") id: Long): Editor {

		return serviceLayer.getEditor(id)
	}

	@GetMapping("/v2/test/add")
	fun addEditorV2(@RequestParam("id") id: Long): String {
		return serviceLayer.addEditorV2(id).toString()
	}

	@GetMapping("/v2/test/get")
	fun getEditorV2(@RequestParam("id") id: Long): EditorV2? {

		return serviceLayer.getEditorV2(id)
	}
}