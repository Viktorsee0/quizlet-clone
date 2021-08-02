package by.tms.quizletclone.controller;

import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public String search( String title, Model model){
        List<LearnModel> allByTitle = modelService.getAllByTitle(title);

        model.addAttribute("models", allByTitle);

        return "search/searchList" ;
    }

}
