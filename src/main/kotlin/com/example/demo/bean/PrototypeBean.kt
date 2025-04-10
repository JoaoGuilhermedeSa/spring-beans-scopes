package com.example.demo.bean

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext
import java.util.*

@Component
@Scope("prototype")
class PrototypeBean {
    val id = UUID.randomUUID()
}