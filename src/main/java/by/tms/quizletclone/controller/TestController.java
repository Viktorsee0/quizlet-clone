package by.tms.quizletclone.controller;

import by.tms.quizletclone.entity.Card;
import by.tms.quizletclone.entity.Test;
import by.tms.quizletclone.service.excpetion.CardNotFoundException;
import by.tms.quizletclone.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CardServiceImpl cardService;

    @GetMapping("/{mId}/{cId}")
    public String getTest(@PathVariable long mId,
                          @PathVariable long cId,
                          Model model,
                          HttpSession session) {

        Object test = session.getAttribute("test");
        if (test == null) {
            session.setAttribute("test", new Test());
        }
        Card card = cardService.cardByIdAndModelId(cId, mId);
        model.addAttribute("word", card);

        model.addAttribute("mId", mId);
        model.addAttribute("cId", cId);
        return "test/test";
    }

    @PostMapping("/{mId}/{cId}")
    public String post(@PathVariable long mId,
                       @PathVariable long cId,
                       String translate,
                       Model model,
                       HttpSession session) {
        Card card = cardService.cardByIdAndModelId(cId, mId);
        Test test = (Test) session.getAttribute("test");

        if (!card.getDefinition().equals(translate)) {
            test.add(card.getTerm(), "wrong!");
        } else {
            test.add(card.getTerm(), "right!");
        }

        session.setAttribute("test", test);

        try {

            Card nextCard = cardService.cardByIdAndModelId(cId + 1, mId);
            return "redirect:/test/" + mId + "/" + (nextCard.getId());

        } catch (CardNotFoundException e) {

            model.addAttribute(test);
            session.removeAttribute("test");
            return "test/end";

        }


    }

}
