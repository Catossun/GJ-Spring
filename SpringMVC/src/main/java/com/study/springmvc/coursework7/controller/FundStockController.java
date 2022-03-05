package com.study.springmvc.coursework7.controller;

import com.study.springmvc.coursework7.entity.Fund;
import com.study.springmvc.coursework7.entity.FundStock;
import com.study.springmvc.coursework7.repository.FundDao;
import com.study.springmvc.coursework7.repository.FundStockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Controller
@RequestMapping("/coursework7/fundstock")
public class FundStockController {
    @Autowired
    FundStockDao fundStockDao;

    @Autowired
    private FundDao fundDao;

    private int pageNumber = -1;

    @GetMapping("/")
    public String index(@ModelAttribute FundStock fundStock, Model model) {
        return "redirect:./page/" + pageNumber;
    }

    @GetMapping("/page/{pageNumber}")
    public String page(@PathVariable("pageNumber") int pageNumber, @ModelAttribute FundStock fundstock, Model model) {
        this.pageNumber = pageNumber;
        int offset = (pageNumber - 1) * FundStockDao.LIMIT;
        initModel(model, "POST", offset);
        return "coursework7/fundstock";
    }

    @GetMapping("/{sid}")
    public String get(@PathVariable("sid") Integer sid, Model model) {
        initModel(model, "PUT", -1);
        model.addAttribute("fundstock", fundStockDao.get(sid));
        return "coursework7/fundstock";
    }

    private void initModel(Model model, String method, int offset) {
        List<FundStock> fundstocks = fundStockDao.queryPage(offset);
        List<Fund> funds = fundDao.queryAll();
        int pageTotalCount = fundStockDao.count() / FundStockDao.LIMIT;
//        model.addAttribute("fundstock", new FundStock());
        model.addAttribute("_method", method);
        model.addAttribute("fundstocks", fundstocks);
        model.addAttribute("funds", funds);
        model.addAttribute("pageTotalCount", pageTotalCount);
        model.addAttribute("groupMap", getGroupMap());
    }

    private Map<String, Integer> getGroupMap() {
        // select s.symbol, sum(s.share) as share
        // from fundstock s
        // group by s.symbol
        List<FundStock> fundstocks = fundStockDao.queryAll();
        return fundstocks.stream()
                .filter(fs -> Objects.nonNull(fs.getShare()))
                .collect(groupingBy(FundStock::getSymbol,
                        summingInt(FundStock::getShare)));
    }

    @PostMapping("/")
    public String add(@ModelAttribute @Valid FundStock fundStock, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            initModel(model, "POST", -1);
            model.addAttribute("fundstock", fundStock);
            return "coursework7/fundstock";
        }
        fundStockDao.add(fundStock);
        return "redirect:./";
    }

    @PutMapping("/{sid}")
    public String update(@PathVariable Integer sid, @ModelAttribute @Valid FundStock fundStock, BindingResult bindingResult) {
        fundStock.setSid(sid);
        fundStockDao.update(fundStock);
        return "redirect:./";
    }
}
