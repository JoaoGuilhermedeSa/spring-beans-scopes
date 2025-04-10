package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import com.example.demo.bean.RequestScopedBean
import com.example.demo.bean.SessionScopedBean
import com.example.demo.bean.SingletonBean
import com.example.demo.bean.PrototypeBean
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.ObjectProvider


@RestController
@RequestMapping("/web-scopes")
class BeanController(
    private val requestScopedBean: RequestScopedBean,
    private val sessionScopedProvider: ObjectProvider<SessionScopedBean>,
    private val singletonBean: SingletonBean,
    private val prototypeBeanProvider: ObjectProvider<PrototypeBean>
) {

    @GetMapping
    fun getScopedBeans(request: HttpServletRequest): Map<String, String> {
        request.getSession(true)

        val sessionScopedBean = sessionScopedProvider.getObject()
        val prototypeBean = prototypeBeanProvider.getObject()
        return mapOf(
            "request" to requestScopedBean.id.toString(),
            "session" to sessionScopedBean.id.toString(),
            "singleton" to singletonBean.id.toString(),
            "prototype" to prototypeBean.id.toString()
        )
    }
}