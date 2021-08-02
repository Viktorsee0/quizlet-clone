package by.tms.quizletclone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    private Map<String, String> ans = new HashMap<>();

    public void add(String f, String n){
        ans.put(f, n);
    }

}
