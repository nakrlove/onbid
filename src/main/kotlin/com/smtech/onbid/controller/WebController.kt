package com.smtech.onbid.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class WebController {
    @RequestMapping(value = ["/", "/{path:^(?!api$).*$}/**"])
    fun redirect(): String {
        return "forward:/index.html"
    }
}