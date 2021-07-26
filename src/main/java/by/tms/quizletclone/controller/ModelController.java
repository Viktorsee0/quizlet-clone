package by.tms.quizletclone.controller;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("model")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("create")
    public String createModel(Model model) {
        model.addAttribute("model", new LearnModel());
        return "models/modelCreate";
    }


    @PostMapping("create")
    public RedirectView createModel(@AuthenticationPrincipal User user, @ModelAttribute("model") LearnModel learnModel) {
        modelService.save(learnModel, user);
        return new RedirectView("/");
    }

//    @GetMapping("addCard/{id}")
//    public String addCard(Model model, @PathVariable long id) {
//        model.addAttribute("card", new Card());
//        model.addAttribute("id", id);
//        return "models/addCard";
//    }

    @PostMapping("addCard/{id}")
    public String addCard(@PathVariable long id,
                          @ModelAttribute("card") Card card) {
        modelService.addCard(id, card);
        return "models/addCard";
    }

    @GetMapping("show")
    public String showModels(Model model) {

        List<LearnModel> all = modelService.getAll();
        model.addAttribute("models", all);

        return "models/models";
    }

    @GetMapping("show/{id}")
    public String showModel(Model model, @PathVariable long id) {

        LearnModel all = modelService.getAll(id);
        model.addAttribute("model", all);
        model.addAttribute("dto", new ModelChangeDTO());
        model.addAttribute("card", new Card());

        return "models/model";
    }

    @PostMapping("changeTitle/{id}")
    public String changeTitle(@PathVariable long id,
                              @ModelAttribute("dto") @Valid ModelChangeDTO dto,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "models/model";
        }

        modelService.changeTitle(id, dto);

        return "redirect:/model/show/" + id;
    }

    @GetMapping("removeCard/{mId}/{cId}")
    public String removeCard(Model model, @PathVariable long mId, @PathVariable long cId) {



        return "models/model";
    }

}
