package com.study.springmvc.coursework5.controller;

import com.study.springmvc.coursework5.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/coursework5/lotto")
public class LottoController {

    @Autowired
    private LottoService lottoService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Set<Integer>> lottos = lottoService.getLottos();
        model.addAttribute("lottoList", lottos);
        Map<Integer, Long> lottoCount = lottoService.groupAndCountLottos(lottos);
        model.addAttribute("lottoShowTimes", lottoCount);
        return "coursework5/show_lotto";
    }

    @RequestMapping("/add")
    public String addLotto() {
        lottoService.addLotto();
        return "redirect:./";
    }

    @RequestMapping("/update/{index}")
    public String updateLotto(@PathVariable("index") int index) {
        lottoService.updateLotto(index);
        return "redirect:../";
    }

    @RequestMapping("/delete/{index}")
    public String deleteLotto(@PathVariable("index") int index) {
        lottoService.deleteLotto(index);
        return "redirect:../";
    }
}
