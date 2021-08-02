package by.tms.quizletclone.controller;

import by.tms.quizletclone.dto.ModelChangeDTO;
import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.service.CardService;
import by.tms.quizletclone.service.ModelService;
import by.tms.quizletclone.util.ImageUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static by.tms.quizletclone.util.SaveImage.saveImage;
import static by.tms.quizletclone.util.SaveImage.validateImage;


@Controller
@RequestMapping("model")
public class ModelController {
    private final ModelService modelService;
    private final CardService cardServiceImpl;

    @Autowired
    public ModelController(ModelService modelService, CardService cardServiceImpl) {
        this.modelService = modelService;
        this.cardServiceImpl = cardServiceImpl;
    }

    @GetMapping("create")
    public String createModel(Model model) {
        model.addAttribute("model", new LearnModel());
        return "models/modelCreate";
    }


    @PostMapping("create")
    public String createModel(@AuthenticationPrincipal User user,
                              @ModelAttribute("model") @Valid LearnModel learnModel,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "models/modelCreate";
        }

        modelService.save(learnModel, user);
        return "redirect:/";
    }

    @PostMapping("addCard/{id}")
    public String addCard(@PathVariable long id,
                          @ModelAttribute("card") @Valid Card card,
                          @RequestParam("file") MultipartFile image,
                          BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "models/model";
        }


        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(image.getOriginalFilename(), image);
                card.setFilename(image.getOriginalFilename());
            }
        } catch (ImageUploadException e) {
            e.printStackTrace();
            bindingResult.reject(e.getMessage());
            return "models/model";
        }
//
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//            card.setFilename(resultFilename);
//        }


        cardServiceImpl.create(card, id);
        return "redirect:/model/show/" + id;
    }

    @GetMapping("show")
    public String showModels(Model model, @AuthenticationPrincipal User user) {

        List<LearnModel> all = modelService.getAllByUser(user.getId());
        model.addAttribute("models", all);

        return "models/models";
    }

    @GetMapping("show/{id}")
    public String showModel(Model model, @PathVariable long id, @AuthenticationPrincipal User user) {

        LearnModel allModels = modelService.getAll(id);
        List<Card> allCards = cardServiceImpl.getAll(id);
        model.addAttribute("model", allModels);
        model.addAttribute("dto", new ModelChangeDTO());
        model.addAttribute("cards", allCards);
        model.addAttribute("card", new Card());
        model.addAttribute("user", user);

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

    @GetMapping("{mId}/removeCard/{cId}")
    public String removeCard(Model model, @PathVariable long cId, @PathVariable long mId) {

        cardServiceImpl.delete(cId);

        return "redirect:/model/show/" + mId;
    }


}
