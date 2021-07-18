package by.tms.quizletclone.controller;

import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("model")
public class ModelController {

    private ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("create")
    public String createModel(Model model){
        model.addAttribute("model", new LearnModel());
        return "model/modelCreate";
    }


    @PostMapping("create")
    public String createModel(@ModelAttribute("model") LearnModel learnModel){
        System.out.println(learnModel);
        modelService.save(learnModel);
        return "model/modelCreate";
    }

//    @GetMapping("addCard/${id}")
//    public String addCard(Model model){
//        model.addAttribute("card", new Card());
//        return "model/addCard";
//    }
//
//    @PostMapping("addCard/${id}")
//    public String addCard(@ModelAttribute("model") LearnModel learnModel){
//
//        System.out.println(learnModel);
//        return "model/addCard";
//    }

}
