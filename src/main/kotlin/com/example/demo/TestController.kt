package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
	@Autowired
	private lateinit var serviceLayer: ServiceLayer

	@GetMapping("/test/add")
	fun addEditor(@RequestParam("id") id: Long): String {
		return serviceLayer.addEditor(id).toString()
	}

	@GetMapping("/test/get")
	fun getEditor(@RequestParam("id") id: Long): Editor {

		return serviceLayer.getEditor(id)
	}
}