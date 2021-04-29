package com.study.login.web.controller;

import com.study.login.domain.dto.ArticleDto;
import com.study.login.service.spec.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final HttpServletRequest request;

    @GetMapping("/form")
    public String articleForm(Model model) {
        model.addAttribute("article", new ArticleDto());
        model.addAttribute("userId", request.getAttribute("loginId"));
        return "/article/article";
    }

    @GetMapping("/detail/{articleId}")
    public String detail(@PathVariable int articleId, Model model) {
        ArticleDto dto = articleService.findById(articleId);
        model.addAttribute("article", dto);
        model.addAttribute("loginId", request.getAttribute("loginId"));

        return "/article/article";
    }

    @GetMapping("/find/all")
    public ModelAndView findAll() {
        List<ArticleDto> articles = articleService.findAll();
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/article/article_list");
        mv.addObject("articles", articles);
        mv.addObject("loginId", request.getAttribute("loginId"));

        return mv;
    }

    @PostMapping("/save")
    public String save(@RequestBody ArticleDto articleDto) {
        articleService.save(articleDto);
        return "redirect:/article/find/all";
    }

    @PostMapping("/remove")
    public String remove(@RequestBody Map<String,Long> param) {
        System.out.println("articleId = " + param.get("articleId"));
        long articleId = param.get("articleId");
        articleService.remove(articleId);

        return "redirect:/article/find/all";
    }
}
