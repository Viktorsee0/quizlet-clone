package by.tms.quizletclone.controller;

import by.tms.quizletclone.entity.Folder;
import by.tms.quizletclone.entity.LearnModel;
import by.tms.quizletclone.entity.User;
import by.tms.quizletclone.service.FolderService;
import by.tms.quizletclone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;
    private final ModelService modelService;

    @Autowired
    public FolderController(FolderService folderService, ModelService modelService) {
        this.folderService = folderService;
        this.modelService = modelService;
    }

    @PostMapping("/create")
    public String createFolder(@AuthenticationPrincipal User user,
                               @Valid Folder folder,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "index";
        }

        folderService.save(folder, user);
        return "index";
    }

    @GetMapping("/show")
    public String showFolders(@AuthenticationPrincipal User user, Model model) {
        List<Folder> all = folderService.getAll(user);
        model.addAttribute("folders", all);
        return "folder/folders";
    }

    @GetMapping("/show/{id}")
    public String showFolder(@AuthenticationPrincipal User user,
                             Model model,
                             @PathVariable long id) {

        Folder byId = folderService.getById(id);
        List<LearnModel> all = modelService.getAll(user, byId);
        model.addAttribute("folder", byId);
        model.addAttribute("models", all);
        model.addAttribute("id", id);
        return "folder/folder";
    }


    @GetMapping("/show/{id}/add")
    public String addModule(@AuthenticationPrincipal User user,Model model, @PathVariable long id) {
        List<LearnModel> all = modelService.getAllWhereFolderIsNull(user);
        model.addAttribute("models", all);
        model.addAttribute("id", id);
        return "folder/addModel";
    }

    @GetMapping("/show/{fId}/add/{mId}")
    public String addModule(@PathVariable long fId, @PathVariable long mId) {
        folderService.addModule(fId, mId);
        return "redirect:/folder/show/" + fId + "/add";
    }

    @GetMapping("/show/{fId}/delete/{mId}")
    public String deleteModule(@PathVariable long fId, @PathVariable long mId) {
        folderService.delete(mId);
        return "redirect:/folder/show/" + fId;
    }


}
